package de.aelpecyem.elementaristics.common.world.dimension.mind;

import de.aelpecyem.elementaristics.common.world.dimension.mind.settings.MindBiomeProviderSettings;
import de.aelpecyem.elementaristics.common.world.dimension.mind.settings.MindGeneratorSettings;
import de.aelpecyem.elementaristics.reg.ModWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;

import javax.annotation.Nullable;

public class MindDimension extends Dimension {
    public MindDimension(World worldIn, DimensionType type) {
        super(worldIn, type);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        ChunkGeneratorType<MindGeneratorSettings, MindChunkGenerator> chunkGen = ModWorld.MIND_GEN;
        MindGeneratorSettings settings = chunkGen.createSettings();
        BiomeProviderType<MindBiomeProviderSettings, MindBiomeProvider> biomeProvider = ModWorld.MIND_PROVIDER;
        return chunkGen.create(this.world, ModWorld.MIND_PROVIDER.create(biomeProvider.createSettings().setGeneratorSettings(settings).setWorldInfo(this.world.getWorldInfo())), settings);
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPos, boolean b) {
        return BlockPos.ZERO;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int i, int i1, boolean b) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long l, float v) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float v, float v1) {
        return new Vec3d(0, 0.3, 1);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }
}
