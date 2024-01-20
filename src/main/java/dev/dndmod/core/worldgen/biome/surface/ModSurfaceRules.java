package dev.dndmod.core.worldgen.biome.surface;

import dev.dndmod.core.blocks.ModBlocks;
import dev.dndmod.core.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource VOID_DIRT = makeStateRule(ModBlocks.VOID_DIRT.get());
    private static final SurfaceRules.RuleSource VOID_GRASS_BLOCK = makeStateRule(ModBlocks.VOID_GRASS.get());
    private static final SurfaceRules.RuleSource VOID_ROCK = makeStateRule(ModBlocks.VOID_ROCK.get());

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource isAbove62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource isAbove63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);

//        SURFACE VOID BIOME SURFACE RULES
        SurfaceRules.RuleSource voidBiomeSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        isAtOrAboveWaterLevel,
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, VOID_GRASS_BLOCK)
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        VOID_DIRT
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.DEEP_UNDER_FLOOR,
                        VOID_ROCK
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.VERY_DEEP_UNDER_FLOOR,
                        VOID_ROCK
                )
        );

        SurfaceRules.RuleSource voidBiomeRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.SURFACE_VOID_BIOME),
                voidBiomeSurface
        );


        return SurfaceRules.sequence(
            voidBiomeRule
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
