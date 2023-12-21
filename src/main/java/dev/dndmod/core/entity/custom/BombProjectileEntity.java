package dev.dndmod.core.entity.custom;

import dev.dndmod.core.entity.ModEntities;
import dev.dndmod.core.items.ModItems;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.ForgeEventFactory;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BombProjectileEntity extends ThrowableItemProjectile implements GeoEntity {
    protected final RawAnimation PRIME_ANIMATION = RawAnimation.begin().thenLoop("animation.model.prime");
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final float EXPLOSION_POWER = 2.5F;
    private final int FUSE_DURRATION_TICKS = 5 * 20;

    private int currentFuseTime = FUSE_DURRATION_TICKS;
    private int particleTickCounter = 0;

    public BombProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setBoundingBox(new AABB(0.5, 0.5, 0.5, 0.5, 0.5, 0.5));
    }

    public BombProjectileEntity(Level pLevel) {
        super(ModEntities.BOMB_PROJECTILE.get(), pLevel);
        this.setBoundingBox(new AABB(0.5, 0.5, 0.5, 0.5, 0.5, 0.5));
    }

    public BombProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BOMB_PROJECTILE.get(), livingEntity, pLevel);
        this.setBoundingBox(new AABB(0.5, 0.5, 0.5, 0.5, 0.5, 0.5));
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide()) {
            if (currentFuseTime-- <= 0) {
                this.explode();
            }
        }

        if (this.level().isClientSide()) {
            if (!this.isRemoved()) {
                if (++particleTickCounter >= 5) {
                 particleTickCounter = 0;

                 this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + this.getBbHeight() / 3.0, this.getZ(), 0.0, 0.04, 0.0);
                }
            }
        }

        super.tick();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BOMB.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        this.setDeltaMovement(0, 0, 0);
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        this.setDeltaMovement(0, 0, 0);
        super.onHitBlock(pResult);
    }

    private void explode() {
        boolean canGrief = ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());

        this.level().broadcastEntityEvent(this, ((byte) 3));
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), EXPLOSION_POWER, canGrief, Level.ExplosionInteraction.TNT);
        this.discard();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    private <E extends BombProjectileEntity> PlayState predicate(final AnimationState<E> bombEntityAnimationState) {
        bombEntityAnimationState.setAndContinue(PRIME_ANIMATION);

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
