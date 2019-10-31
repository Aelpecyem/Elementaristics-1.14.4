package de.aelpecyem.elementaristics.common.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public abstract class TileEntityFacingBaseBlock extends HorizontalFacingBaseBlock {
    public TileEntityFacingBaseBlock(String name, Properties properties) {
        super(name, properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);
}
