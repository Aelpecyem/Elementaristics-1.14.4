package de.aelpecyem.elementaristics.common.misc.pantheon;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.block.tile.ShrineTileEntity;
import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Deity {
    public static final Map<ResourceLocation, Deity> DEITIES = new HashMap<>();
    private int hour;
    private ResourceLocation name;
    private Aspect aspect;
    private int color;

    public Deity(int hour, ResourceLocation name, @Nullable Aspect aspect, int color) {
        this.hour = hour;
        this.name = name;
        this.aspect = aspect;
        this.color = color;
        DEITIES.put(name, this);
    }

    public int getHour() {
        return hour;
    }

    public int getColor() {
        return color;
    }

    public ResourceLocation getName() {
        return name;
    }

    public Aspect getAspect() {
        return aspect;
    }


    public void passiveParticles(ShrineTileEntity te) {
        if (te.getWorld().isRemote && te.getWorld().rand.nextInt(10) == 0) {
            Elementaristics.particles.spawnAmbientBlockParticles(te.getWorld(), te.getPos(), color);
        }
    }
}
