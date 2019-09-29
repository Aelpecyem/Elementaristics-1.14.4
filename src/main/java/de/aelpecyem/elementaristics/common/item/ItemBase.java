package de.aelpecyem.elementaristics.common.item;

import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item {
    protected String name;
    public ItemBase(String name) {
        super(new Item.Properties().maxStackSize(64).group(Elementaristics.ELEMENTARISTICS_TAB));
        this.name = name;
        setRegistryName(new ResourceLocation(Elementaristics.MODID, name));
    }
}
