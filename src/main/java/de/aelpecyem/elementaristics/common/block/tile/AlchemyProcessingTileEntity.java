package de.aelpecyem.elementaristics.common.block.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AlchemyProcessingTileEntity extends ModTileEntity implements ITickableTileEntity {
    public AlchemyProcessingTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    protected int ticks;
    protected LazyOptional<IItemHandler> inventory = LazyOptional.of(this::createHandler);

    protected IItemHandler createHandler() {
        return new ItemStackHandler(1);
    }


    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("ticks", ticks);
        inventory.ifPresent(h -> compound.put("inventory", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        ticks = compound.getInt("ticks");
        inventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(compound.getCompound("inventory")));
        super.read(compound);
    }

    @Override
    public void tick() {
        if (isWorking()) {
            increaseTicks();
            if (isFinished()) {
                finish();
            }
        } else {
            resetProgress();
        }
    }

    /**
     * Use this method to calculate if the apparatus in question should increase its working progress
     *
     * @return whether the apparatus should continue work
     */
    public abstract boolean isWorking();

    public void increaseTicks() {
        ticks++;
    }

    public void resetProgress() {
        ticks = 0;
    }

    public abstract boolean isFinished();

    public void finish() {
        resetProgress();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public IItemHandler getInventory() {
        return inventory.orElse(null);
    }
}
