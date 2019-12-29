package de.aelpecyem.elementaristics.common.world.biome;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.reg.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class MindBiomeMeadows extends Biome {
    protected static final SurfaceBuilderConfig MIND_MEADOWS_SURFACE = new SurfaceBuilderConfig(ModBlocks.stone_enriched.getDefaultState(), Blocks.PODZOL.getDefaultState(), Blocks.CLAY.getDefaultState());

    public MindBiomeMeadows() {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT, MIND_MEADOWS_SURFACE)).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(1000.8F).downfall(0.4F).waterColor(4159204).waterFogColor(329011).parent(null));
        setRegistryName(Elementaristics.MODID, "fields_reason");
        this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
        this.addStructure(Feature.PILLAGER_OUTPOST, new PillagerOutpostConfig(0.004D));
        //this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, createDecoratedFeature(ModFeatures.TEST, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_PASSTHROUGH, new ChanceConfig(80)));
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        //ModBiomeFeatures.addDriedGrass(this);
        //ModBiomeFeatures.addLowDriedLakes(this);
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        //this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityInit.COLD_CREEPER, 100, 4, 4));
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature)
    {
        return 0xE0B041;
    }
}
