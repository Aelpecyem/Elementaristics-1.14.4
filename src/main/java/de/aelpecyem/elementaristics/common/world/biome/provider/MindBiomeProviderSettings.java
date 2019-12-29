package de.aelpecyem.elementaristics.common.world.biome.provider;

import de.aelpecyem.elementaristics.common.world.gen.MindGenSettings;
import de.aelpecyem.elementaristics.common.world.gen.MindWorldType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class MindBiomeProviderSettings implements IBiomeProviderSettings {
    private WorldInfo worldInfo;
    private MindGenSettings genSettings;

    public MindBiomeProviderSettings setWorldInfo(WorldInfo worldInfo) {
        worldInfo.setGenerator(new MindWorldType());
        this.worldInfo = worldInfo;
        return this;
    }

    public MindBiomeProviderSettings setGeneratorSettings(MindGenSettings genSettings) {
        this.genSettings = genSettings;
        return this;
    }

    public WorldInfo getWorldInfo() {
        worldInfo.setGenerator(new MindWorldType());
        return worldInfo;
    }

    public MindGenSettings getGeneratorSettings() {
        return genSettings;
    }
}
