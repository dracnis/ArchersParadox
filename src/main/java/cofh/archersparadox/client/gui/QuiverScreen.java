package cofh.archersparadox.client.gui;

import cofh.archersparadox.inventory.container.QuiverContainer;
import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.util.helpers.RenderHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.createSlot;
import static cofh.core.util.helpers.GuiHelper.generatePanelInfo;
import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class QuiverScreen extends ContainerScreenCoFH<QuiverContainer> {

    public static final String TEX_PATH = ID_COFH_CORE + ":textures/gui/generic.png";
    public static final String TEX_PATH_EXT = ID_COFH_CORE + ":textures/gui/generic_extension.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);
    public static final ResourceLocation TEXTURE_EXT = new ResourceLocation(TEX_PATH_EXT);

    protected int renderExtension;

    public QuiverScreen(QuiverContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, titleIn);

        texture = TEXTURE;
        info = generatePanelInfo("info.archers_paradox.quiver");

        renderExtension = container.getExtraRows() * 18;
        imageHeight += renderExtension;
    }

    @Override
    public void init() {

        super.init();

        for (int i = 0; i < menu.getContainerInventorySize(); ++i) {
            Slot slot = menu.slots.get(i);
            addElement(createSlot(this, slot.x, slot.y));
        }
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {

        RenderHelper.resetColor();
        RenderHelper.bindTexture(texture);

        drawTexturedModalRect(leftPos, topPos, 0, 0, imageWidth, imageHeight);
        if (renderExtension > 0) {
            RenderHelper.bindTexture(TEXTURE_EXT);
            drawTexturedModalRect(leftPos, topPos + renderExtension, 0, 0, imageWidth, imageHeight);
        }
        RenderSystem.pushMatrix();
        RenderSystem.translatef(leftPos, topPos, 0.0F);

        drawPanels(matrixStack, false);
        drawElements(matrixStack, false);

        RenderSystem.popMatrix();
    }

}
