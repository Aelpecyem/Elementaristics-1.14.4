package de.aelpecyem.elementaristics.common.block.tile;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.particle.GlowParticle;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleModes;
import de.aelpecyem.elementaristics.client.render.BoilingBasinRenderer;
import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcess;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcessType;
import de.aelpecyem.elementaristics.util.ColorUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

import static de.aelpecyem.elementaristics.reg.ModBlocks.PURIFIER;

public class BoilingBasingTileEntity extends AlchemyProcessingTileEntity {
    public static final int TICKS_REQUIRED = 500;

    public BoilingBasingTileEntity() {
        super(PURIFIER);
    }
    /*this will be merged with the Basin
      for this, an additional part with added aspects will be added; aspects will only be addable when the basin is filled,
      either with essence etc. or some sort of fluid...
    */

    @Override
    public AspectProcess addProcessPart() {
        return new AspectProcess(AspectProcessType.PURIFY);
    }

    @Override
    public int getTargetColor(int previousColor) {
        return ColorUtil.toDecimal(ColorUtil.blend(new Color(12700898), new Color(previousColor), 0.4F, 0.6F));//ColorUtil.blend(new Color(47615), new Color(previousColor), 0.3F, 0.7F).getRGB(); //merge it to be brighter/whiter
    }

    @Override
    public boolean isWorking() {
        return isLit() && isValidItem(getInventory().getStackInSlot(0)) && getInventory().getStackInSlot(0).getCount() >= 3 && getInventory().getStackInSlot(1).isEmpty(); //or if it is a boiling_basin recipe, but that will come later
    }

    public boolean isLit() {
        return world.getBlockState(pos).get(BlockStateProperties.LIT);
    }
    @Override
    public void finish() {
        getInventory().insertItem(1, getInventory().extractItem(0, 3, false), false);
        super.finish();
    }

    @Override
    public void increaseTicks() {
        if (world.isRemote && world.rand.nextBoolean()) {
            doSteamParticles();
        }
        super.increaseTicks();
    }

    @Override
    public boolean isFinished() {
        return ticks >= TICKS_REQUIRED;
    }

    public boolean isValidItem(ItemStack stack) {
        return stack.getItem() instanceof AlchemicalMatterItem || stack.getItem() instanceof EssenceItem;
    }

    @OnlyIn(Dist.CLIENT)
    public void doSteamParticles() {
        GlowParticle particle = new GlowParticle(world, pos.getX() + 0.25F + world.rand.nextFloat() / 2F, pos.getY() + 0.6F + world.rand.nextFloat() / 10F, pos.getZ() + 0.25F + world.rand.nextFloat() / 2F, 0, world.rand.nextFloat() / 100F, 0, 120, BoilingBasinRenderer.getFluidColor(this, getInventory(), getTicks() / (float) TICKS_REQUIRED).getRGB(), 0.9F, 0.05F + world.rand.nextFloat() / 10F, 0, true, true, GlowParticle.EnumFadeMode.OUT);
        particle.setMode(ParticleModes.STEAM);
        Elementaristics.particles.spawnParticle(particle);
        //add custom particle mode: steam --- switch to white-blueish thing and have a variation and uhhh probably something with alpha
    }
}
