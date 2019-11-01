package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.block.PurifierBlock;
import de.aelpecyem.elementaristics.common.block.base.BaseBlock;
import de.aelpecyem.elementaristics.common.block.base.SlabBaseBlock;
import de.aelpecyem.elementaristics.common.block.base.StairsBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.PurifierTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Elementaristics.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static BaseBlock stone_enriched = new BaseBlock("stone_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BaseBlock stonebricks_enriched = new BaseBlock("stonebricks_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static BaseBlock smooth_stone_enriched = new BaseBlock("smooth_stone_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

    public static SlabBaseBlock stone_enriched_slab = new SlabBaseBlock("stone_enriched_slab", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static SlabBaseBlock stonebricks_enriched_slab = new SlabBaseBlock("stonebricks_enriched_slab", stonebricks_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static SlabBaseBlock smooth_stone_enriched_slab = new SlabBaseBlock("smooth_stone_enriched_slab", smooth_stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

    public static StairsBaseBlock stone_enriched_stairs = new StairsBaseBlock("stone_enriched_stairs", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static StairsBaseBlock stonebricks_enriched_stairs = new StairsBaseBlock("stonebricks_enriched_stairs", stonebricks_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    public static StairsBaseBlock smooth_stone_enriched_stairs = new StairsBaseBlock("smooth_stone_enriched_stairs", smooth_stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));


    public static PurifierBlock purifier = new PurifierBlock();//PurifierBlock(); //The Purifier will be a glass construct, which will need fire to work... particles will look accordingly


    //TileEntityTypes
    public static TileEntityType<PurifierTileEntity> PURIFIER = (TileEntityType<PurifierTileEntity>) TileEntityType.Builder.create(PurifierTileEntity::new, ModBlocks.purifier).build(null).setRegistryName("purifier");

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> register) {
        Elementaristics.LOGGER.info("Registering tile entities...");
        register.getRegistry().register(PURIFIER);
    }
}
