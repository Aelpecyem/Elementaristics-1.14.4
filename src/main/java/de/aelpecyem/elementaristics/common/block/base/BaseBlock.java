package de.aelpecyem.elementaristics.common.block.base;


import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BaseBlock extends Block {
    protected String name;

    public BaseBlock(String name, Properties properties) {
        super(properties);
        this.name = name;
        setRegistryName(new ResourceLocation(Elementaristics.MODID, name));
    }
}
