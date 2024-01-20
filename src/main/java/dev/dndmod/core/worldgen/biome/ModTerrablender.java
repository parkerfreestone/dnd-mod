package dev.dndmod.core.worldgen.biome;

import dev.dndmod.core.DNDMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(DNDMod.MOD_ID, "overworld"), 5));
    }
}
