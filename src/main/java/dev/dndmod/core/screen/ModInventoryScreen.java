package dev.dndmod.core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.dndmod.core.DNDMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ModInventoryScreen extends InventoryScreen {
    private boolean lastRecipeBookOpenState;
    private ImageButton questButton;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DNDMod.MOD_ID, "textures/gui/custom_inventory.png");

    public ModInventoryScreen(AbstractContainerMenu menu, Inventory inv, Component title) {
        super(inv.player);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = this.leftPos;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void init() {
        super.init();

        lastRecipeBookOpenState = this.getRecipeBookComponent().isVisible();

        questButton = new ImageButton(this.leftPos + 128, this.height / 2 - 22, 20, 18, 200, 0, 19, TEXTURE, (button) -> {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Quest Book Opened!"));
        });

        this.addRenderableWidget(questButton);

        if (this.getRecipeBookComponent().isVisible()) {
            questButton.setX(this.leftPos + (width - imageWidth - 200) / 2);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        boolean currentRecipeBookState = this.getRecipeBookComponent().isVisible();
        if (currentRecipeBookState != lastRecipeBookOpenState) {
            lastRecipeBookOpenState = currentRecipeBookState;

            questButton.setX(getDefaultQuestButtonXPos());
        }
    }

    @Override
    public void onClose() {
        if (this.getRecipeBookComponent().isVisible()) {
            this.getRecipeBookComponent().toggleVisibility();
        }

        super.onClose();
    }

    private int getDefaultQuestButtonXPos() {
        return this.leftPos + 128;
    }
}
