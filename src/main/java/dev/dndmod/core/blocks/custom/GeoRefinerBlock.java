package dev.dndmod.core.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GeoRefinerBlock extends BaseEntityBlock {

    public GeoRefinerBlock(Properties pProperties) {
        super(pProperties);
    }


    // THIS NEEDS TO BE CHANGED SILLY GUY
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}
