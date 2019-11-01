package de.aelpecyem.elementaristics.common.block.tile;

import static de.aelpecyem.elementaristics.reg.ModBlocks.PURIFIER;

public class PurifierTileEntity extends AlchemyProcessingTileEntity {
    public PurifierTileEntity() {
        super(PURIFIER);
    }

    @Override
    public boolean isWorking() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
