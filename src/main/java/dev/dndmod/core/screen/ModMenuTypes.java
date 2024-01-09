package dev.dndmod.core.screen;

import dev.dndmod.core.DNDMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, DNDMod.MOD_ID);

    public static final RegistryObject<MenuType<ModInventoryMenu>> MOD_INVENTORY_MENU =
            MENUS.register("mod_inventory_menu",
                    () -> IForgeMenuType.create((windowId, inv, data) -> new ModInventoryMenu(inv, true, inv.player)));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}