package de.aelpecyem.elementaristics.common.block;

import de.aelpecyem.elementaristics.common.block.base.TileEntityFacingBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.PurifierTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class PurifierBlock extends TileEntityFacingBaseBlock {
    public PurifierBlock() {
        super("purifier", Properties.create(Material.ROCK, DyeColor.GRAY));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PurifierTileEntity();
    }
}
