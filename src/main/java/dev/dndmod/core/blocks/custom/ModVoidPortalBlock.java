package dev.dndmod.core.blocks.custom;

import dev.dndmod.core.worldgen.portal.ModTeleporter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.rmi.registry.Registry;

public class ModVoidPortalBlock extends Block {
    public ModVoidPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    private void handleTeleportation(Entity entity) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            MinecraftServer minecraftServer = serverLevel.getServer();
//            ResourceKey<Level> voidDimensionKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("dndmod", "<path>"));

//            ServerLevel voidDimensionLevel = minecraftServer.getLevel(voidDimensionKey);
//            if (voidDimensionLevel != null && !entity.isPassenger()) {
//                entity.changeDimension(voidDimensionLevel, new ModTeleporter(entity.blockPosition(), false));
            }
//        }
    }
}
