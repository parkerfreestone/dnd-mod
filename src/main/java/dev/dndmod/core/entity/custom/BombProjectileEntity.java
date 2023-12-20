package dev.dndmod.core.entity.custom;

import dev.dndmod.core.entity.ModEntities;
import dev.dndmod.core.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
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

    public BombProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BombProjectileEntity(Level pLevel) {
        super(ModEntities.BOMB_PROJECTILE.get(), pLevel);
    }

    public BombProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BOMB_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BOMB.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (!this.level().isClientSide()) {
            this.explode();
        }

        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide()) {
            this.explode();
        }

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
