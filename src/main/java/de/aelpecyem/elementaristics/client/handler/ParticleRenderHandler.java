package de.aelpecyem.elementaristics.client.handler;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.reg.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParticleRenderHandler {
    @SubscribeEvent
    public static void onRenderLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        mc.getProfiler().startSection(() -> Elementaristics.MODID + ":renderParticles");
        ModParticles.Handler.renderParticle(event.getPartialTicks());
        mc.getProfiler().endSection();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().world == null) {
            ModParticles.Handler.clearParticles();
        } else if (!Minecraft.getInstance().isGamePaused() && Minecraft.getInstance().renderViewEntity != null) {
            Minecraft.getInstance().getProfiler().startSection(() -> Elementaristics.MODID + ":updateParticles");
            ModParticles.Handler.updateParticles();
            Minecraft.getInstance().getProfiler().endSection();
        }
    }
}
