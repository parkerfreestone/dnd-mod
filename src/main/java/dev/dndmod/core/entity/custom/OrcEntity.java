package dev.dndmod.core.entity.custom;

import dev.dndmod.core.entity.ai.OrcAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class OrcEntity extends Monster implements GeoEntity {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(OrcEntity.class, EntityDataSerializers.BOOLEAN);
    protected final RawAnimation WALK_ANIMATION = RawAnimation.begin().thenLoop("animation.orc.walk");
    protected final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenLoop("animation.orc.attack");

    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public int attackAnimationTimeout = 20;

    public OrcEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 42D)
                .add(Attributes.ATTACK_DAMAGE, 6f)
                .add(Attributes.FOLLOW_RANGE, 35D)
                .add(Attributes.ATTACK_KNOCKBACK, 1f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3f)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new OrcAttackGoal(this, 1.0D, true));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));

        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9, 32.0F));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackController", 2, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState<OrcEntity> orcEntityAnimationState) {
       if (this.isAttacking() && attackAnimationTimeout <= 0) {
           attackAnimationTimeout = 20;
           return orcEntityAnimationState.setAndContinue(ATTACK_ANIMATION);
       } else {
           --this.attackAnimationTimeout;
       }

        if (!this.isAttacking()) {
            return PlayState.STOP;
        }

        return PlayState.CONTINUE;
    }

    private <E extends OrcEntity> PlayState predicate(final AnimationState<E> orcEntityAnimationState) {
        if (orcEntityAnimationState.isMoving()) {
            return orcEntityAnimationState.setAndContinue(WALK_ANIMATION);
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PIGLIN_BRUTE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }
}
