package dev.dndmod.core.entity.events;

import net.minecraft.world.item.Item;

public class ModDropConfig {
    private final Item item;
    private final int minAmount;
    private final int maxAmount;

    public ModDropConfig(Item item, int minAmount, int maxAmount) {
        this.item = item;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public Item getItem() {
        return item;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }
}
