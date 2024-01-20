package dev.dndmod.core;

import com.mojang.logging.LogUtils;
import dev.dndmod.core.blocks.ModBlocks;
import dev.dndmod.core.entity.ModEntities;
import dev.dndmod.core.entity.client.BombProjectileRenderer;
import dev.dndmod.core.entity.client.OrcRenderer;
import dev.dndmod.core.entity.events.ModEntityDropHandler;
import dev.dndmod.core.items.ModCreativeModTabs;
import dev.dndmod.core.items.ModItems;
import dev.dndmod.core.screen.ModInventoryScreen;
import dev.dndmod.core.screen.ModMenuTypes;
import dev.dndmod.core.worldgen.biome.ModBiomes;
import dev.dndmod.core.worldgen.biome.ModOverworldRegion;
import dev.dndmod.core.worldgen.biome.ModTerrablender;
import dev.dndmod.core.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import terrablender.api.Region;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DNDMod.MOD_ID)
public class DNDMod
{
    public static final String MOD_ID = "dndmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DNDMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
//            ModTerrablender.registerBiomes();

//            Regions.register(new ModOverworldRegion(new ResourceLocation(MOD_ID, "overworld"), 20));

//            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());

            ModEntityDropHandler.setupDropConfigs();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.ORC_TOOTH_SWORD);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) { }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.ORC.get(), OrcRenderer::new);

            EntityRenderers.register(ModEntities.BOMB_PROJECTILE.get(), BombProjectileRenderer::new);

            MenuScreens.register(ModMenuTypes.MOD_INVENTORY_MENU.get(), ModInventoryScreen::new);
        }
    }
}
