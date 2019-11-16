package de.aelpecyem.elementaristics.common.world.dimension.mind;

import de.aelpecyem.elementaristics.common.world.dimension.mind.settings.MindBiomeProviderSettings;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MindBiomeProvider extends BiomeProvider {
    public MindBiomeProvider(MindBiomeProviderSettings settings) {
        /*WorldInfo worldinfo = settings.getWorldInfo();
        Layer[] alayer = GaiaLayerUtil.makeLayers(worldinfo.getSeed());
        this.genBiomes = alayer[0];
        this.biomeFactoryLayer = alayer[1];

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());*/
    }

    @Override
    public Biome getBiome(int i, int i1) {
        return null;
    }

    @Override
    public Biome[] getBiomes(int i, int i1, int i2, int i3, boolean b) {
        return new Biome[0];
    }

    @Override
    public Set<Biome> getBiomesInSquare(int i, int i1, int i2) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int i, int i1, int i2, List<Biome> list, Random random) {
        return null;
    }

    @Override
    public boolean hasStructure(Structure<?> structure) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        return null;
    }
}
