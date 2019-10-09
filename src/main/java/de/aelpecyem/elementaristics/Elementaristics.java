package de.aelpecyem.elementaristics;

import de.aelpecyem.elementaristics.common.capability.ElementaristicsCapability;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.proxy.ClientProxy;
import de.aelpecyem.elementaristics.proxy.CommonProxy;
import de.aelpecyem.elementaristics.reg.ModBlocks;
import de.aelpecyem.elementaristics.reg.ModItems;
import de.aelpecyem.elementaristics.reg.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod(Elementaristics.MODID)
public class Elementaristics {
    //todo; redo particles entirely, but keep the nifty particle types I added uwu
    public static final String MODID = "elementaristics";
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static final Logger LOGGER = LogManager.getLogger(Elementaristics.MODID);
    public static ModParticles particles = new ModParticles();

    public static final ItemGroup ELEMENTARISTICS_TAB = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            return EssenceItem.withAspect(Aspect.MAGAN);
        }

        @Override
        public void fill(NonNullList<ItemStack> items) {
            for (final Aspect aspect : Aspect.ASPECTS.values())
                items.add(EssenceItem.withAspect(aspect));

            super.fill(items);

            //for (final BannerPattern banner : ModBanners.getModdedPatterns())
            //    items.add(ModBanners.createBanner(Items.WHITE_BANNER, banner));
        }
    };

    public Elementaristics() {
        Aspect.initAspects();
        //  ModBanners.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void preInit(final FMLCommonSetupEvent event) {
        LOGGER.info("Starting pre-init...");
        CapabilityManager.INSTANCE.register(ElementaristicsCapability.class, new ElementaristicsCapability(), ElementaristicsCapability::new);
        PacketHandler.initPackets();
    }

    private void postInit(final FMLLoadCompleteEvent event) {
        LOGGER.info("Starting post-init...");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        particles.init();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        /*InterModComms.sendTo(MODID, "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });*/
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
       /* LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));*/
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> register) {
            Elementaristics.LOGGER.info("Registering items...");
            try {
                for (Field f : ModItems.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Item) {
                        register.getRegistry().register((Item) obj);
                    }
                }
                for (Field f : ModBlocks.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Block) {
                        register.getRegistry().register(new BlockItem((Block) obj, new Item.Properties().group(Elementaristics.ELEMENTARISTICS_TAB)).setRegistryName(((Block) obj).getRegistryName()));
                    }
                }
            } catch (Exception ignored) {
            }
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> register) {
            Elementaristics.LOGGER.info("Registering blocks...");
            try {
                for (Field f : ModBlocks.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Block) {
                        register.getRegistry().register((Block) obj);
                    }
                }
            } catch (Exception ignored) {}
        }
    }
}
