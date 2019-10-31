package de.aelpecyem.elementaristics.common.misc.processing;

import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AspectProcess {
    protected ResourceLocation name;
    protected Ingredient startIngredient;
    protected List<AspectProcessPart> processParts;

    public AspectProcess(ResourceLocation name, Ingredient startIngredient, AspectProcessPart... processParts) {
        this.name = name;
        this.startIngredient = startIngredient;
        this.processParts = Arrays.asList(processParts);
    }

    public static class Util {
        public static List<AspectProcessPart> getCompletedProcessesFor(ItemStack alchemicalMatter) {
            List<AspectProcessPart> processParts = new ArrayList<>();
            if (alchemicalMatter.getItem() instanceof AlchemicalMatterItem) {
                processParts.addAll(deserializeProcessString(AlchemicalMatterItem.getProcessString(alchemicalMatter)));
            }
            return processParts;
        }

        public static int getProcessStageFor(ItemStack alchemicalMatter) {
            if (alchemicalMatter.getItem() instanceof AlchemicalMatterItem) {
                return AlchemicalMatterItem.getProcessStage(alchemicalMatter);
            }
            return 0;
        }

        public static List<AspectProcessPart> deserializeProcessString(String processString) {
            String[] parts = processString.split(";");
            List<AspectProcessPart> processParts = new ArrayList<>();
            for (String part : parts) {
                if (AspectProcessPart.PROCESS_PARTS.containsKey(part)) {
                    processParts.add(AspectProcessPart.PROCESS_PARTS.get(part));
                }
            }
            return processParts;
        }
    }
}
