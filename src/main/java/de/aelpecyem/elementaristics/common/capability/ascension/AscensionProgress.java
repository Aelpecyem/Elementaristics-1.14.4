package de.aelpecyem.elementaristics.common.capability.ascension;

import de.aelpecyem.elementaristics.common.capability.soul.Soul;
import de.aelpecyem.elementaristics.reg.ModRegistries;
import de.aelpecyem.elementaristics.util.SoulHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class AscensionProgress {
    public boolean isDirty;

    protected Soul soul;
    protected boolean knowsSoul;
    protected int ascensionStage;
    protected AscensionPath ascensionPath;

    public AscensionProgress() {
        ascensionPath = ModRegistries.PATH_DEUS_EX_CARNE;
        soul = SoulHelper.getRandomSoulWithChance();
    }

    public void setSoul(Soul soul) {
        isDirty = true;
        this.soul = soul;
    }

    public boolean knowsSoul() {
        return knowsSoul;
    }

    public void setKnowsSoul(boolean knowsSoul) {
        isDirty = true;
        this.knowsSoul = knowsSoul;
    }

    public Soul getSoul() {
        return soul;
    }

    public void setAscensionPath(AscensionPath ascensionPath) {
        isDirty = true;
        this.ascensionPath = ascensionPath;
    }

    public AscensionPath getAscensionPath() {
        return ascensionPath;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {
        nbt.putString("soul", soul != null ? soul.getName().toString() : "");
        nbt.putBoolean("knowsSoul", knowsSoul);
        nbt.putString("path", ascensionPath.getPathName().toString());
        ascensionPath.writeToNBT(nbt);
        nbt.putInt("ascensionStage", ascensionStage);
        return nbt;
    }

    public AscensionProgress readFromNBT(CompoundNBT nbt) {
        soul = Soul.SOULS.getOrDefault(new ResourceLocation(nbt.getString("soul")), ModRegistries.MANA);
        knowsSoul = nbt.getBoolean("knowsSoul");
        ascensionPath = AscensionPath.PATHS.getOrDefault(new ResourceLocation(nbt.getString("path")), ModRegistries.PATH_DEUS_EX_CARNE);
        ascensionPath.readFromNBT(nbt);
        ascensionStage = nbt.getInt("ascensionStage");
        return this;
    }
}
