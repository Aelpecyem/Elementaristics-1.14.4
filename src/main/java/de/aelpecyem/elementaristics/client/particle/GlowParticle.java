package de.aelpecyem.elementaristics.client.particle;


import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleMode;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleModes;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;


public class GlowParticle extends ModParticle { //todo work on all that stuff later, maybe optimize it by using actual RenderType code etc
    public float distanceTravelled = 0;
    public boolean followPosition = false;
    public double xTo, yTo, zTo;
    public ParticleMode mode = ParticleModes.STANDARD;
    public EnumFadeMode fadeMode = EnumFadeMode.NONE;
    public boolean shrinkAlpha = false;
    public float desiredScale;

    public GlowParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.particleScale = 0.7F;
        this.particleGravity = 0.002f;
        this.particleRed = 0.7F; //(float) motionX;
        this.particleGreen = 0.3F;//(float) motionY;
        this.particleBlue = 0.5F;//(float) motionZ;
        this.maxAge = this.rand.nextInt(20) + 10;
        this.particleAlpha = 1.0F;
        this.desiredScale = particleScale;
    }

    public GlowParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int lifetime, int color, float alpha, float scale, float gravity, boolean collision, boolean shrinkWithAlpha, EnumFadeMode fadeMode) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.particleScale = scale;
        this.particleGravity = gravity;
        this.particleRed = (((color >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleGreen = (((color >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleBlue = ((color & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.maxAge = lifetime;
        this.particleAlpha = alpha; //todo fix alpha
        this.canCollide = collision;
        this.fadeMode = fadeMode;
        this.shrinkAlpha = shrinkWithAlpha;
        this.desiredScale = particleScale;
        //todo work on the "AI" and params stuff later, add spawning methods in ModParticles, move the handling done in ParticleHandler to ModParticles, tick stuff etc.
    }

    public GlowParticle(World world, double posX, double posY, double posZ, double posXTo, double posYTo, double posZTo, int lifetime, int color, float alpha, float scale, boolean collision, boolean shrinkWithAlpha, EnumFadeMode fadeMode) {
        super(world, posX, posY, posZ, 0, 0, 0);
        this.particleScale = scale;
        this.particleGravity = 0;
        this.particleRed = (((color >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleGreen = (((color >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.particleBlue = ((color & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.maxAge = lifetime;
        this.particleAlpha = alpha;
        this.canCollide = collision;
        this.followPosition = true;
        this.xTo = posXTo;
        this.yTo = posYTo;
        this.zTo = posZTo;
        this.fadeMode = fadeMode;
        this.shrinkAlpha = shrinkWithAlpha;
        this.desiredScale = particleScale;
    }

    public ParticleMode getMode() {
        return mode;
    }

    public void setMode(ParticleMode mode) {
        this.mode = mode;
    }

    //todo create a method to move relative to current movement (multiply vectors???? no clue) probably test that then...

    @Override
    public void move(double x, double y, double z) {
        super.move(x, y, z);
        distanceTravelled += x + y + z;
    }

    public void moveRelative(double x, double y, double z) {
        Vec3d moveVec = new Vec3d(x, y, z);
        Vec3d curMoveVec = new Vec3d(motionX, motionY, motionZ);
        Vec3d actualMoveVec = moveVec.mul(curMoveVec);
        motionX = actualMoveVec.x;
        motionY = actualMoveVec.y;
        motionZ = actualMoveVec.z;
    }

    @Override
    public void tick() {
        if (mode != null) {
            mode.tick(this);
        }
        if (mode == null || !mode.overridesCompletely()) {
            if (followPosition) {
                double velX = motionX = (xTo - posX) / 20;
                double velY = motionY = (yTo - posY) / 20;
                double velZ = motionZ = (zTo - posZ) / 20;
                double vel = (Math.abs(velX) + Math.abs(velY) + Math.abs(velZ)) / 3F;
                if (vel > 0.1) {
                    double percentageX = velX / vel;
                    double percentageY = velY / vel;
                    double percentageZ = velZ / vel;
                    motionX = 0.1 * percentageX;
                    motionY = 0.1 * percentageY;
                    motionZ = 0.1 * percentageZ;
                } else {
                    motionX = velX;
                    motionY = velY;
                    motionZ = velZ;
                }
            }

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            this.age++;
            if (this.age++ >= this.maxAge) {
                this.setExpired();
            } else {
                this.motionY -= 0.04D * (double) this.particleGravity;
                this.move(this.motionX, this.motionY, this.motionZ);

                float lifeRatio = (float) this.age / (float) this.maxAge; //in-out stuff
                lifeRatio = fadeMode == EnumFadeMode.IN_OUT ? 1 - (0.5F - Math.abs(0.5F - lifeRatio)) * 2 : fadeMode == EnumFadeMode.IN ? 1 - lifeRatio : lifeRatio;
                System.out.println(lifeRatio);
                if (this.fadeMode != EnumFadeMode.NONE) { //todo fix particle alpha
                    this.particleAlpha = 1;//particleAlpha - (lifeRatio * particleAlpha); //possibly the alpha wasn't actually broken?
                }
                if (this.fadeMode != EnumFadeMode.NONE && this.shrinkAlpha) {
                    this.particleScale = this.desiredScale * (1 - lifeRatio);
                }

            }
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            if (this.age++ < this.maxAge) {
                this.move(this.motionX, this.motionY, this.motionZ);
            } else {
                this.setExpired();
            }
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
