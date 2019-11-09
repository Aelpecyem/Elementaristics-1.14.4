package de.aelpecyem.elementaristics.client.model;

import com.mojang.blaze3d.platform.GlStateManager;
import de.aelpecyem.elementaristics.util.RenderUtil;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

/**
 * boiling_basin_2 - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelBoilingBasin extends ModelBase {
    public RendererModel basinBottom;
    public RendererModel frameFront;
    public RendererModel frameBack;
    public RendererModel frameRight;
    public RendererModel frameLeft;
    public RendererModel legFrontRight;
    public RendererModel legFrontLeft;
    public RendererModel legBackRight;
    public RendererModel hotplate;
    public RendererModel legBackLeft;
    public RendererModel vesselFront;
    public RendererModel vesselBack;
    public RendererModel vesselLeft;
    public RendererModel vesselRight;
    public RendererModel footFrontRight;
    public RendererModel footBackLeft;
    public RendererModel footFrontLeft;
    public RendererModel footBackRight;
    public RendererModel fluid;

    public ModelBoilingBasin() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.vesselLeft = new RendererModel(this, 0, 10);
        this.vesselLeft.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselLeft.addBox(-5.0F, 0.0F, -4.0F, 1, 6, 8, 0.0F);
        this.legFrontRight = new RendererModel(this, 0, 0);
        this.legFrontRight.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.legFrontRight.addBox(4.0F, 0.0F, -5.0F, 1, 4, 1, 0.0F);
        this.vesselRight = new RendererModel(this, 0, 33);
        this.vesselRight.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselRight.addBox(4.0F, 0.0F, -4.0F, 1, 6, 8, 0.0F);
        this.footBackRight = new RendererModel(this, 25, 0);
        this.footBackRight.setRotationPoint(4.5F, 23.0F, 4.5F);
        this.footBackRight.addBox(-0.2F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(footBackRight, 0.0F, -0.7853981633974483F, 0.0F);
        this.frameRight = new RendererModel(this, 27, 5);
        this.frameRight.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.frameRight.addBox(4.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F);
        this.footFrontRight = new RendererModel(this, 25, 0);
        this.footFrontRight.setRotationPoint(4.7F, 23.0F, -4.7F);
        this.footFrontRight.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(footFrontRight, 0.0F, 0.7853981633974483F, 0.0F);
        this.frameBack = new RendererModel(this, 10, 10);
        this.frameBack.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.frameBack.addBox(-5.0F, 0.0F, 4.0F, 10, 1, 1, 0.0F);
        this.legFrontLeft = new RendererModel(this, 0, 0);
        this.legFrontLeft.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.legFrontLeft.addBox(-5.0F, 0.0F, -5.0F, 1, 4, 1, 0.0F);
        this.footFrontLeft = new RendererModel(this, 25, 0);
        this.footFrontLeft.setRotationPoint(-4.6F, 23.0F, -4.6F);
        this.footFrontLeft.addBox(-1.7F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(footFrontLeft, 0.0F, -0.7853981633974483F, 0.0F);
        this.fluid = new RendererModel(this, 0, 49);
        this.fluid.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.fluid.addBox(-4.0F, 0.0F, -4.0F, 8, 5, 8, 0.0F);
        this.hotplate = new RendererModel(this, 0, 25);
        this.hotplate.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.hotplate.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
        this.legBackLeft = new RendererModel(this, 0, 0);
        this.legBackLeft.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.legBackLeft.addBox(-5.0F, 0.0F, 4.0F, 1, 4, 1, 0.0F);
        this.vesselFront = new RendererModel(this, 19, 17);
        this.vesselFront.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselFront.addBox(-4.0F, 0.0F, -5.0F, 8, 6, 1, 0.0F);
        this.vesselBack = new RendererModel(this, 19, 40);
        this.vesselBack.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.vesselBack.addBox(-4.0F, 0.0F, 4.0F, 8, 6, 1, 0.0F);
        this.frameFront = new RendererModel(this, 10, 10);
        this.frameFront.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.frameFront.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 1, 0.0F);
        this.frameLeft = new RendererModel(this, 27, 5);
        this.frameLeft.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.frameLeft.addBox(-5.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F);
        this.legBackRight = new RendererModel(this, 0, 0);
        this.legBackRight.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.legBackRight.addBox(4.0F, 0.0F, 4.0F, 1, 4, 1, 0.0F);
        this.footBackLeft = new RendererModel(this, 25, 0);
        this.footBackLeft.setRotationPoint(-5.1F, 23.0F, 5.1F);
        this.footBackLeft.addBox(-1.0F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(footBackLeft, 0.0F, 0.7853981633974483F, 0.0F);
        this.basinBottom = new RendererModel(this, 0, 0);
        this.basinBottom.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.basinBottom.addBox(-4.0F, 0.0F, -4.0F, 8, 1, 8, 0.0F);
    }

    @Override
    public void render(float scale) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.legFrontRight.render(scale);
        this.footBackRight.render(scale);
        this.frameRight.render(scale);
        this.footFrontRight.render(scale);
        this.frameBack.render(scale);
        this.legFrontLeft.render(scale);
        this.footFrontLeft.render(scale);
        this.fluid.render(scale);
        this.hotplate.render(scale);
        this.legBackLeft.render(scale);
        this.frameFront.render(scale);
        this.frameLeft.render(scale);
        this.legBackRight.render(scale);
        this.footBackLeft.render(scale);


        GlStateManager.disableCull();
        GlStateManager.disableAlphaTest();
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
