package de.aelpecyem.elementaristics.client.render.tile;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.client.model.tile.ModelBoilingBasin;
import de.aelpecyem.elementaristics.common.block.tile.BoilingBasinTileEntity;
import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.util.ColorUtil;
import de.aelpecyem.elementaristics.util.InventoryUtil;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraftforge.items.IItemHandler;

import java.awt.*;

public class BoilingBasinRenderer extends TileEntityRenderer<BoilingBasinTileEntity> {
    public static ModelBoilingBasin MODEL = new ModelBoilingBasin();

    //TODO fix the model and render stuff for this basin, but not now lol
    @Override
    public void render(BoilingBasinTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        /*if (shouldRenderFluid(tile, tile.getInventory())) {
            float progress = tile.getTicks() / (float) BoilingBasingTileEntity.TICKS_REQUIRED;
            // float progress = tile.getTicks() / (float) BoilingBasingTileEntity.TICKS_REQUIRED;
            float fluidHeight = 0.06F + (tile.getInventory().getStackInSlot(0).getCount() >= 3 ? (0.083F * tile.getInventory().getStackInSlot(0).getCount() * (1 - progress)) : 0); //later use a special texture uwu
            Color color = getFluidColor(tile, tile.getInventory(), progress);
            RenderUtil.translateAgainstPlayer(tile.getPos(), false);
            //Minecraft.getInstance().getTextureMap().getSprite(ClientProxy.THICC_WATER);//use this later
            RenderUtil.renderCube(Minecraft.getInstance().getTextureMap().getSprite(ClientProxy.THICC_WATER), 0, 0.3125d, 0, 0.25d, 0, 0.25d, 0.75d, fluidHeight, 0.75d, color != null ? color.getRGB() : 148586, Fluids.WATER.getAttributes().getLuminosity());
        }*/

        //do render for that later :(
        GlStateManager.pushMatrix();
        GlStateManager.translated(x + 0.5, y + 1.5, z + 0.5);
        GlStateManager.rotated(180, 0, 0, 1);
        MODEL.render(0.0625f);
        GlStateManager.popMatrix();
    }

    private boolean shouldRenderFluid(BoilingBasinTileEntity tile, IItemHandler inventory) {
        return getFluidColor(tile, inventory, 0) != null;
    }

    public static Color getFluidColor(BoilingBasinTileEntity tile, IItemHandler inventory, float progress) {
        int i = InventoryUtil.slotForCheck(inventory, slot -> tile.isValidItem(inventory.getStackInSlot(slot)));
        if (i > -1) {
            if (tile.isWorking()) {
                return ColorUtil.blend(new Color(tile.getOriginalColor()), new Color(tile.getTargetColor(tile.getOriginalColor())), 1 - progress, progress);
            }
            if (inventory.getStackInSlot(i).getItem() instanceof AlchemicalMatterItem) {
                return new Color(AlchemicalMatterItem.getColor(inventory.getStackInSlot(i)));
            } else if (inventory.getStackInSlot(i).getItem() instanceof EssenceItem) {
                return new Color(EssenceItem.getAspect(inventory.getStackInSlot(i)).getColor());
            }
        }
        return null;
    }
}
