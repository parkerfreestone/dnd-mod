package dev.dndmod.core.datagen.loot;

import dev.dndmod.core.blocks.ModBlocks;
import dev.dndmod.core.items.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
//        THIS IS FOR NORMAL BLOCKS
//        this.dropSelf(ModBlocks.BLOCK_HERE.get());

        this.add(ModBlocks.MAGNESIUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.MAGNESIUM_ORE.get(), ModItems.RAW_MAGNESIUM.get(), 1.0f, 4.0f));

        this.add(ModBlocks.MAGMATITE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.MAGMATITE_ORE.get(), ModItems.UNCURED_MAGMATITE.get(), 1.0f, 3.0f));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item, float dropsFrom, float dropsTo) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(dropsFrom, dropsTo)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
