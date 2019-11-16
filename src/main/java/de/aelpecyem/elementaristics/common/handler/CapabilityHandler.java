package de.aelpecyem.elementaristics.common.handler;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.capability.ElementaristicsCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {
    private static final ResourceLocation CAP = new ResourceLocation(Elementaristics.MODID, "cap_player");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent event) { //todo, fix this so the capabiltiy does only get added once for each entity
        if (event.getObject() instanceof PlayerEntity) {
            System.out.println(event.getCapabilities().containsKey(CAP));
            event.addCapability(CAP, new ElementaristicsCapability());
        }
    }

    @SubscribeEvent
    public static void update(LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntity().world.isRemote && event.getEntityLiving() instanceof PlayerEntity) {
            ElementaristicsCapability.Util.syncAll((PlayerEntity) event.getEntityLiving());
        }
    }
}
