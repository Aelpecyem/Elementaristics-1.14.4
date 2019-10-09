package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.BannerPattern;

import java.util.ArrayList;
import java.util.List;

public class ModBanners{
    private static List<BannerPattern> patterns = new ArrayList<>();
    public static BannerPattern pattern_glory;
    public static void init() { //todo, those need to get craftable
        Elementaristics.LOGGER.info("Registering banner patterns...");

        pattern_glory = addCraftingPattern("glory", EssenceItem.withAspect(Aspect.LIGHT)); //todo, somehow implement the crafting recipes
    }

    public static BannerPattern addCraftingPattern (String name, ItemStack craftingStack) {
        final BannerPattern pattern = BannerPattern.create(name.toUpperCase(), Elementaristics.MODID + "_" + name, Elementaristics.MODID + "_" + name, " # ", " # ", "###");//craftingStack);
        patterns.add(pattern);
        return pattern;
    }

    public static List<BannerPattern> getModdedPatterns () {

        return patterns;
    }

    public static ItemStack createBanner (Item base, BannerPattern... patterns) {

        final ItemStack stack = new ItemStack(base);

        final CompoundNBT blockEntityTag = stack.getOrCreateChildTag("BlockEntityTag");

        final ListNBT list = new ListNBT();

        for (final BannerPattern pattern : patterns) {

            final CompoundNBT patternTag = new CompoundNBT();
            patternTag.putString("Pattern", pattern.getHashname());
            patternTag.putInt("Color", 15);
            list.add(patternTag);
        }

        blockEntityTag.put("Patterns", list);

        return stack;
    }
}
