package de.aelpecyem.elementaristics.common.misc.soul;

import de.aelpecyem.elementaristics.common.misc.soul.stats.SoulStats;

import java.util.HashMap;
import java.util.Map;

public class Soul {
    public static final Map<String, Soul> SOULS = new HashMap<>();

    public static void initSouls() {

    }

    private String name;
    private int color;
    private SoulStats statInstance;

    public Soul(String name, int color, SoulStats soulStats) {
        this.name = name;
        this.color = color;
        this.statInstance = soulStats;
        SOULS.put(name, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
