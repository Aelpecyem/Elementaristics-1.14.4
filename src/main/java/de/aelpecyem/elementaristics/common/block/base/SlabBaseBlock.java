package de.aelpecyem.elementaristics.common.block.base;


import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.data.ElemLootTableProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class SlabBaseBlock extends SlabBlock {
    protected String name;
    private final BlockState baseState;

    public SlabBaseBlock(String name, BlockState baseState, Properties properties) {
        super(properties);
        this.name = name;
        this.baseState = baseState;
        setRegistryName(new ResourceLocation(Elementaristics.MODID, name));
        ElemLootTableProvider.LOOT_TABLES.put(getRegistryName(), ElemLootTableProvider.genSlab(this));
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
