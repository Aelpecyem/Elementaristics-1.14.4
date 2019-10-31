package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.item.consumable.burnable.HerbsBundleItem;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Elementaristics.MODID)
public class ModItems {
    public static Item essence = new EssenceItem();

    public static Item bundle_herbs = new HerbsBundleItem();
}
