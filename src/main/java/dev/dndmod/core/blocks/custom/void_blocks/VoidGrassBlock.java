package dev.dndmod.core.blocks.custom.void_blocks;

import dev.dndmod.core.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;

public class VoidGrassBlock extends GrassBlock {
    public VoidGrassBlock(Properties p_53685_) {
        super(p_53685_);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeVoidGrass(pState, pLevel, pPos)) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.VOID_GRASS.get().defaultBlockState());
        } else {
            for (int i = 0; i < 4; i++) {
                BlockPos targetPos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                BlockState targetState = pLevel.getBlockState(targetPos);

                if (targetState.is(ModBlocks.VOID_DIRT.get()) && canPropogate(pState, pLevel, targetPos)) {
                    pLevel.setBlockAndUpdate(targetPos, defaultBlockState());
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return plantable.getPlant(world, pos.relative(facing)).getBlock() == ModBlocks.VOID_TENDRIL.get();
    }

    private boolean canBeVoidGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = levelReader.getBlockState(abovePos);

        int light = LightEngine.getLightBlockInto(levelReader, state, pos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(levelReader, abovePos));
        boolean isDarkEnough = light >= levelReader.getMaxLightLevel() - 2;

        return isDarkEnough;
    }

    private boolean canPropogate(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        return canBeVoidGrass(state, level, pos) && !level.getFluidState(abovePos).is(FluidTags.WATER);

    }
}
