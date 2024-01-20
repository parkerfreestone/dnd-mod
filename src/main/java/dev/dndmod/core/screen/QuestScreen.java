package dev.dndmod.core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.dndmod.core.DNDMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class QuestScreen extends AbstractContainerScreen<QuestMenu> {
    private final ResourceLocation TEXTURE =
            new ResourceLocation(DNDMod.MOD_ID, "textures/gui/quest_binder.png");

    public QuestScreen(QuestMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
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
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
