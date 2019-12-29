package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.world.biome.MindBiomeMeadows;
import de.aelpecyem.elementaristics.common.world.dimension.MindDimensionType;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Elementaristics.MODID)
public class ModWorld {
    public static final MindDimensionType MIND = new MindDimensionType(new ResourceLocation(Elementaristics.MODID, "mind"));

    public static final Biome FIELDS_REASON = new MindBiomeMeadows();

    public static void registerDimensions() {
        DimensionManager.registerDimension(new ResourceLocation(Elementaristics.MODID, "mind"), MIND, new PacketBuffer(Unpooled.buffer(16)), false);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void registerModDimensions(final RegistryEvent.Register<ModDimension> event) {
            Elementaristics.LOGGER.info("Registering dimensions...");
            event.getRegistry().registerAll(MIND);
        }

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> e) {
            Elementaristics.LOGGER.info("Registering biomes...");
            e.getRegistry().registerAll(FIELDS_REASON);
        }
    }
}
