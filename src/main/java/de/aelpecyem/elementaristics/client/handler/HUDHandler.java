package de.aelpecyem.elementaristics.client.handler;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.capability.ElementaristicsCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HUDHandler {
    public static String current_vision = "";
    public static float vision_progress = 0;
    public static final ResourceLocation HUD_TEXTURE = new ResourceLocation(Elementaristics.MODID, "textures/gui/hud_elements.png");


    @SubscribeEvent
    public static void onRenderHud(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE && !event.isCancelable()) {
            renderMaganBar(event);
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && !event.isCancelable()) {
            renderVision(event);
        }
    }

    public static void renderVision(RenderGameOverlayEvent.Post event) {
        if (!current_vision.equals("")) {
            if (vision_progress >= 1) {
                current_vision = "";
            }
            ResourceLocation tex = new ResourceLocation(current_vision);
            int posX = event.getWindow().getScaledWidth() / 2 - 256 / 2;
            int poxY = event.getWindow().getScaledHeight() / 2 - 256 / 2;
            float alpha = (0.5F - Math.abs(0.5F - vision_progress)) * 1.8F;
            drawColoredTexturedModalRect(posX, poxY, 0, 0, 256, 256, Color.WHITE, alpha, tex);
            if (!Minecraft.getInstance().isGamePaused())
                vision_progress += 0.003F;
        } else {
            vision_progress = 0;
        }
    }

    public static void renderMaganBar(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.player.isCreative() && !mc.player.isSpectator()) { //Config.client.showBar &&
            ElementaristicsCapability caps = ElementaristicsCapability.getCapability(mc.player); //mc.player.getCapability(PlayerCapProvider.ELEMENTARISTICS_CAP, null);
            if (caps.ascensionStage > 0) {
                int posX = event.getWindow().getScaledWidth() / 2 - 93; // + 10;
                int poxY = event.getWindow().getScaledHeight() - 31;
                float mult = caps.currentMagan / (float) ElementaristicsCapability.MAX_MAGAN_BASE;
                Color color = new Color(145674);//)ColorUtil.convertIntToColor(SoulInit.getSoulFromId(caps.getSoulId()).getParticleColor());
                //if (caps.getTimeStunted() > 0) {
                //   color = ColorUtil.blend(color, Color.gray, 1 - Math.min(0.1 * (float) caps.getTimeStunted() / 10F, 0.8), Math.min(0.1 * (float) caps.getTimeStunted() / 10F, 0.8));
                //}
                //   mc.renderEngine.bindTexture(HUD_TEXTURE);
                drawColoredTexturedModalRect(posX, poxY, 0, 0, Math.round(186 * mult), 9, color, 1, HUD_TEXTURE);
            }
        }
    }


    public static void drawColoredTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, Color color, float alpha, ResourceLocation resTex) {
        GlStateManager.pushMatrix();
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        float zLevel = -90.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double) x, (double) (y + height), (double) zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos((double) (x + width), (double) (y + height), (double) zLevel).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos((double) (x + width), (double) y, (double) zLevel).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        bufferbuilder.pos((double) x, (double) y, (double) zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha).endVertex();
        Minecraft.getInstance().getTextureManager().bindTexture(resTex);
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
        GlStateManager.popMatrix();
    }
}
