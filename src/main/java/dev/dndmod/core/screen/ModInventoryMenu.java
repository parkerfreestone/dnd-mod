package dev.dndmod.core.screen;

import dev.dndmod.core.screen.slots.AccessorySlot;
import dev.dndmod.core.screen.slots.BackpackSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;

public class ModInventoryMenu extends InventoryMenu {
    private static final int ACCESSORY_SLOT_X = 77;
    private static final int ACCESSORY_SLOT_Y = 58;
    private static final int BACKPACK_SLOT_X = 77;
    private static final int BACKPACK_SLOT_Y = 40;

    public ModInventoryMenu(Inventory pPlayerInventory, boolean pActive, Player pOwner) {
        super(pPlayerInventory, pActive, pOwner);

        Container playerContainer = pPlayerInventory.player.getInventory();

        this.addSlot(new AccessorySlot(playerContainer, 98, ACCESSORY_SLOT_X, ACCESSORY_SLOT_Y));

        this.addSlot(new BackpackSlot(playerContainer, 99, BACKPACK_SLOT_X, BACKPACK_SLOT_Y));
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

//    @Override
//    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
//        return super.quickMoveStack(pPlayer, pIndex);
//    }
}
