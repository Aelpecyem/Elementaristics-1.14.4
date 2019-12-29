package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.world.biome.MindBiomeMeadows;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Elementaristics.MODID)
public class ModWorld {
    public static final Biome FIELDS_REASON = new MindBiomeMeadows();


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> e) {
            Elementaristics.LOGGER.info("Registering biomes...");
            e.getRegistry().registerAll(FIELDS_REASON);
        }
    }
}
