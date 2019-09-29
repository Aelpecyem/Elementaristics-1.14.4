package de.aelpecyem.elementaristics.util;

public class ColorUtil {
    public static boolean isDark(float r, float g, float b){
        return isDark(r, g, b, 0.45F);
    }

    public static boolean isDark(float r, float g, float b, float threshold) {
        return r < threshold && g < threshold && b < threshold;
    }
}
