package dev.dndmod.core.datagen;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.blocks.ModBlocks;
import dev.dndmod.core.blocks.custom.void_blocks.VoidPortalFrameBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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

        blockWithItem(ModBlocks.SMOOTH_END_STONE_BRICKS);

        blockWithActiveInactiveState(ModBlocks.VOID_PORTAL_FRAME, "void_portal_frame_side", "void_portal_frame", "void_portal_frame_front_active", "void_portal_frame_front");

//        DECORATION BLOCKS
        blockWithItem(ModBlocks.THATCH);

        simpleBlockWithItem(ModBlocks.VOID_TENDRIL.get(), models().cross(blockTexture(ModBlocks.VOID_TENDRIL.get()).getPath(),
                blockTexture(ModBlocks.VOID_TENDRIL.get())).renderType("cutout"));
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

    private void blockWithActiveInactiveState(RegistryObject<Block> blockRegistryObject,
                                              String sideTexture, String topBottomTexture,
                                              String frontActiveTexture, String frontInactiveTexture) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            boolean isActive = state.getValue(VoidPortalFrameBlock.ACTIVATED);
            Direction facing = state.getValue(VoidPortalFrameBlock.FACING);
            String frontTexture = isActive ? frontActiveTexture : frontInactiveTexture;
            String blockName = blockRegistryObject.getId().getPath();
            String modelSuffix = isActive ? "_active" : "_inactive";

            ModelFile blockModel = models().withExistingParent(blockName + modelSuffix, "block/cube")
                    .texture("particle", "dndmod:block/" + sideTexture)
                    .texture("down", "dndmod:block/" + topBottomTexture)
                    .texture("up", "dndmod:block/" + topBottomTexture)
                    .texture("north", "dndmod:block/" + frontTexture)
                    .texture("south", "dndmod:block/" + sideTexture)
                    .texture("east", "dndmod:block/" + sideTexture)
                    .texture("west", "dndmod:block/" + sideTexture);

            int yRotation = switch(facing) {
                case SOUTH -> 180;
                case WEST -> 270;
                case EAST -> 90;
                default -> 0;
            };

            itemModels().getBuilder(blockRegistryObject.getId().getPath()).parent(blockModel);

            return ConfiguredModel.builder().modelFile(blockModel).rotationY(yRotation).build();
        });
    }
}
