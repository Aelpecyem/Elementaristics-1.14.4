package de.aelpecyem.elementaristics.client.fx.particle;


import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;


public class GlowParticle extends ModParticle { //todo work on all that stuff later, maybe optimize it by using actual RenderType code etc.
    public GlowParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.particleScale = 0.7F;
        this.particleGravity = 0.002f;
        this.particleRed = 0.7F; //(float) motionX;
        this.particleGreen = 0.3F;//(float) motionY;
        this.particleBlue = 0.5F;//(float) motionZ;
        this.maxAge = this.rand.nextInt(20) + 10;
        this.particleAlpha = 1.0F;
    }

    public GlowParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int lifetime, int color, float alpha, float scale, float gravity, EnumFadeMode fadeMode) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.particleScale = scale;
        this.particleGravity = gravity;
        this.particleRed = (((color >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleGreen = (((color >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleBlue = ((color & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.maxAge = lifetime;
        this.particleAlpha = alpha;
        //todo work on the "AI" and params stuff later, add spawning methods in ModParticles, move the handling done in ParticleHandler to ModParticles, tick stuff etc.
    }

    public GlowParticle(World world, double posX, double posY, double posZ, double posXTo, double posYTo, double posZTo, int lifetime, int color, float alpha, float scale, EnumFadeMode fadeMode) {
        super(world, posX, posY, posZ, 0, 0, 0);
        this.particleScale = scale;
        this.particleGravity = 0;
        this.particleRed = (((color >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleGreen = (((color >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleBlue = ((color & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.maxAge = lifetime;
        this.particleAlpha = alpha;
    }


    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ < this.maxAge && this.particleAlpha > 0.0F) {
            this.motionX += this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1);
            this.motionZ += this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1);
            this.motionY -= this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1);
            ;
            this.move(this.motionX, this.motionY, this.motionZ);
            if (this.age >= this.maxAge - 10 && this.particleAlpha > 0.01F) {
                this.particleAlpha -= 0.05F;
            }
        } else {
            this.setExpired();
        }

    }

    @Override
    public int getBrightnessForRender(float partialTick) {
        float f = ((float) this.age + partialTick) / (float) this.maxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightnessForRender(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int) (f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Elementaristics.MODID, "textures/particle/glow.png");//ParticleTexture.MAGIC_SMOKE[currentFrame];
    }

    public enum EnumFadeMode {
        OUT,
        IN,
        IN_OUT,
        NONE
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<IParticleData> {
        @Nullable
        @Override
        public Particle makeParticle(IParticleData iParticleData, World world, double v, double v1, double v2, double v3, double v4, double v5) {
            return new GlowParticle(world, v, v1, v2, v3, v4, v5);
        }
    }
}
