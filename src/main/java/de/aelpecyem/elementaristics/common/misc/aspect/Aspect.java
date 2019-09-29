package de.aelpecyem.elementaristics.common.misc.aspect;

import java.util.HashMap;
import java.util.Map;

public class Aspect {
    public static final Map<String, Aspect> ASPECTS = new HashMap<>();
    public static Aspect MAGAN;
    public static Aspect MANA;

    public static Aspect AIR;
    public static Aspect EARTH;
    public static Aspect FIRE;
    public static Aspect WATER;
    public static Aspect AETHER;

    public static Aspect LIGHT;
    public static Aspect FLUX;
    public static void initAspects(){
        MAGAN = new Aspect("magan", 14117632);
        MANA = new Aspect("mana", 13972108);

        AIR = new Aspect("air", 1026524);
        EARTH = new Aspect("earth",23296);
        WATER = new Aspect("water",13275);
        FIRE = new Aspect("fire", 16740608);
        AETHER = new Aspect("aether", 9961727);

        LIGHT = new Aspect("light", 16777157);
        FLUX = new Aspect("flux", 11891928);
    }

    private String name;
    private int color;

    public Aspect(String name, int color) {
        this.name = name;
        this.color = color;
        ASPECTS.put(name, this);
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
}
