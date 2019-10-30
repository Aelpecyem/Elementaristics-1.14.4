package de.aelpecyem.elementaristics.common.capability.soul;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.capability.soul.stats.SoulStats;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class Soul {
    public static final Map<ResourceLocation, Soul> SOULS = new HashMap<>();

    protected ResourceLocation name;
    protected int color;
    protected float chance;
    protected SoulStats statInstance;

    public Soul(ResourceLocation name, int color, float chance, SoulStats soulStats) {
        this.name = name;
        this.color = color;
        this.chance = chance;
        this.statInstance = soulStats;
        SOULS.put(name, this);
    }

    public Soul(String name, int color, float chance, SoulStats soulStats) {
        this(new ResourceLocation(Elementaristics.MODID, name), color, chance, soulStats);
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    public ResourceLocation getName() {
        return name;
    }

    public void setName(ResourceLocation name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public SoulStats getStatInstance() {
        return statInstance;
    }

    public void setStatInstance(SoulStats statInstance) {
        this.statInstance = statInstance;
    }
}
