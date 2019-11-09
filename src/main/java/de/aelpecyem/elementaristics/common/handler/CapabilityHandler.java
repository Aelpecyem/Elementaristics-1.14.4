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
    public static void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(CAP, new ElementaristicsCapability(((PlayerEntity) event.getObject()).getRNG()));
    }

    @SubscribeEvent
    public static void update(LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntity().world.isRemote && event.getEntityLiving() instanceof PlayerEntity) {
            System.out.println(ElementaristicsCapability.getCapability((PlayerEntity) event.getEntityLiving()).ascensionProgress.getSoul().getName().toString());
            ElementaristicsCapability.Util.syncAll((PlayerEntity) event.getEntityLiving());
        }
    }
}
