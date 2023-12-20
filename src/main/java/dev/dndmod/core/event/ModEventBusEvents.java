package dev.dndmod.core.event;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.entity.ModEntities;
import dev.dndmod.core.entity.custom.OrcEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DNDMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ORC.get(), OrcEntity.setAttributes());
    }
}
