package de.aelpecyem.elementaristics.client.fx.particle;

import de.aelpecyem.elementaristics.reg.ModParticles;
import de.aelpecyem.elementaristics.util.ColorUtil;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class ModParticle extends TexturedParticle implements IParticleRenderType {
    protected ModParticle(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    protected ModParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    abstract ResourceLocation getTexture();

    @Override
    protected float getMinU() {
        return 0f;
    }

    @Override
    protected float getMaxU() {
        return 1f;
    }

    @Override
    protected float getMinV() {
        return 0f;
    }

    @Override
    protected float getMaxV() {
        return 1f;
    }

    public IParticleRenderType getRenderType() {
        return ColorUtil.isDark(particleRed, particleGreen, particleBlue) ? ModParticles.DARKEN : ModParticles.BRIGHT;
    }

    @Override
    public void beginRender(BufferBuilder buffer, TextureManager textureManager) {
        RenderHelper.disableStandardItemLighting();
        textureManager.bindTexture(getTexture());
        buffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
    }

    @Override
    public void finishRender(Tessellator tess) {
        tess.draw();
    }
}
