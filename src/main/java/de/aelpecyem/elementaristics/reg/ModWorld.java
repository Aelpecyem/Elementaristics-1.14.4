package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.world.dimension.mind.MindBiomeProvider;
import de.aelpecyem.elementaristics.common.world.dimension.mind.MindChunkGenerator;
import de.aelpecyem.elementaristics.common.world.dimension.mind.MindDimension;
import de.aelpecyem.elementaristics.common.world.dimension.mind.settings.MindBiomeProviderSettings;
import de.aelpecyem.elementaristics.common.world.dimension.mind.settings.MindGeneratorSettings;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.BiFunction;

public class ModWorld {
    @ObjectHolder(Elementaristics.MODID + ":mind")
    public static final ModDimension MIND = new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return MindDimension::new;
        }
    }.setRegistryName(Elementaristics.MODID, "mind");

    @ObjectHolder(Elementaristics.MODID + ":mind_provider")
    public static final BiomeProviderType<MindBiomeProviderSettings, MindBiomeProvider> MIND_PROVIDER = new BiomeProviderType<>(MindBiomeProvider::new, MindBiomeProviderSettings::new);

    @ObjectHolder(Elementaristics.MODID + ":mind_gen")
    public static final ChunkGeneratorType<MindGeneratorSettings, MindChunkGenerator> MIND_GEN = new ChunkGeneratorType<>(MindChunkGenerator::new, true, MindGeneratorSettings::new);

    public static final DimensionType MIND_DIMENSION = DimensionManager.registerDimension(new ResourceLocation(Elementaristics.MODID, "elem_mind"), MIND, new PacketBuffer(Unpooled.buffer(16)), false);

}
