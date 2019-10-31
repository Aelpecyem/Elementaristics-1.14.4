package de.aelpecyem.elementaristics.common.misc.processing;

import java.util.HashMap;
import java.util.Map;

public class AspectProcessPart {
    public static final Map<String, AspectProcessPart> PROCESS_PARTS = new HashMap<>();
    protected String name;

    public AspectProcessPart(String name) {
        this.name = name;
        PROCESS_PARTS.put(name, this);
    }

    //work on this later....

    /*
        The idea: basically, essence crafting will be different; you'll have an essence (or an item) to start with, and by starting the process it will get turned into
        Alchemical Matter... this Alchemical Matter will have an NBT Tag displaying the processes it went through, and its current processing stage...
        once the process is complete, which will be checked for each Aspect Process, the Alchemical Matter gets transformed into the desired item... Alchemical Matter will also be consumable,
        because of an Alchemical Process that requires exactly that.

        For a Process to advance, a machine of sorts is always required; it will modify the Alchemical Matter's NBT Data accordingly... if no Recipe can be continued with the Alchemical Matter's state (each Machine will check that, which is why they will all have the same base)
        Chaotic Matter will be left (each Process part basically acts like a recipe)
     */
    public String serialize() {
        return name + ";";
    }
}
