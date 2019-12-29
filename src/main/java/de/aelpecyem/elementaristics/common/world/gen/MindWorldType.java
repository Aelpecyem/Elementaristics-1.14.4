package de.aelpecyem.elementaristics.common.world.gen;

import de.aelpecyem.elementaristics.common.world.biome.layers.MindBiomeLayer;
import de.aelpecyem.elementaristics.common.world.biome.layers.MindLayerUtil;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.EdgeBiomeLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

import java.util.function.LongFunction;

public class MindWorldType extends WorldType {
    public MindWorldType() {
        super("mind_type");
    }

    @Override
    public <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> getBiomeLayer(IAreaFactory<T> parentLayer, OverworldGenSettings chunkSettings, LongFunction<C> contextFactory) {
        parentLayer = (new MindBiomeLayer(getWorldType(), (MindGenSettings) chunkSettings)).apply(contextFactory.apply(200L), parentLayer);
        parentLayer = MindLayerUtil.repeat(1000L, ZoomLayer.NORMAL, parentLayer, 2, contextFactory);
        parentLayer = EdgeBiomeLayer.INSTANCE.apply(contextFactory.apply(1000L), parentLayer);
        return parentLayer;
    }
}