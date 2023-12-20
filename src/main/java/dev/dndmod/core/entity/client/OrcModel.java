package dev.dndmod.core.entity.client;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.entity.custom.OrcEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.DataTicket;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OrcModel extends GeoModel<OrcEntity> {
    @Override
    public ResourceLocation getModelResource(OrcEntity orcEntity) {
        return new ResourceLocation(DNDMod.MOD_ID, "geo/orc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcEntity orcEntity) {
        return new ResourceLocation(DNDMod.MOD_ID, "textures/entity/orc.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcEntity orcEntity) {
        return new ResourceLocation(DNDMod.MOD_ID, "animations/orc.animation.json");
    }

    @Override
    public void setCustomAnimations(OrcEntity animatable, long instanceId, AnimationState<OrcEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
