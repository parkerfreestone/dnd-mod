package dev.dndmod.core.blocks;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.blocks.custom.GeoRefinerBlock;
import dev.dndmod.core.blocks.custom.ModVoidPortalBlock;
import dev.dndmod.core.blocks.custom.void_blocks.VoidGrassBlock;
import dev.dndmod.core.blocks.custom.void_blocks.VoidPortalFrameBlock;
import dev.dndmod.core.blocks.custom.void_blocks.VoidTendrilBlock;
import dev.dndmod.core.items.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DNDMod.MOD_ID);

    public static final RegistryObject<Block> MAGNESIUM_ORE = registerBlock("magnesium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> MAGMATITE_ORE = registerBlock("magmatite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> GEO_REFINER = registerBlock("geo_refiner",
            ()-> new GeoRefinerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));


//    VOID BLOCKS
    public static final RegistryObject<Block> VOID_GRASS = registerBlock("void_grass",
            ()-> new VoidGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> VOID_DIRT = registerBlock("void_dirt",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> VOID_ROCK = registerBlock("void_rock",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> VOID_COBBLED_ROCK = registerBlock("void_cobbled_rock",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLED_DEEPSLATE)));
    public static final RegistryObject<Block> VOID_PORTAL_BLOCK = registerBlock("void_portal_block",
            ()-> new ModVoidPortalBlock(BlockBehaviour.Properties.copy(Blocks.END_PORTAL).noLootTable().noOcclusion().noCollission()));
    public static final RegistryObject<Block> VOID_TENDRIL = registerBlock("void_tendril",
            ()-> new VoidTendrilBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion().noCollission()));
    public static final RegistryObject<Block> VOID_PORTAL_FRAME = registerBlock("void_portal_frame",
            ()-> new VoidPortalFrameBlock(BlockBehaviour.Properties.copy(Blocks.END_PORTAL_FRAME)));

    public static final RegistryObject<Block> SMOOTH_END_STONE_BRICKS = registerBlock("smooth_end_stone_bricks",
            ()-> new VoidPortalFrameBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));



//    DECORATION_BLOCKS
    public static final RegistryObject<Block> THATCH = registerBlock("thatch",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
