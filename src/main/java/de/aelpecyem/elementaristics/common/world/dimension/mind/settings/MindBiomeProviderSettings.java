package de.aelpecyem.elementaristics.common.world.dimension.mind.settings;

import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class MindBiomeProviderSettings implements IBiomeProviderSettings { private WorldInfo worldInfo;
    private MindGeneratorSettings generatorSettings;

    public MindBiomeProviderSettings() { }

    public MindBiomeProviderSettings setWorldInfo(WorldInfo info) {
        this.worldInfo = info;
        return this;
    }

    public MindBiomeProviderSettings setGeneratorSettings(MindGeneratorSettings settings) {
        this.generatorSettings = settings;
        return this;
    }

    public WorldInfo getWorldInfo() {
        return this.worldInfo;
    }

    public MindGeneratorSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}
