package de.aelpecyem.elementaristics.client.handler;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenderManipulationHandler {
    @SubscribeEvent
    public static void manipulatePlayerRender(RenderPlayerEvent.Pre event) {
        if (event.getEntityLiving().dimension == DimensionType.OVERWORLD) {
            GlStateManager.color4f(1, 0.9F, 0.7F, 0.7F);
        }
    }
}
