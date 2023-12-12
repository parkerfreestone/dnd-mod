package dev.dndmod.core.items;

import dev.dndmod.core.DNDMod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DNDMod.MOD_ID);

    public static final RegistryObject<Item> ORC_TOOTH = ITEMS.register("orc_tooth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_MAGMATITE = ITEMS.register("raw_magmatite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGMATITE_INGOT = ITEMS.register("magmatite_ingot",
            () -> new Item(new Item.Properties()));

    // ORC TOOTH TOOLS
    public static final RegistryObject<Item> ORC_TOOTH_SWORD = ITEMS.register("orc_tooth_sword",
            () -> new SwordItem(ModToolTiers.ORC_TOOTH, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> ORC_TOOTH_PICKAXE = ITEMS.register("orc_tooth_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ORC_TOOTH, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> ORC_TOOTH_AXE = ITEMS.register("orc_tooth_axe",
            () -> new AxeItem(ModToolTiers.ORC_TOOTH, 5, 1, new Item.Properties()));
    public static final RegistryObject<Item> ORC_TOOTH_SHOVEL = ITEMS.register("orc_tooth_shovel",
            () -> new ShovelItem(ModToolTiers.ORC_TOOTH, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> ORC_TOOTH_HOE = ITEMS.register("orc_tooth_hoe",
            () -> new HoeItem(ModToolTiers.ORC_TOOTH, 0, 0, new Item.Properties()));

    // MAGMATITE ARMOR
    public static final RegistryObject<Item> MAGMATITE_HELMET = ITEMS.register("magmatite_helmet",
            () -> new ArmorItem(ModArmorMaterials.MAGMATITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MAGMATITE_CHESTPLATE = ITEMS.register("magmatite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MAGMATITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MAGMATITE_LEGGINGS = ITEMS.register("magmatite_leggings",
            () -> new ArmorItem(ModArmorMaterials.MAGMATITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MAGMATITE_BOOTS = ITEMS.register("magmatite_boots",
            () -> new ArmorItem(ModArmorMaterials.MAGMATITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
