package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcessType;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class ModRecipes {
    public static void init() {
        initProcessTypes();
        initEssenceCrafting();
    }

    private static void initProcessTypes() {
        AspectProcessType.PURIFY = new AspectProcessType(new ResourceLocation(Elementaristics.MODID, "purify"));
        AspectProcessType.REACT = new AspectProcessType(new ResourceLocation(Elementaristics.MODID, "react"));

    }

    private static void initEssenceCrafting() {

    }

    public static void initCraftingRecipes(Consumer<IFinishedRecipe> consumer) {

    }
}
