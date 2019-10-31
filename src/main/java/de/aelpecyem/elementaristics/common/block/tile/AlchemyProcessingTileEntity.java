package de.aelpecyem.elementaristics.common.block.tile;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class AlchemyProcessingTileEntity extends ModTileEntity implements ITickableTileEntity {
    public AlchemyProcessingTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {

    }
}
