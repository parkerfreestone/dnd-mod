package dev.dndmod.core.items;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DNDMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DND_TAB = CREATIVE_MODE_TABS.register("dnd_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ORC_TOOTH.get()))
                    .title(Component.translatable("creativetab.dndmod"))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.ORC_TOOTH.get());
                        output.accept(ModItems.ORC_SWORD.get());
                        output.accept(ModItems.MAGNESIUM_INGOT.get());
                        output.accept(ModItems.RAW_MAGNESIUM.get());

                        output.accept(ModBlocks.MAGNESIUM_ORE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
