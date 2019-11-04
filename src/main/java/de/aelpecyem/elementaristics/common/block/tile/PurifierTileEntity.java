package de.aelpecyem.elementaristics.common.block.tile;

import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcess;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcessType;
import de.aelpecyem.elementaristics.util.ColorUtil;
import net.minecraft.state.properties.BlockStateProperties;

import java.awt.*;

import static de.aelpecyem.elementaristics.reg.ModBlocks.PURIFIER;

public class PurifierTileEntity extends AlchemyProcessingTileEntity {
    public PurifierTileEntity() {
        super(PURIFIER);
    }
    //this will be merged with the Basin

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public AspectProcess addProcessPart() {
        return new AspectProcess(AspectProcessType.PURIFY);
    }

    @Override
    public int getTargetColor(int previousColor) {
        System.out.println(new Color(12700898).getRGB());
        return ColorUtil.blend(new Color(12700898), new Color(previousColor), 0.3F, 0.7F).getRGB();//ColorUtil.blend(new Color(47615), new Color(previousColor), 0.3F, 0.7F).getRGB(); //merge it to be brighter/whiter
    }

    @Override
    public boolean isWorking() {
        return isLit() && (getInventory().getStackInSlot(0).getItem() instanceof AlchemicalMatterItem || getInventory().getStackInSlot(0).getItem() instanceof EssenceItem) && getInventory().getStackInSlot(1).isEmpty(); //or if it is a purifier recipe, but that will come later
    }

    public boolean isLit() {
        return world.getBlockState(pos).get(BlockStateProperties.LIT);
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
