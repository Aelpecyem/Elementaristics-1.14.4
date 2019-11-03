package de.aelpecyem.elementaristics.common.block.tile;

import de.aelpecyem.elementaristics.common.block.pantheon.ShrineBlock;
import de.aelpecyem.elementaristics.common.misc.pantheon.Deity;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;

import static de.aelpecyem.elementaristics.reg.ModBlocks.SHRINE;


public class ShrineTileEntity extends ModTileEntity implements ITickableTileEntity {
    public Deity deityBound;

    public ShrineTileEntity() {
        super(SHRINE);
    }


    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("deityBound", deityBound == null ? "" : deityBound.getName().toString());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        deityBound = Deity.DEITIES.get(new ResourceLocation(compound.getString("deityBound")));
        super.read(compound);
    }

    @Override
    public void tick() {
        if (deityBound != null) {
            if (world.isRemote) {
                deityBound.passiveParticles(this);
            }
        } else {
            Block block = world.getBlockState(pos).getBlock();
            if (block instanceof ShrineBlock) {
                deityBound = ((ShrineBlock) block).deityBound;
            }
        }
        //nothing yet, except being my bois
    }
}
