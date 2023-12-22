package dev.dndmod.core.screen.buttons;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class QuestsButton extends Button {
    private final ResourceLocation texture;

    public QuestsButton(int x, int y, int width, int height, Component message, OnPress onPress, CreateNarration createNarration, ResourceLocation texture) {
        super(x, y, width, height, message, onPress, createNarration);
        this.texture = texture;
    }

    @Override
    public void renderTexture(GuiGraphics pGuiGraphics, ResourceLocation pTexture, int pX, int pY, int pUOffset, int pVOffset, int pTextureDifference, int pWidth, int pHeight, int pTextureWidth, int pTextureHeight) {
//        super.renderTexture(pGuiGraphics, pTexture, pX, pY, pUOffset, pVOffset, pTextureDifference, pWidth, pHeight, pTextureWidth, pTextureHeight);
//        pGuiGraphics.blit(TEXTURE,  width, height);
    }
}