package de.aelpecyem.elementaristics.common.misc.processing;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class AspectProcessType {
    public static Map<ResourceLocation, AspectProcessType> PROCESS_TYPES = new HashMap<>();
    public static AspectProcessType PURIFY;
    public static AspectProcessType REACT;

    public ResourceLocation name;

    public AspectProcessType(ResourceLocation name) {
        this.name = name;
        PROCESS_TYPES.put(name, this);
    }

    public ResourceLocation getName() {
        return name;
    }
}
