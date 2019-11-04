package de.aelpecyem.elementaristics.common.block.tile;

import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcess;
import de.aelpecyem.elementaristics.reg.ModItems;
import de.aelpecyem.elementaristics.util.InventoryUtil;
import net.minecraft.item.ItemStack;
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
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                sync();
                super.onContentsChanged(slot);
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return slot == 1 || (stack.getItem() instanceof AlchemicalMatterItem || stack.getItem() instanceof EssenceItem);
            }
        };
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
            sync();
            increaseTicks();
            if (isFinished()) {
                doAlchemy();
                finish();
            }
        } else {
            resetProgress();
        }
    }

    public void doAlchemy() {
        int prevColor = -1;
        if (InventoryUtil.check(getInventory(), slot -> getInventory().getStackInSlot(slot).getItem() instanceof EssenceItem)) {
            int slot = InventoryUtil.slotForCheck(getInventory(), s -> getInventory().getStackInSlot(s).getItem() instanceof EssenceItem);
            prevColor = EssenceItem.getAspect(getInventory().getStackInSlot(slot)).getColor();
            int count = getInventory().extractItem(slot, getInventory().getSlotLimit(slot), false).getCount();
            getInventory().insertItem(slot, new ItemStack(ModItems.alchemical_matter, count), false);
        }
        if (InventoryUtil.check(getInventory(), slot -> getInventory().getStackInSlot(slot).getItem() instanceof AlchemicalMatterItem)) {
            ItemStack stack = getInventory().getStackInSlot(InventoryUtil.slotForCheck(getInventory(), slot -> getInventory().getStackInSlot(slot).getItem() instanceof AlchemicalMatterItem));
            if (prevColor == -1) {
                prevColor = AlchemicalMatterItem.getColor(stack);
            }
            AlchemicalMatterItem.setColor(stack, getTargetColor(prevColor));
            AlchemicalMatterItem.addSuccessfulProcess(stack, addProcessPart());
        }
    }

    public abstract AspectProcess addProcessPart();

    public abstract int getTargetColor(int previousColor);
    /**
     * Use this method to calculate if the apparatus in question should increase its working progress
     *
     * @return whether the apparatus should continue work
     */
    public boolean isWorking() {
        return (InventoryUtil.check(getInventory(), slot -> getInventory().getStackInSlot(slot).getItem() instanceof AlchemicalMatterItem) || InventoryUtil.check(getInventory(), slot -> getInventory().getStackInSlot(slot).getItem() instanceof EssenceItem)) && InventoryUtil.check(getInventory(), slot -> getInventory().getStackInSlot(slot).isEmpty());
    }

    public void increaseTicks() {
        ticks++;
    }

    public void resetProgress() {
        if (ticks > 0) {
            ticks = 0;
            sync();
        }
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
