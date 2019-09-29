package de.aelpecyem.elementaristics.client.fx.handler;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.fx.particle.GlowParticle;
import de.aelpecyem.elementaristics.client.fx.particle.ParticleHandler;
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
        mc.getProfiler().startSection(() -> Elementaristics.MODID + ":renderParticles"); //mcProfiler.func_194340_a(() -> Elementaristics.MODID + ":renderParticles");
        ParticleHandler.renderParticle(event.getPartialTicks());//, event.getContext().event.getPartialTicks());
        mc.getProfiler().endSection();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().world == null) {
            ParticleHandler.clearParticles();
        } else if (!Minecraft.getInstance().isGamePaused() && Minecraft.getInstance().renderViewEntity != null) {
            GlowParticle particle = new GlowParticle(Minecraft.getInstance().world, Minecraft.getInstance().renderViewEntity.posX, Minecraft.getInstance().renderViewEntity.posY, Minecraft.getInstance().renderViewEntity.posZ, 0, 0, 0);//, 10415, 4, 1000, 0, true, true, 0.8F, false);
            ParticleHandler.spawnParticle(() -> particle);
            //Minecraft.getInstance().particles.addEffect(particle); //todo, maybe instead of having a special particle handler, an own particle type could be declared with beginRender and finishRender methods to apply the OpenGL stuff
            Minecraft.getInstance().getProfiler().startSection(() -> Elementaristics.MODID + ":updateParticles");
            ParticleHandler.updateParticles();
            Minecraft.getInstance().getProfiler().endSection();
        }
    }
}
