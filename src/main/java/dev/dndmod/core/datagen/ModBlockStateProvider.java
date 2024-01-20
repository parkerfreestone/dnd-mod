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
//        ORES
        blockWithItem(ModBlocks.MAGNESIUM_ORE);
        blockWithItem(ModBlocks.MAGMATITE_ORE);

        blockWithItem(ModBlocks.GEO_REFINER);
//        simpleBlock(ModBlocks.GEO_REFINER.get(),
//                new ModelFile.UncheckedModelFile("block/geo_refiner"));


//        VOID BLOCKS
        blockWithMultiTexture(ModBlocks.VOID_GRASS,
                "void_grass_top",
                "void_dirt",
                "void_grass"
                );
        blockWithItem(ModBlocks.VOID_DIRT);
        blockWithItem(ModBlocks.VOID_ROCK);
        blockWithItem(ModBlocks.VOID_COBBLED_ROCK);
        blockWithItem(ModBlocks.VOID_PORTAL_BLOCK);

//        DECORATION BLOCKS
        blockWithItem(ModBlocks.THATCH);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockWithMultiTexture(RegistryObject<Block> block,
                                       String topTexture, String bottomTexture, String sideTexture) {
        String modId = DNDMod.MOD_ID;

        ModelFile blockModel = models().withExistingParent(block.getId().getPath(), "block/cube")
                .texture("particle", modId + ":block/" + sideTexture)
                .texture("down", modId + ":block/" + bottomTexture)
                .texture("up", modId + ":block/" + topTexture)
                .texture("north", modId + ":block/" + sideTexture)
                .texture("south", modId + ":block/" + sideTexture)
                .texture("east", modId + ":block/" + sideTexture)
                .texture("west", modId + ":block/" + sideTexture);

        simpleBlock(block.get(), blockModel);
        itemModels().withExistingParent(block.getId().getPath(), modLoc("block/" + block.getId().getPath()));
    }
}
