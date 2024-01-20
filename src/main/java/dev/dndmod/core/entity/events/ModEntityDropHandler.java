package dev.dndmod.core.entity.events;

import dev.dndmod.core.entity.ModEntities;
import dev.dndmod.core.items.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber()
public class ModEntityDropHandler {
    private static final Random RANDOM = new Random();
    private static final Map<EntityType<?>, ModDropConfig> dropConfigs = new HashMap<>();

    public static void setupDropConfigs() {
        dropConfigs.put(ModEntities.ORC.get(), new ModDropConfig(ModItems.ORC_TOOTH.get(), 1, 2));
    }

    @SubscribeEvent
    public static void onLivingDrop(LivingDropsEvent event) {
        EntityType<?> entityType = event.getEntity().getType();
        ModDropConfig config = dropConfigs.get(entityType);

        if (config != null) {
            int amount = config.getMinAmount() + RANDOM.nextInt(config.getMaxAmount() - config.getMinAmount() + 1);
            ItemStack droppedItem = new ItemStack(ModItems.ORC_TOOTH.get(), amount);

            event.getDrops().add(new ItemEntity(event.getEntity().level(),
                    event.getEntity().getX(),
                    event.getEntity().getY(),
                    event.getEntity().getZ(),
                    droppedItem
            ));
        }
    }
}
