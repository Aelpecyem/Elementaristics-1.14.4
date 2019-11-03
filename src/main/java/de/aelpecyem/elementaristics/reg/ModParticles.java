package de.aelpecyem.elementaristics.reg;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.particle.GlowParticle;
import de.aelpecyem.elementaristics.client.particle.ModParticle;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;


@ObjectHolder(Elementaristics.MODID)
@Mod.EventBusSubscriber(modid = Elementaristics.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {
    //@ObjectHolder(Elementaristics.MODID + ":glow")
    //public static final ParticleType GLOW = new BasicParticleType(true).setRegistryName(Elementaristics.MODID, "glow");

    @OnlyIn(Dist.CLIENT)
    public void init(){
        //Elementaristics.LOGGER.log(Level.INFO, "Registering particle factories...")
        // ParticleManager particles = Minecraft.getInstance().particles;
        //particles.registerFactory(GLOW, (ParticleManager.IParticleMetaFactory) BubbleParticle.Factory::new); //todo fix the particle factory
    }

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> register) {
        //Elementaristics.LOGGER.log(Level.INFO, "Registering particles...");
        //register.getRegistry().register(GLOW);
    }

    //-----PARTICLE SPAWNING---------
    public void spawnParticle(GlowParticle particle) {
        Handler.spawnParticle(() -> particle);
    }

    public void spawnStandardParticle(World world, double x, double y, double z, int color) {
        GlowParticle particle = new GlowParticle(world, x, y, z, world.rand.nextGaussian() * 0.02D,
                world.rand.nextGaussian() * 0.02D,
                world.rand.nextGaussian() * 0.02D, 120, color, 0.9F, 0.8F, 0, true, true, GlowParticle.EnumFadeMode.OUT); // out
        spawnParticle(particle);
    }

    public void spawnStandardParticle(World world, double x, double y, double z, @Nullable ParticleMode mode, double speedMult) {
        GlowParticle particle = new GlowParticle(world, x, y, z, world.rand.nextGaussian() * speedMult,
                world.rand.nextGaussian() * speedMult,
                world.rand.nextGaussian() * speedMult, 400, 16711680, 0.9F, 0.8F, 0, true, false, GlowParticle.EnumFadeMode.OUT); //in out
        if (mode != null)
            particle.setMode(mode);
        spawnParticle(particle);
    }

    public void spawnEntityParticles(Entity entityIn, int color, @Nullable ParticleMode mode, double speedMult) {
        double motionX = entityIn.world.rand.nextGaussian() * speedMult;
        double motionY = entityIn.world.rand.nextGaussian() * speedMult;
        double motionZ = entityIn.world.rand.nextGaussian() * speedMult;
        GlowParticle particle = new GlowParticle(
                entityIn.world,
                entityIn.posX + entityIn.world.rand.nextFloat() * entityIn.getWidth()
                        * 2.0F - entityIn.getWidth(),
                entityIn.posY + 0.5D + entityIn.world.rand.nextFloat()
                        * entityIn.getHeight(),
                entityIn.posZ + entityIn.world.rand.nextFloat() * entityIn.getWidth()
                        * 2.0F - entityIn.getWidth(),
                motionX,
                motionY,
                motionZ,
                160, color, 1F, 0.1F + entityIn.world.rand.nextFloat() / 40F, 0F, true, true, GlowParticle.EnumFadeMode.OUT);
        if (mode != null)
            particle.setMode(mode);
        spawnParticle(particle);
    }

    public void spawnAmbientBlockParticles(World world, BlockPos pos, int color) {
        double motionX = world.rand.nextGaussian() * 0.001D;
        double motionY = world.rand.nextGaussian() * 0.001D;
        double motionZ = world.rand.nextGaussian() * 0.001D;
        GlowParticle particle = new GlowParticle(world, pos.getX() + world.rand.nextFloat(), pos.getY() + world.rand.nextFloat(), pos.getZ() + world.rand.nextFloat(), motionX, motionY, motionZ, 120, color, 0.9F, 0.1F, 0, false, false, GlowParticle.EnumFadeMode.IN_OUT);
        spawnParticle(particle);
    }

    //-----PARTICLE RENDER STUFF-----
    public static class Handler {
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

        public static int getParticleAmount(boolean depth) {
            return PARTICLES.size();
        }

        public static void clearParticles() {
            if (!PARTICLES.isEmpty())
                PARTICLES.clear();
        }
    }

    public static class RenderTypes {
        public static IParticleRenderType BRIGHT = new IParticleRenderType() {
            @Override
            public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
                GlStateManager.enableAlphaTest();
                GlStateManager.enableBlend();
                GlStateManager.alphaFunc(516, 0.003921569F);
                GlStateManager.disableCull();
                GlStateManager.depthMask(false);

                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
                bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP); //POSITION_TEX_COLOR PARTICLE_POSITION_TEX_COLOR_LMAP
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

        public static IParticleRenderType DARKEN = new IParticleRenderType() {
            @Override
            public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
                GlStateManager.enableAlphaTest();
                GlStateManager.enableBlend();
                GlStateManager.alphaFunc(516, 0.003921569F);
                GlStateManager.disableCull();
                GlStateManager.depthMask(false);

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
}
