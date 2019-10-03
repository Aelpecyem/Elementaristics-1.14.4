package de.aelpecyem.elementaristics.common.handler;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.capability.CapabilityElementarstics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {
    private static final ResourceLocation CAP = new ResourceLocation(Elementaristics.MODID, "cap_player");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof PlayerEntity) event.addCapability(CAP, new CapabilityElementarstics());
    }
}
