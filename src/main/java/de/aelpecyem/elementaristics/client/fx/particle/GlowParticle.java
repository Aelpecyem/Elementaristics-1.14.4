package de.aelpecyem.elementaristics.client.fx.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class GlowParticle extends SpriteTexturedParticle {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Elementaristics.MODID, "textures/misc/particle_base_1.png");
    //the rendering with the ParticleHandler class might not be needed in 1.14, but idk, we'll see
    //also, for the 1.14 port, change the constructors and add option for also fading IN and not only out; this should be false for spells etc, but true for statues
    private final float desiredScale;
    private final boolean fade; //fade instead of boolean will be a mode (0: no fading, 1: fade out, 3: fade in then out, 4: fade int
    private final boolean followPosition;
    private final double xTo, yTo, zTo;
    private final boolean shrink;
    //cut out a few of these

    public GlowParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int color, float scale, int maxAge, float gravity, boolean collision, boolean fade, float alpha, boolean shrink) {
        super(world, posX, posY, posZ);
        this.desiredScale = scale;
        this.maxAge = maxAge;

        this.canCollide = collision;
        this.particleGravity = gravity;
        this.fade = fade;

        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;

        followPosition = false;
        this.shrink = shrink;
        xTo = 0;
        yTo = 0;
        zTo = 0;

        float r = (((color >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        float g = (((color >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        float b = ((color & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.setColor(r, g, b);

        this.particleAlpha = alpha;
        this.particleScale = desiredScale; //setSize(desiredScale, desiredScale);
        //this.multipleParticleScaleBy(desiredScale); //particleScale = this.desiredScale;

        //setParticleTextureIndex(0);
        this.sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(new ResourceLocation(Elementaristics.MODID, "particle/glow").toString());

    }

    public GlowParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int color, float scale, int maxAge, float gravity, boolean collision, boolean fade, boolean shrink, boolean followPosition, double xTo, double yTo, double zTo) {
        super(world, posX, posY, posZ);
        this.desiredScale = scale;
        this.maxAge = maxAge;
        this.canCollide = collision;
        this.particleGravity = gravity;
        this.fade = fade;
        this.shrink = shrink;

        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.followPosition = followPosition;
        this.xTo = xTo;
        this.yTo = yTo;
        this.zTo = zTo;

        float r = (((color >> 16) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        float g = (((color >> 8) & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        float b = ((color & 255) / 255F) * (1F - this.rand.nextFloat() * 0.25F);
        this.setColor(r, g, b);


        this.particleAlpha = 0.5F;
        this.particleScale = desiredScale;
        setSprite(Minecraft.getInstance().getTextureMap().getAtlasSprite(new ResourceLocation(Elementaristics.MODID, "particle/glow").toString()));
        //this.setSize(desiredScale, desiredScale);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public int getBrightnessForRender(float f) {
        return 15 << 20 | 15 << 4;
    }

    public float getRed(){
        return particleRed;
    }

    public float getGreen(){
        return particleGreen;
    }

    public float getBlue(){
        return particleBlue;
    }



    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            GlowParticle glowParticle = new GlowParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, worldIn.rand.nextInt(16777215), worldIn.rand.nextInt(5) + 5, 200, 0, false, true, 0.9F, true);
            glowParticle.selectSpriteRandomly(this.spriteSet);
            return glowParticle;
        }
    }
}
