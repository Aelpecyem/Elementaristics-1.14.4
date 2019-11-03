package de.aelpecyem.elementaristics.common.block.tile;

import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcess;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcessType;

import static de.aelpecyem.elementaristics.reg.ModBlocks.PURIFIER;

public class PurifierTileEntity extends AlchemyProcessingTileEntity {
    public PurifierTileEntity() {
        super(PURIFIER);
    }

    @Override
    public void tick() {
        System.out.println(getInventory().getStackInSlot(0));
        super.tick();
    }

    @Override
    public AspectProcess addProcessPart() {
        return new AspectProcess(AspectProcessType.PURIFY);
    }

    @Override
    public boolean isWorking() {
        return getInventory().getStackInSlot(0).getItem() instanceof AlchemicalMatterItem && getInventory().getStackInSlot(1).isEmpty(); //or if it is a purifier recipe, but that will come later
    }

    @Override
    public void finish() {
        getInventory().insertItem(1, getInventory().extractItem(0, 1, false), false);
        super.finish();
    }

    @Override
    public boolean isFinished() {
        return ticks >= 100;
    }
}
