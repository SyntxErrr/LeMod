package screens;

import com.modding.lemod.LeModMain;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import menus.SteelFurnaceMenu;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SteelFurnaceScreen extends AbstractContainerScreen<SteelFurnaceMenu>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(LeModMain.MODID, "textures/gui/steel_furnace_gui.png");
	
	public SteelFurnaceScreen(SteelFurnaceMenu menu, Inventory playerInv, Component title) {
		super(menu, playerInv, title);
	}

	@Override
	protected void init() 
	{
		super.init();
		this.inventoryLabelY = 10000;
		this.titleLabelY = 10000;
	}
	
	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) 
	{
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		
		this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
		renderProgressArrow(poseStack, x, y);
	}
	
	private void renderProgressArrow(PoseStack poseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(poseStack, x + 80, y + 34, 177, 14, menu.getScaledProgress(), 18);
        }
    }
	
	@Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) 
	{
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
	
}
