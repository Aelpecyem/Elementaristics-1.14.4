package de.aelpecyem.elementaristics.util;

import de.aelpecyem.elementaristics.common.capability.soul.Soul;
import de.aelpecyem.elementaristics.reg.ModRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SoulHelper {

    public static Soul getRandomSoul() {
        Random generator = new Random();
        Soul[] values = Soul.SOULS.values().toArray(new Soul[]{});
        return values[generator.nextInt(values.length)];
    }

    public static Soul getRandomSoulWithChance() {
        Random random = new Random();
        List<Soul> possibleSouls = new ArrayList<>();
        possibleSouls.addAll(Soul.SOULS.values());
        Collections.shuffle(possibleSouls);
        for (Soul soul : possibleSouls) {
            if (soul.getChance() > random.nextFloat()) return soul;
        }
        return ModRegistries.DRAGON;
    }
}
