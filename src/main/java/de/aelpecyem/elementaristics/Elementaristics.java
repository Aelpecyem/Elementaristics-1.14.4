package de.aelpecyem.elementaristics;

import de.aelpecyem.elementaristics.client.render.tile.ShrineRenderer;
import de.aelpecyem.elementaristics.common.block.pantheon.ShrineBlock;
import de.aelpecyem.elementaristics.common.capability.ElementaristicsCapability;
import de.aelpecyem.elementaristics.common.item.essence.EssenceItem;
import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.proxy.ClientProxy;
import de.aelpecyem.elementaristics.proxy.CommonProxy;
import de.aelpecyem.elementaristics.reg.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
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
        }
    };

    public Elementaristics() {
        Aspect.initAspects();
        //  ModBanners.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Starting pre-init...");
        ModRegistries.init();
        CapabilityManager.INSTANCE.register(ElementaristicsCapability.class, new ElementaristicsCapability(), ElementaristicsCapability::new);
        PacketHandler.initPackets();
        ModRecipes.init();
        proxy.setup();
    }

    private void postSetup(final FMLLoadCompleteEvent event) {
        LOGGER.info("Starting post-init...");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        particles.init();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

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
                        if (obj instanceof ShrineBlock) {
                            DistExecutor.runForDist(() -> () -> registerTEISR((Block) obj, register), () -> () -> register((Block) obj, register));
                        } else {
                            register.getRegistry().register(new BlockItem((Block) obj, new Item.Properties().group(Elementaristics.ELEMENTARISTICS_TAB)).setRegistryName(((Block) obj).getRegistryName()));
                        }
                    }
                }

            } catch (Exception ignored) {
                ignored.printStackTrace(); //check
            }
        }

        @OnlyIn(Dist.DEDICATED_SERVER)
        private static Void register(Block block, RegistryEvent.Register<Item> r) {
            if (block instanceof ShrineBlock) {
                r.getRegistry().register(new BlockItem(block, new Item.Properties().group(Elementaristics.ELEMENTARISTICS_TAB)).setRegistryName(block.getRegistryName()));
            }
            return null;
        }

        @OnlyIn(Dist.CLIENT)
        private static Void registerTEISR(Block block, RegistryEvent.Register<Item> r) {
            if (block instanceof ShrineBlock) {
                r.getRegistry().register(new BlockItem(block, new Item.Properties().group(Elementaristics.ELEMENTARISTICS_TAB).setTEISR(() -> () -> new ShrineRenderer.ForwardingTEISR())).setRegistryName(block.getRegistryName()));
            }
            return null;
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

        @SubscribeEvent
        public static void registerEffects(RegistryEvent.Register<Effect> register) {
            Elementaristics.LOGGER.info("Registering potion effects...");
            try {
                for (Field f : ModPotions.class.getFields()) {
                    Object obj = f.get(null);
                    if (obj instanceof Effect) {
                        register.getRegistry().register((Effect) obj);
                    }
                }
            } catch (Exception ignored) {
            }
        }

        @SubscribeEvent
        public static void registerDimensions(RegistryEvent.Register<ModDimension> register) {
            Elementaristics.LOGGER.info("Registering dimensions...");
            try {
                for (Field f : ModWorld.class.getFields()) {
                    Object obj = f.get(null);
                    Elementaristics.LOGGER.info(obj);
                    if (obj instanceof ModDimension) {
                        register.getRegistry().register((ModDimension) obj);
                    }
                }
            } catch (Exception ignored) {}
        }

        @SubscribeEvent
        public static void registerGenerators(RegistryEvent.Register<ChunkGeneratorType<?, ?>> register) {
            Elementaristics.LOGGER.info("Registering world generators...");
            try {
                for (Field f : ModWorld.class.getFields()) {
                    Object obj = f.get(null);
                    Elementaristics.LOGGER.info(obj);
                    if (obj instanceof ChunkGeneratorType) {
                        register.getRegistry().register((ChunkGeneratorType) obj);
                    }
                }
            } catch (Exception ignored) {}
        }

        @SubscribeEvent
        public static void registerBiomes(RegistryEvent.Register<BiomeProviderType<?, ?>> register) {
            Elementaristics.LOGGER.info("Registering world generators...");
            try {
                for (Field f : ModWorld.class.getFields()) {
                    Object obj = f.get(null);
                    Elementaristics.LOGGER.info(obj);
                    if (obj instanceof BiomeProviderType) {
                        register.getRegistry().register((BiomeProviderType) obj);
                    }
                }
            } catch (Exception ignored) {}
        }

        @SubscribeEvent
        public static void registerSpecialTextures(TextureStitchEvent.Pre event) {
            event.addSprite(ClientProxy.THICC_WATER);
        }
    }
}
