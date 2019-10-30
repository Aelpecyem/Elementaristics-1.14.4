package de.aelpecyem.elementaristics.common.capability.ascension;

import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class AscensionPath {
    public static final Map<ResourceLocation, AscensionPath> PATHS = new HashMap<>();
    protected ResourceLocation pathName;

    public AscensionPath(ResourceLocation pathName) {
        this.pathName = pathName;
        PATHS.put(pathName, this);
    }

    public AscensionPath(String name) {
        this(new ResourceLocation(Elementaristics.MODID, name));
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {
        return nbt;
    }

    public AscensionPath readFromNBT(CompoundNBT nbt) {
        return this;
    }

    public ResourceLocation getPathName() {
        return pathName;
    }

    //handle stuff with ascension levels later here
}
