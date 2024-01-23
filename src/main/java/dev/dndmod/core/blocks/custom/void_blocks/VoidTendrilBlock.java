package dev.dndmod.core.blocks.custom.void_blocks;

import dev.dndmod.core.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class VoidTendrilBlock extends DoublePlantBlock {

    public VoidTendrilBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos groundPos = pPos.below();
        BlockState groundState = pLevel.getBlockState(groundPos);

        return groundState.is(ModBlocks.VOID_GRASS.get()) || groundState.is(ModBlocks.VOID_DIRT.get());
    }
}
