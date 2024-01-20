package dev.dndmod.core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.dndmod.core.DNDMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import net.minecraft.world.level.levelgen.structure.structures.EndCityStructure;

public class ModInventoryScreen extends InventoryScreen {
    private boolean lastRecipeBookOpenState;
    private ImageButton questButton;

    private float xMouse;
    private float yMouse;

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
        int j = this.topPos;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        int entityRenderWidth = 100;
        int entityRenderHeight = 100;
        int entityX = x + 51;
        int entityY = j + 75;

        int entityCenterX = entityX + entityRenderWidth / 2;
        int entityCenterY = entityY + entityRenderHeight / 2;

        if (this.minecraft != null) {
            if (this.minecraft.player != null) {
                renderEntityInInventoryFollowsMouse(pGuiGraphics, entityX, entityY, 30, (float) entityCenterX - xMouse, (float)entityCenterY - yMouse, this.minecraft.player);
            }
        }
    }

    @Override
    protected void init() {
        super.init();

        boolean isRecipeBookOpen = this.getRecipeBookComponent().isVisible();
        if (isRecipeBookOpen) {
            this.getRecipeBookComponent().toggleVisibility();
        }

        lastRecipeBookOpenState = this.getRecipeBookComponent().isVisible();

        questButton = new ImageButton(this.leftPos + 128, this.height / 2 - 22, 20, 18, 200, 0, 19, TEXTURE, (button) -> {
            Minecraft mc = this.minecraft;
            LocalPlayer player = mc.player;

            if (player != null) {
                Inventory playerInventory = player.getInventory();
                Component title = Component.literal("Quest Book");

                QuestMenu questMenu = new QuestMenu(MenuType.GENERIC_9x3, player.containerMenu.containerId);

                QuestScreen questScreen = new QuestScreen(questMenu, playerInventory, title);

                Minecraft.getInstance().setScreen(questScreen);

                this.onClose();
            }
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

        this.xMouse = (float)pMouseX;
        this.yMouse = (float)pMouseY;
    }

    @Override
    public void onClose() {
        if (this.getRecipeBookComponent().isVisible()) {
            this.getRecipeBookComponent().toggleVisibility();
        }

        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    public static void renderEntityInInventoryFollowsMouse(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, float pMouseX, float pMouseY, LivingEntity pEntity) {
        float f = (float)Math.atan((pMouseX / 40.0F));
        float f1 = (float)Math.atan((pMouseY / 40.0F));

        renderEntityInInventoryFollowsAngle(pGuiGraphics, pX, pY, pScale, f, f1, pEntity);
    }

    private int getDefaultQuestButtonXPos() {
        return this.leftPos + 128;
    }
}
