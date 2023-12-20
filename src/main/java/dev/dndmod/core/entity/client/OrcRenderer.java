package dev.dndmod.core.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.dndmod.core.DNDMod;
import dev.dndmod.core.entity.custom.OrcEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrcRenderer extends GeoEntityRenderer<OrcEntity> {

    public OrcRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OrcModel());
    }

    @Override
    public ResourceLocation getTextureLocation(OrcEntity animatable) {
        return new ResourceLocation(DNDMod.MOD_ID, "textures/entity/orc.png");
    }

    @Override
    public void render(OrcEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
