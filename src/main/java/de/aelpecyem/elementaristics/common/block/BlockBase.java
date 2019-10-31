package de.aelpecyem.elementaristics.common.block;


import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block{
    protected String name;
    public BlockBase(String name, Properties properties) {
        super(properties);
        this.name = name;
        setRegistryName(new ResourceLocation(Elementaristics.MODID, name));
    }
}
