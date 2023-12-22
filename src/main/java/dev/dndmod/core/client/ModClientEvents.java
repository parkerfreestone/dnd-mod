package dev.dndmod.core.client;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.screen.ModInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModClientEvents {
    @Mod.EventBusSubscriber(modid = DNDMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;
            if (player != null) {
                if (mc.options.keyInventory.consumeClick()) {
                    mc.setScreen(new ModInventoryScreen(player.inventoryMenu, player.getInventory(), Component.translatable("container.inventory")));
                }
            }
        }
    }

    // INSERT CLIENTBUSEVENTS CLASS HERE
}