package de.aelpecyem.elementaristics.common.world.biome;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.reg.ModBlocks;
import de.aelpecyem.elementaristics.reg.ModWorld;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public abstract class BaseMindBiome extends Biome {
    protected static final SurfaceBuilderConfig MIND_SURFACE_BASE = new SurfaceBuilderConfig(ModBlocks.stone_enriched.getDefaultState(), Blocks.PODZOL.getDefaultState(), Blocks.CLAY.getDefaultState());

    public BaseMindBiome(String name) {
        this(name, (new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT, MIND_SURFACE_BASE)).precipitation(RainType.NONE).category(Category.DESERT).depth(0.125F).scale(0.05F).temperature(1000.8F).downfall(0.4F).waterColor(4159204).waterFogColor(329011).parent(null));
    }

    public BaseMindBiome(String name, Builder builder) {
        super(builder);
        setRegistryName(Elementaristics.MODID, name);
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature)
    {
        return 3224223;
    }
}
