package dev.dndmod.core.items;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.utils.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier ORC_TOOTH = TierSortingRegistry.registerTier(
            new ForgeTier(5, 190, 7f, 2.7f, 14,
                    ModTags.Blocks.NEEDS_ORC_TOOTH_TOOL, () -> Ingredient.of(ModItems.ORC_TOOTH.get())),
            new ResourceLocation(DNDMod.MOD_ID, "orc_tooth"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND)
    );
}
