package dev.dndmod.core.datagen;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.items.ModItems;
import dev.dndmod.core.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, DNDMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.MAGMATITE_HELMET.get(),
                        ModItems.MAGMATITE_CHESTPLATE.get(),
                        ModItems.MAGMATITE_LEGGINGS.get(),
                        ModItems.MAGMATITE_BOOTS.get());

        this.tag(ModTags.Items.IS_PLAYER_ACCESSORY)
                .add(ModItems.UNCURED_MAGMATITE.get());

//        this.tag(ModTags.Items.)
    }
}
