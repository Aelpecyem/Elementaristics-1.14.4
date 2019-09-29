package de.aelpecyem.elementaristics.client.fx.handler;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.item.essence.ItemEssence;
import de.aelpecyem.elementaristics.reg.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorHandler{

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, tint) -> {
            if (stack.getItem() instanceof ItemEssence)
                return ItemEssence.getAspect(stack) != null ? ItemEssence.getAspect(stack).getColor() : Minecraft.getInstance().world.rand.nextInt(16777215);
            return 16777215;
        }, ModItems.essence);
    }
}
