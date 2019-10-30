package de.aelpecyem.elementaristics.common.capability.magan;

import net.minecraft.nbt.CompoundNBT;

public class MaganStorage {
    public boolean isDirty;
    protected int currentMagan = 0;

    public MaganStorage() {

    }

    public void setCurrentMagan(int currentMagan) {
        isDirty = true;
        this.currentMagan = currentMagan;
    }

    public int getCurrentMagan() {
        return currentMagan;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {
        nbt.putInt("currentMagan", currentMagan);
        return nbt;
    }

    public MaganStorage readFromNBT(CompoundNBT nbt) {
        currentMagan = nbt.getInt("currentMagan");
        return this;
    }
}
