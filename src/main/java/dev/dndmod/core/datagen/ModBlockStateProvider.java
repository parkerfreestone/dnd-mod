package dev.dndmod.core.datagen;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DNDMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MAGNESIUM_ORE);
        blockWithItem(ModBlocks.MAGMATITE_ORE);

        blockWithItem(ModBlocks.GEO_REFINER);
//        simpleBlock(ModBlocks.GEO_REFINER.get(),
//                new ModelFile.UncheckedModelFile("block/geo_refiner"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
