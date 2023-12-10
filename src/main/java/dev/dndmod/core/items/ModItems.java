package dev.dndmod.core.items;

import dev.dndmod.core.DNDMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DNDMod.MOD_ID);

    public static final RegistryObject<Item> ORC_SWORD = ITEMS.register("orc_sword",
            () -> new SwordItem(Tiers.IRON, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> ORC_TOOTH = ITEMS.register("orc_tooth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
