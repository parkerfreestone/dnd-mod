package dev.dndmod.core.entity.client;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.entity.custom.BombProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BombProjectileModel extends GeoModel<BombProjectileEntity> {
    @Override
    public ResourceLocation getModelResource(BombProjectileEntity bombProjectileEntity) {
        return new ResourceLocation(DNDMod.MOD_ID, "geo/bomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BombProjectileEntity bombProjectileEntity) {
        return new ResourceLocation(DNDMod.MOD_ID, "textures/entity/bomb.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BombProjectileEntity bombProjectileEntity) {
        return new ResourceLocation(DNDMod.MOD_ID, "animations/bomb.animation.json");
    }


}
