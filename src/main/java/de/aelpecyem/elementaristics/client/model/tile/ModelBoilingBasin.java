package de.aelpecyem.elementaristics.client.model.tile;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * boiling_basin_2 - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelBoilingBasin extends ModelBase {
    public static final ResourceLocation FLUID_TEX = new ResourceLocation(Elementaristics.MODID, "textures/block/boiling_basin_fluid.png");
    public static final ResourceLocation GLASS_TEX = new ResourceLocation(Elementaristics.MODID, "textures/block/boiling_basin_glass.png");
    public static final ResourceLocation FRAME_TEX = new ResourceLocation(Elementaristics.MODID, "textures/block/boiling_basin.png");
    //add the animated parts later I guess
    public RendererModel basinBottom;
    public RendererModel vesselFront;
    public RendererModel vesselBack;
    public RendererModel vesselLeft;
    public RendererModel vesselRight;
    public RendererModel fluid;

    public ModelBoilingBasin() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.vesselLeft = new RendererModel(this, 0, 10);
        this.vesselLeft.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselLeft.addBox(-5.0F, 0.0F, -4.0F, 1, 6, 8, 0.0F);
        this.vesselRight = new RendererModel(this, 0, 33);
        this.vesselRight.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselRight.addBox(4.0F, 0.0F, -4.0F, 1, 6, 8, 0.0F);
        this.fluid = new RendererModel(this, 0, 49);
        this.fluid.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.fluid.addBox(-4.0F, 0.0F, -4.0F, 8, 5, 8, 0.0F);
        this.vesselFront = new RendererModel(this, 19, 17);
        this.vesselFront.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselFront.addBox(-4.0F, 0.0F, -5.0F, 8, 6, 1, 0.0F);
        this.vesselBack = new RendererModel(this, 19, 40);
        this.vesselBack.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselBack.addBox(-4.0F, 0.0F, 4.0F, 8, 6, 1, 0.0F);
        this.basinBottom = new RendererModel(this, 0, 0);
        this.basinBottom.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.basinBottom.addBox(-4.0F, 0.0F, -4.0F, 8, 1, 8, 0.0F);
    }

    public void render(float scale, Color color, float fluidHeight) {
        GlStateManager.scaled(0.99F, 0.99F, 0.99F);
        GlStateManager.translated(0, 0.011, 0);
        Minecraft.getInstance().getTextureManager().bindTexture(GLASS_TEX);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableCull();
        //if (color != null)
          //  RenderUtil.renderCube(Minecraft.getInstance().getTextureMap().getSprite(ClientProxy.THICC_WATER), 0, 0.3125d, 0, 0.25d, 0, 0.25d, 0.75d, fluidHeight, 0.75d, color != null ? color.getRGB() : 0, Fluids.WATER.getAttributes().getLuminosity());
        Minecraft.getInstance().getTextureManager().bindTexture(GLASS_TEX);
        this.vesselLeft.render(scale);
        this.vesselRight.render(scale);
        this.basinBottom.render(scale);
        this.vesselFront.render(scale);
        this.vesselBack.render(scale);
        GlStateManager.enableCull();
        GlStateManager.enableDepthTest();
        GlStateManager.disableBlend();
        super.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
