package de.aelpecyem.elementaristics.client.handler;

import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenderManipulationHandler {
    @SubscribeEvent
    public static void manipulatePlayerRender(RenderPlayerEvent.Pre event) {

    }

    @SubscribeEvent
    public static void manipulatePlayerHandRenderer(RenderHandEvent event) {

    }

}
