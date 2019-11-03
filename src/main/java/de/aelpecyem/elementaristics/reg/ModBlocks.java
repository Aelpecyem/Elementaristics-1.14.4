package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.block.alchemy.PurifierBlock;
import de.aelpecyem.elementaristics.common.block.base.BaseBlock;
import de.aelpecyem.elementaristics.common.block.base.SlabBaseBlock;
import de.aelpecyem.elementaristics.common.block.base.StairsBaseBlock;
import de.aelpecyem.elementaristics.common.block.pantheon.ShrineBlock;
import de.aelpecyem.elementaristics.common.block.tile.PurifierTileEntity;
import de.aelpecyem.elementaristics.common.block.tile.ShrineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final List<ShrineBlock> SHRINES = new ArrayList<>();
    @ObjectHolder(Elementaristics.MODID + ":stone_enriched")
    public static BaseBlock stone_enriched = new BaseBlock("stone_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    @ObjectHolder(Elementaristics.MODID + ":stonebricks_enriched")
    public static BaseBlock stonebricks_enriched = new BaseBlock("stonebricks_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    @ObjectHolder(Elementaristics.MODID + ":smooth_stone_enriched")
    public static BaseBlock smooth_stone_enriched = new BaseBlock("smooth_stone_enriched", Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

    @ObjectHolder(Elementaristics.MODID + ":stone_enriched_slab")
    public static SlabBaseBlock stone_enriched_slab = new SlabBaseBlock("stone_enriched_slab", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    @ObjectHolder(Elementaristics.MODID + ":stonebricks_enriched_slab")
    public static SlabBaseBlock stonebricks_enriched_slab = new SlabBaseBlock("stonebricks_enriched_slab", stonebricks_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    @ObjectHolder(Elementaristics.MODID + ":smooth_stone_enriched_slab")
    public static SlabBaseBlock smooth_stone_enriched_slab = new SlabBaseBlock("smooth_stone_enriched_slab", smooth_stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));

    @ObjectHolder(Elementaristics.MODID + ":stone_enriched_stairs")
    public static StairsBaseBlock stone_enriched_stairs = new StairsBaseBlock("stone_enriched_stairs", stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    @ObjectHolder(Elementaristics.MODID + ":stonebricks_enriched_stairs")
    public static StairsBaseBlock stonebricks_enriched_stairs = new StairsBaseBlock("stonebricks_enriched_stairs", stonebricks_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    @ObjectHolder(Elementaristics.MODID + ":smooth_stone_enriched_stairs")
    public static StairsBaseBlock smooth_stone_enriched_stairs = new StairsBaseBlock("smooth_stone_enriched_stairs", smooth_stone_enriched.getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));


    @ObjectHolder(Elementaristics.MODID + ":purifier")
    public static PurifierBlock purifier = new PurifierBlock();//PurifierBlock(); //The Purifier will be a glass construct, which will need fire to work... particles will look accordingly

    @ObjectHolder(Elementaristics.MODID + ":shrine_dragon_aether")
    public static ShrineBlock shrine_dragon_aether = new ShrineBlock("shrine_dragon_aether", ModRegistries.DRAGON_AETHER);
    @ObjectHolder(Elementaristics.MODID + ":shrine_dragon_fire")
    public static ShrineBlock dragon_fire = new ShrineBlock("shrine_dragon_fire", ModRegistries.DRAGON_FIRE);
    @ObjectHolder(Elementaristics.MODID + ":shrine_dragon_earth")
    public static ShrineBlock shrine_dragon_earth = new ShrineBlock("shrine_dragon_earth", ModRegistries.DRAGON_EARTH);
    @ObjectHolder(Elementaristics.MODID + ":shrine_dragon_water")
    public static ShrineBlock shrine_dragon_water = new ShrineBlock("shrine_dragon_water", ModRegistries.DRAGON_WATER);
    @ObjectHolder(Elementaristics.MODID + ":shrine_dragon_air")
    public static ShrineBlock shrine_dragon_air = new ShrineBlock("shrine_dragon_air", ModRegistries.DRAGON_AIR);
    @ObjectHolder(Elementaristics.MODID + ":shrine_sun")
    public static ShrineBlock shrine_sun = new ShrineBlock("shrine_sun", ModRegistries.SUN);
    @ObjectHolder(Elementaristics.MODID + ":shrine_goat")
    public static ShrineBlock shrine_goat = new ShrineBlock("shrine_goat", ModRegistries.GOAT);
    @ObjectHolder(Elementaristics.MODID + ":shrine_moth")
    public static ShrineBlock shrine_moth = new ShrineBlock("shrine_moth", ModRegistries.MOTH);
    @ObjectHolder(Elementaristics.MODID + ":shrine_witch")
    public static ShrineBlock shrine_witch = new ShrineBlock("shrine_witch", ModRegistries.WITCH);


    //TileEntityTypes
    @ObjectHolder(Elementaristics.MODID + ":purifier")
    public static TileEntityType<PurifierTileEntity> PURIFIER = (TileEntityType<PurifierTileEntity>) TileEntityType.Builder.create(PurifierTileEntity::new, ModBlocks.purifier).build(null).setRegistryName("purifier");
    @ObjectHolder(Elementaristics.MODID + ":shrine")
    public static TileEntityType<ShrineTileEntity> SHRINE = (TileEntityType<ShrineTileEntity>) TileEntityType.Builder.create(ShrineTileEntity::new, SHRINES.toArray(new ShrineBlock[SHRINES.size()])).build(null).setRegistryName("shrine");

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> register) {
        Elementaristics.LOGGER.info("Registering tile entities...");
        register.getRegistry().register(PURIFIER);
        register.getRegistry().register(SHRINE);
    }
}
