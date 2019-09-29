package de.aelpecyem.elementaristics.client.fx.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;


@OnlyIn(Dist.CLIENT)
public class ParticleHandler{
    public static final ResourceLocation PARTICLE_TEX = new ResourceLocation(Elementaristics.MODID, "textures/misc/particle_base_1.png");

    private static final List<GlowParticle> PARTICLES = new CopyOnWriteArrayList<>();
    private static final List<GlowParticle> PARTICLES_DARK = new CopyOnWriteArrayList<>(); //use a larger texture with indexes!
    public static int range = 32;

    public static void spawnParticle(Supplier<GlowParticle> particle) {
        /*Config.EnumParticles particleAmount = Config.client.particleAmount;
        switch (particleAmount) {
            case STANDARD:
                break;
            case REDUCED:
                if (mc.world.rand.nextInt(3) != 0) {
                    return;
                }
                break;
            case MINIMAL:
                if (mc.world.rand.nextInt(10) != 0) {
                    return;
                }
                break;
        }*/
        boolean isDark = ColorUtil.isDark(particle.get().getRed(), particle.get().getGreen(), particle.get().getBlue());
        if (!isDark){
            PARTICLES.add(particle.get());
        }else{
            PARTICLES_DARK.add(particle.get());
        }
    }

    public static void updateParticles() {
        updateList(PARTICLES);
        updateList(PARTICLES_DARK);

        range = 32;
    }

    private static void updateList(List<GlowParticle> particles) {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.tick();
            if (!particle.isAlive())
                particles.remove(i);
        }
    }

    public static void renderParticles(float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        if (player != null) {

            ActiveRenderInfo info = new ActiveRenderInfo();
            //mc.getRenderManager().info;

           // System.out.println(mc.gameSettings.thirdPersonView);
            info.update(mc.world, player, false, false, partialTicks);
            float rotationX = 90;//MathHelper.cos(info.getYaw() * 0.017453292F); //todo get this to always match the yaw basically >:(
            float rotationZ = 0; //MathHelper.sin(info.getYaw() * 0.017453292F);
            float rotationYZ = -rotationZ * MathHelper.sin(info.getPitch() * 0.017453292F); //add - to rotationZ if that doesn't work
            float rotationXY = rotationX * MathHelper.sin(info.getPitch() * 0.017453292F);
            float rotationXZ = MathHelper.cos(info.getPitch() * 0.017453292F); //stuff


            Particle.interpPosX = info.getProjectedView().x;
            Particle.interpPosY = info.getProjectedView().y;
            Particle.interpPosZ = info.getProjectedView().z;


            GlStateManager.pushMatrix();

            GlStateManager.enableAlphaTest(); //enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.alphaFunc(516, 0.003921569F);
            GlStateManager.disableCull();
            GlStateManager.depthMask(false);
            mc.getTextureManager().bindTexture(PARTICLE_TEX);

            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            Tessellator t = Tessellator.getInstance();
            BufferBuilder buffer = t.getBuffer();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
            for (TexturedParticle particle : PARTICLES) {
                particle.renderParticle(buffer, info, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
            }
            t.draw();

            //todo, in order to draw darker particles, a new render type will be added, and chosen depending on the context
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
            for (TexturedParticle particle : PARTICLES_DARK) {
                particle.renderParticle(buffer, info, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
                //look at ParticleManager, so as to adapt the code
            }
            t.draw();


            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); //ONE_MINUS_SRC_ALPHA
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);

            GlStateManager.popMatrix(); //todo add the openGL of this code later
        }
    }


    public static int getParticleAmount(boolean depth) {
        return depth ? PARTICLES.size() : PARTICLES_DARK.size();
    }

    public static void clearParticles() {
        if (!PARTICLES.isEmpty())
            PARTICLES.clear();
        if (!PARTICLES_DARK.isEmpty())
            PARTICLES_DARK.clear();
    }
}
