package de.aelpecyem.elementaristics.common.world.biome.layers;

import com.google.common.collect.ImmutableList;
import de.aelpecyem.elementaristics.common.world.gen.MindGenSettings;
import de.aelpecyem.elementaristics.reg.ModWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;

public class MindBiomeLayer implements IC0Transformer {
    public static ImmutableList<BiomeManager.BiomeEntry> getBiomesToGeneration() {
        ArrayList<BiomeManager.BiomeEntry> list = new ArrayList<>();
        list.add(new BiomeManager.BiomeEntry(ModWorld.FIELDS_REASON, 100));
       // list.add(new BiomeManager.BiomeEntry(Biomes.ICE_SPIKES, 60));
        return ImmutableList.copyOf(list);
    }

    @SuppressWarnings("unchecked")
    private java.util.List<BiomeManager.BiomeEntry>[] biomes = new java.util.ArrayList[BiomeManager.BiomeType.values().length];
    private final MindGenSettings settings;

    public MindBiomeLayer(WorldType p_i48641_1_, MindGenSettings p_i48641_2_) {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
            ImmutableList<BiomeManager.BiomeEntry> biomesToAdd = getBiomesToGeneration();
            int idx = type.ordinal();

            if (biomes[idx] == null)
                biomes[idx] = new java.util.ArrayList<BiomeManager.BiomeEntry>();

            if (biomesToAdd != null)
                biomes[idx].addAll(biomesToAdd);
        }

        if (p_i48641_1_ == WorldType.DEFAULT_1_1) {
            this.settings = null;
        } else {
            this.settings = p_i48641_2_;
        }
    }

    @SuppressWarnings("deprecation")
    public int apply(INoiseRandom context, int value) {
        if (this.settings != null && this.settings.func_202199_l() >= 0) {
            return this.settings.func_202199_l();
        } else {
            return Registry.BIOME.getId(getWeightedBiomeEntry(net.minecraftforge.common.BiomeManager.BiomeType.DESERT, context).biome);
        }
    }

    protected BiomeManager.BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type, INoiseRandom context) {
        java.util.List<BiomeManager.BiomeEntry> biomeList = biomes[type.ordinal()];
        int totalWeight = net.minecraft.util.WeightedRandom.getTotalWeight(biomeList);
        int weight = BiomeManager.isTypeListModded(type) ? context.random(totalWeight) : context.random(totalWeight / 10) * 10;
        return net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
    }
}