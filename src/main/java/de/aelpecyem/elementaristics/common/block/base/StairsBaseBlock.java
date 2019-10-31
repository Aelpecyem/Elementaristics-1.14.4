package de.aelpecyem.elementaristics.common.block.base;


import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class StairsBaseBlock extends StairsBlock {
    protected String name;
    private final BlockState baseState;

    public StairsBaseBlock(String name, BlockState baseState, Properties properties) {
        super(baseState, properties);
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
