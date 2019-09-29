package de.aelpecyem.elementaristics.reg;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.fx.particle.MagicGlowParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;


@ObjectHolder(Elementaristics.MODID)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {
    @ObjectHolder(Elementaristics.MODID + ":glow")
    public static final BasicParticleType GLOW = new BasicParticleType(false);

    @OnlyIn(Dist.CLIENT)
    public void init(){
        Elementaristics.LOGGER.log(Level.INFO, "Registering particle factories...");
        ParticleManager particles = Minecraft.getInstance().particles;

        //particles.registerFactory(GLOW, MagicGlowParticle.Factory::new); todo fix the particle stuff later, probabs by redoing it :(
    }

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> register) {
        Elementaristics.LOGGER.log(Level.INFO, "Registering particles...");

        //register.getRegistry().register(GLOW.setRegistryName("glow"));
    }



    IParticleRenderType BRIGHT = new IParticleRenderType() {
        @Override
        public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
            GlStateManager.enableAlphaTest(); //enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.alphaFunc(516, 0.003921569F);
            GlStateManager.disableCull();
            GlStateManager.depthMask(false);

            //bind texture if weird

            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        }

        @Override
        public void finishRender(Tessellator tessellator) {
            tessellator.draw();

            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); //ONE_MINUS_SRC_ALPHA
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
        }
        @Override
        public String toString() {
            return "ElEM_BRIGHT";
        }
    };

    IParticleRenderType DARKEN = new IParticleRenderType() {
        @Override
        public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
            GlStateManager.enableAlphaTest(); //enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.alphaFunc(516, 0.003921569F);
            GlStateManager.disableCull();
            GlStateManager.depthMask(false);

            //todo bind texture if weird, look at IParticleRenderType for that

            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        }

        @Override
        public void finishRender(Tessellator tessellator) {
            tessellator.draw();

            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); //ONE_MINUS_SRC_ALPHA
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
        }
    };
}
