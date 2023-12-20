package dev.dndmod.core.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.dndmod.core.DNDMod;
import dev.dndmod.core.entity.custom.BombProjectileEntity;
import dev.dndmod.core.entity.custom.OrcEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BombProjectileRenderer extends GeoEntityRenderer<BombProjectileEntity> {

    public BombProjectileRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BombProjectileModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BombProjectileEntity animatable) {
        return new ResourceLocation(DNDMod.MOD_ID, "textures/entity/bomb.png");
    }

    @Override
    public void render(BombProjectileEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
