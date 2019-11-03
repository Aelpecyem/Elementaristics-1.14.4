package de.aelpecyem.elementaristics.common.block.alchemy;

import de.aelpecyem.elementaristics.common.block.base.TileEntityFacingBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.PurifierTileEntity;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class PurifierBlock extends TileEntityFacingBaseBlock {
    public PurifierBlock() {
        super("purifier", Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    }


    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile != null) {
                IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, hit.getFace()).orElse(null);
                if (itemHandler != null) {
                    return InventoryUtil.interactWithTileInventory(tile, itemHandler, player, handIn, player.isSneaking() ? 64 : 1);
                }
                PacketHandler.Util.updateTE(tile);
            }
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PurifierTileEntity();
    }
}
