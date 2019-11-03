package de.aelpecyem.elementaristics.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.client.model.ModelBase;
import de.aelpecyem.elementaristics.common.block.pantheon.ShrineBlock;
import de.aelpecyem.elementaristics.common.block.tile.ShrineTileEntity;
import de.aelpecyem.elementaristics.common.misc.pantheon.Deity;
import de.aelpecyem.elementaristics.proxy.ClientProxy;
import de.aelpecyem.elementaristics.reg.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class ShrineRenderer extends TileEntityRenderer<ShrineTileEntity> {
    private static Deity deity = ModRegistries.WITCH;

    @Override
    public void render(ShrineTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        ModelBase model = ClientProxy.DEITY_MODEL_MAP.get(tileEntityIn != null ? tileEntityIn.deityBound : deity);
        ResourceLocation texture = ClientProxy.DEITY_TEXTURE_MAP.get(tileEntityIn != null ? tileEntityIn.deityBound : deity);
        if (texture == null || model == null) return;
        GlStateManager.pushMatrix();
        GlStateManager.translated(x + 0.5, y + 1.5, z + 0.5);
        GlStateManager.rotated(180, 0, 0, 1);
        bindTexture(texture);
        if (tileEntityIn != null) {
            Direction face = tileEntityIn.getWorld().getBlockState(tileEntityIn.getPos()).get(BlockStateProperties.FACING);
            GlStateManager.rotated(face == Direction.WEST ? 270 : face == Direction.EAST ? 90 : face == Direction.SOUTH ? 180 : 0, 0, 1, 0);
        }
        model.render(0.0625f);
        GlStateManager.popMatrix();
    }

    public static class ForwardingTEISR extends ItemStackTileEntityRenderer {
        @Override
        public void renderByItem(ItemStack itemStack) {
            if (Block.getBlockFromItem(itemStack.getItem()) instanceof ShrineBlock) {
                ShrineRenderer.deity = ((ShrineBlock) Block.getBlockFromItem(itemStack.getItem())).deityBound;
                TileEntityRenderer r = TileEntityRendererDispatcher.instance.getRenderer(ShrineTileEntity.class);
                r.render(null, 0, 0, 0, 0, -69);
            }
        }
    }
}
