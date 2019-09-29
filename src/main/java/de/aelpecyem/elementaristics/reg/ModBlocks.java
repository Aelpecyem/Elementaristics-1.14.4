package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.block.BlockBase;
import de.aelpecyem.elementaristics.common.block.BlockSlabBase;
import de.aelpecyem.elementaristics.common.block.BlockStairsBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Elementaristics.MODID)
public class ModBlocks {
    public static BlockBase stone_enriched = new BlockBase("stone_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BlockBase stonebricks_enriched = new BlockBase("stonebricks_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BlockBase smooth_stone_enriched = new BlockBase("smooth_stone_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

    public static BlockSlabBase stone_enriched_slab = new BlockSlabBase("stone_enriched_slab", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BlockSlabBase stonebricks_enriched_slab = new BlockSlabBase("stonebricks_enriched_slab", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BlockSlabBase smooth_stone_enriched_slab = new BlockSlabBase("smooth_stone_enriched_slab", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

    public static BlockStairsBase stone_enriched_stairs = new BlockStairsBase("stone_enriched_stairs", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BlockStairsBase stonebricks_enriched_stairs = new BlockStairsBase("stonebricks_enriched_stairs", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

}
