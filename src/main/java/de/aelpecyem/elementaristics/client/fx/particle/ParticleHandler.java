package de.aelpecyem.elementaristics.client.fx.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;


@OnlyIn(Dist.CLIENT)
public class ParticleHandler{
    private static final List<Particle> PARTICLES = new CopyOnWriteArrayList<>();

    public static void spawnParticle(Supplier<GlowParticle> particle) {
        /*Config.EnumParticles particleAmount = Config.client.particleAmount; todo: implement this once i finally get myself to add a config
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
        PARTICLES.add(particle.get());
    }

    public static void updateParticles() {
        updateList(PARTICLES);
    }

    private static void updateList(List<Particle> particles) {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.tick();
            if (!particle.isAlive())
                particles.remove(i);
        }
    }

    public static void renderParticle(float parTicks) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        if (player != null) {
            ActiveRenderInfo info = mc.getRenderManager().info;//new ActiveRenderInfo();
            float rotationX = MathHelper.cos(info.getYaw() * 0.017453292F);
            float rotationYZ = MathHelper.sin(info.getYaw() * 0.017453292F);
            float rotationXY = -rotationYZ * MathHelper.sin(info.getPitch() * 0.017453292F);
            float rotationXZ = rotationX * MathHelper.sin(info.getPitch() * 0.017453292F);
            float rotationZ = MathHelper.cos(info.getPitch() * 0.017453292F);

            Tessellator tesser = Tessellator.getInstance();
            BufferBuilder buffer = tesser.getBuffer();
            for (Particle particle : PARTICLES) {
                if (particle instanceof ModParticle)
                    mc.textureManager.bindTexture(((ModParticle) particle).getTexture());
                particle.getRenderType().beginRender(buffer, mc.textureManager);
                particle.renderParticle(buffer, info, parTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
                particle.getRenderType().finishRender(tesser);
            }

        }
    }

    /* public static void renderParticles(float partialTicks) {
         Minecraft mc = Minecraft.getInstance();
         PlayerEntity player = mc.player;

         if (player != null) {

             ActiveRenderInfo info = new ActiveRenderInfo();
             info.update(mc.world, player, false, false, partialTicks);
             float rotationX = MathHelper.cos(info.getYaw() * 0.017453292F); //todo get this to always match the yaw basically >:(
             float rotationZ = MathHelper.sin(info.getYaw() * 0.017453292F);
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
             for (Particle particle : PARTICLES) {
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

 */
    public static int getParticleAmount(boolean depth) {
        return PARTICLES.size();
    }

    public static void clearParticles() {
        if (!PARTICLES.isEmpty())
            PARTICLES.clear();
    }
}
