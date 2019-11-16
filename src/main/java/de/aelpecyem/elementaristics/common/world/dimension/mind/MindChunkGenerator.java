package de.aelpecyem.elementaristics.common.world.dimension.mind;

import de.aelpecyem.elementaristics.common.world.dimension.mind.settings.MindGeneratorSettings;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.*;

public class MindChunkGenerator extends NoiseChunkGenerator<MindGeneratorSettings> {

    private static final float[] biomeWeights = Util.make(new float[25], (afloat) -> {
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                afloat[i + 2 + (j + 2) * 5] = f;
            }
        }
    });
    private OctavesNoiseGenerator depthNoise;
    private final boolean amplified;
    private final INoiseGenerator surfaceDepthNoise;
    public MindChunkGenerator(World worldIn, BiomeProvider provider, MindGeneratorSettings mindGeneratorSettings) {
        super(worldIn, provider, 4, 8, 256, mindGeneratorSettings, true);
        this.randomSeed.skip(2620);
        this.depthNoise = new OctavesNoiseGenerator(randomSeed, 16);
        this.amplified = worldIn.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
        this.surfaceDepthNoise = new PerlinNoiseGenerator(this.randomSeed, 4);
    }

    @Override
    protected double[] func_222549_a(int i, int i1) {
        return new double[0];
    }

    @Override
    protected double func_222545_a(double v, double v1, int i) {
        return 0;
    }

    @Override
    protected void func_222548_a(double[] doubles, int i, int i1) {

    }

    @Override
    public int getGroundHeight() {
        return 0;
    }
}
