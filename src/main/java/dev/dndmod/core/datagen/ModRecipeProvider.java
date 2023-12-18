package dev.dndmod.core.datagen;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.blocks.ModBlocks;
import dev.dndmod.core.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> MAGNESIUM_SMELTABLES = List.of(
            ModItems.RAW_MAGNESIUM.get(),
            ModBlocks.MAGNESIUM_ORE.get()
    );
    private static final List<ItemLike> MAGMATITE_SMELTABLES = List.of(
            ModItems.UNCURED_MAGMATITE.get(),
            ModBlocks.MAGMATITE_ORE.get()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, MAGNESIUM_SMELTABLES, RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 0.13f, 175, "magnesium");
        oreBlasting(consumer, MAGNESIUM_SMELTABLES, RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 0.13f, 75, "magnesium");

        oreSmelting(consumer, MAGMATITE_SMELTABLES, RecipeCategory.MISC, ModItems.CURED_MAGMATITE.get(), 0.2f, 200, "magmatite");
        oreBlasting(consumer, MAGMATITE_SMELTABLES, RecipeCategory.MISC, ModItems.CURED_MAGMATITE.get(), 0.2f, 100, "magmatite");

        // ORC TOOTH TOOLSET RECIPES
        // SWORD
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORC_TOOTH_SWORD.get())
                .pattern(" T ")
                .pattern(" T ")
                .pattern(" S ")
                .define('T', ModItems.ORC_TOOTH.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ORC_TOOTH.get()), has(ModItems.ORC_TOOTH.get()))
                .save(consumer);

        // PICKAXE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORC_TOOTH_PICKAXE.get())
                .pattern("TTT")
                .pattern(" S ")
                .pattern(" S ")
                .define('T', ModItems.ORC_TOOTH.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ORC_TOOTH.get()), has(ModItems.ORC_TOOTH.get()))
                .save(consumer);

        // AXE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORC_TOOTH_AXE.get())
                .pattern(" TT")
                .pattern(" ST")
                .pattern(" S ")
                .define('T', ModItems.ORC_TOOTH.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ORC_TOOTH.get()), has(ModItems.ORC_TOOTH.get()))
                .save(consumer);

        // SHOVEL
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORC_TOOTH_SHOVEL.get())
                .pattern(" T ")
                .pattern(" S ")
                .pattern(" S ")
                .define('T', ModItems.ORC_TOOTH.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ORC_TOOTH.get()), has(ModItems.ORC_TOOTH.get()))
                .save(consumer);

        // HOE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORC_TOOTH_HOE.get())
                .pattern(" TT")
                .pattern(" S ")
                .pattern(" S ")
                .define('T', ModItems.ORC_TOOTH.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ORC_TOOTH.get()), has(ModItems.ORC_TOOTH.get()))
                .save(consumer);

        // SHIELD
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORC_TOOTH_SHIELD.get())
                .pattern("TIT")
                .pattern("TLT")
                .pattern(" T ")
                .define('T', ModItems.ORC_TOOTH.get())
                .define('I', Items.IRON_INGOT)
                .define('L', Items.LEATHER)
                .unlockedBy(getHasName(ModItems.ORC_TOOTH.get()), has(ModItems.ORC_TOOTH.get()))
                .save(consumer);

        // MAGMATITE ARMOR RECIPIES
        // MAGMATITE CHESTPLATE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGMATITE_HELMET.get())
                .pattern("MMM")
                .pattern("M M")
                .define('M', ModItems.UNCURED_MAGMATITE.get())
                .unlockedBy(getHasName(ModItems.UNCURED_MAGMATITE.get()), has(ModItems.UNCURED_MAGMATITE.get()))
                .save(consumer);

        // MAGMATITE CHESTPLATE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGMATITE_CHESTPLATE.get())
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.UNCURED_MAGMATITE.get())
                .unlockedBy(getHasName(ModItems.UNCURED_MAGMATITE.get()), has(ModItems.UNCURED_MAGMATITE.get()))
                .save(consumer);

        // MAGMATITE LEGGINGS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGMATITE_LEGGINGS.get())
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', ModItems.UNCURED_MAGMATITE.get())
                .unlockedBy(getHasName(ModItems.UNCURED_MAGMATITE.get()), has(ModItems.UNCURED_MAGMATITE.get()))
                .save(consumer);

        // MAGMATITE BOOTS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGMATITE_BOOTS.get())
                .pattern("M M")
                .pattern("M M")
                .define('M', ModItems.UNCURED_MAGMATITE.get())
                .unlockedBy(getHasName(ModItems.UNCURED_MAGMATITE.get()), has(ModItems.UNCURED_MAGMATITE.get()))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, DNDMod.MOD_ID + ":" +getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
