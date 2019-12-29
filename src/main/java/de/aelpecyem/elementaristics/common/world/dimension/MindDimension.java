package de.aelpecyem.elementaristics.common.world.dimension;

import de.aelpecyem.elementaristics.common.world.biome.provider.MindBiomeProvider;
import de.aelpecyem.elementaristics.common.world.biome.provider.MindBiomeProviderSettings;
import de.aelpecyem.elementaristics.common.world.gen.MindChunkGenerator;
import de.aelpecyem.elementaristics.common.world.gen.MindGenSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class MindDimension extends Dimension {
    public MindDimension(World worldIn, DimensionType typeIn) {
        super(worldIn, typeIn);
    }

    public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
        BiomeProviderType<MindBiomeProviderSettings, MindBiomeProvider> biomeprovidertype1 = new BiomeProviderType<>(MindBiomeProvider::new, MindBiomeProviderSettings::new);
        MindBiomeProviderSettings biomeprovidersettings1 = biomeprovidertype1.createSettings().setGeneratorSettings(new MindGenSettings()).setWorldInfo(this.world.getWorldInfo());
        MindBiomeProvider biomeprovider = biomeprovidertype1.create(biomeprovidersettings1);

        ChunkGeneratorType<MindGenSettings, MindChunkGenerator> chunkgeneratortype4 = new ChunkGeneratorType<>(MindChunkGenerator::new, true, MindGenSettings::new);
        MindGenSettings gensettings1 = chunkgeneratortype4.createSettings();
        gensettings1.setDefaultFluid(Blocks.LAVA.getDefaultState());
        return chunkgeneratortype4.create(this.world, biomeprovider, gensettings1);
    }

    @Nullable
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        for (int i = chunkPosIn.getXStart(); i <= chunkPosIn.getXEnd(); ++i) {
            for (int j = chunkPosIn.getZStart(); j <= chunkPosIn.getZEnd(); ++j) {
                BlockPos blockpos = this.findSpawn(i, j, checkValid);
                if (blockpos != null) {
                    return blockpos;
                }
            }
        }

        return null;
    }
    @Nullable
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(posX, 0, posZ);
        Biome biome = this.world.getBiome(blockpos$mutableblockpos);
        BlockState blockstate = biome.getSurfaceBuilderConfig().getTop();

        if (checkValid && !blockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            Chunk chunk = this.world.getChunk(posX >> 4, posZ >> 4);
            int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, posX & 15, posZ & 15);
            if (i < 0) {
                return null;
            } else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, posX & 15, posZ & 15) > chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, posX & 15, posZ & 15)) {
                return null;
            } else {
                for (int j = i + 1; j >= 0; --j) {
                    blockpos$mutableblockpos.setPos(posX, j, posZ);
                    BlockState blockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
                    if (!blockstate1.getFluidState().isEmpty()) {
                        break;
                    }

                    if (blockstate1.equals(blockstate)) {
                        return blockpos$mutableblockpos.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float) (d0 * 2.0D + d1) / 3.0F;
    }

    @Override
    public boolean hasSkyLight() {
        return true;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld() {
        return false;
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    @OnlyIn(Dist.CLIENT)
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        float f = MathHelper.cos(celestialAngle * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.7529412F;
        float f2 = 0.84705883F;
        float f3 = 1.0F;
        f1 = f1 * (f * 0.94F + 0.06F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.09F);
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere() {
        return false;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    public static void teleport(Entity entity) {
        if (!entity.world.isRemote) {
            DimensionType type = MindDimensionType.getDimensionType();
            if (entity.getRidingEntity() == null && !entity.isBeingRidden()) {
                if (entity.timeUntilPortal > 0) {
                    entity.timeUntilPortal = 10;
                } else if (entity.dimension != type) {
                    entity.timeUntilPortal = 10;
                    entity.changeDimension(type);
                } else if (entity.dimension == type) {
                    entity.timeUntilPortal = 10;
                    entity.changeDimension(DimensionType.OVERWORLD);
                }
            }
        }
    }
}
