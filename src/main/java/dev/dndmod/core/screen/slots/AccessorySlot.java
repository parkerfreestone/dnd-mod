package dev.dndmod.core.screen.slots;

import dev.dndmod.core.utils.ModTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AccessorySlot extends Slot {
    public AccessorySlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.is(ModTags.Items.IS_PLAYER_ACCESSORY);
    }
}
