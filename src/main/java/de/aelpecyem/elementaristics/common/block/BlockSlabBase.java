package de.aelpecyem.elementaristics.common.block;


import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.block.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockSlabBase extends SlabBlock{
    protected String name;
    private final BlockState baseState;

    public BlockSlabBase(String name, BlockState baseState, Properties properties) {
        super(properties);
        this.name = name;
        this.baseState = baseState;
        setRegistryName(new ResourceLocation(Elementaristics.MODID, name));
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState tool) {
        return this.baseState.getHarvestTool();
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return this.baseState.getHarvestLevel();
    }
}
