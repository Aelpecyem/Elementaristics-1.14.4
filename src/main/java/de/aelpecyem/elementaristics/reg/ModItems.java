package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.item.consumable.burnable.HerbsBundleItem;
import de.aelpecyem.elementaristics.common.item.crafting.AlchemicalMatterItem;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

//@ObjectHolder(Elementaristics.MODID)
public class ModItems {
    @ObjectHolder(Elementaristics.MODID + ":essence")
    public static Item essence = new EssenceItem();

    @ObjectHolder(Elementaristics.MODID + ":alchemical_matter")
    public static Item alchemical_matter = new AlchemicalMatterItem();
    @ObjectHolder(Elementaristics.MODID + ":bundle_herbs")
    public static Item bundle_herbs = new HerbsBundleItem();
}
