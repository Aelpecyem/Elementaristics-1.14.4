package de.aelpecyem.elementaristics.client.model.entity;

import de.aelpecyem.elementaristics.common.entity.CultistEntity;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class CultistModel extends EntityModel<CultistEntity> implements IHasArm, IHasHead{
    public RendererModel head;
    public RendererModel leg0;
    public RendererModel leg1;
    public RendererModel rightArm;
    public RendererModel leftArm;
    public RendererModel body0;
    public RendererModel body1;
    public RendererModel headChild;
    public RendererModel headChild_1;

    public CultistModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body1 = new RendererModel(this, 0, 38);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.body0 = new RendererModel(this, 16, 20);
        this.body0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body0.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.headChild_1 = new RendererModel(this, 24, 0);
        this.headChild_1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.headChild_1.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.leg0 = new RendererModel(this, 0, 22);
        this.leg0.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.headChild = new RendererModel(this, 32, 0);
        this.headChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild.addBox(-4.0F, -10.0F, -4.0F, 8, 12, 8, 0.1F);

        this.rightArm = new RendererModel(this, 40, 46);
        this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);

        this.leftArm = new RendererModel(this, 40, 46);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

        this.leg1 = new RendererModel(this, 0, 22);
        this.leg1.mirror = true;
        this.leg1.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.head.addChild(this.headChild_1);
        // this.head.addChild(this.headChild);
    }

    @Override
    public void render(CultistEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.body1.render(scale);
        this.body0.render(scale);
        this.leg0.render(scale);
        this.rightArm.render(scale);
        this.head.render(scale);
        this.leftArm.render(scale);
        this.leg1.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(CultistEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.body0.rotateAngleY = 0.0F;
        this.rightArm.rotationPointZ = 0.0F;
        this.rightArm.rotationPointX = -5.0F;
        this.leftArm.rotationPointZ = 0.0F;
        this.leftArm.rotationPointX = 5.0F;

        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg0.rotateAngleY = 0.0F;
        this.leg1.rotateAngleY = 0.0F;
        this.leg0.rotateAngleZ = 0.0F;
        this.leg1.rotateAngleZ = 0.0F;

        if (this.isSitting) {
            this.rightArm.rotateAngleX += -((float) Math.PI / 5F);
            this.leftArm.rotateAngleX += -((float) Math.PI / 5F);
            this.leg0.rotateAngleX = -1.4137167F;
            this.leg0.rotateAngleY = ((float) Math.PI / 10F);
            this.leg0.rotateAngleZ = 0.07853982F;
            this.leg1.rotateAngleX = -1.4137167F;
            this.leg1.rotateAngleY = -((float) Math.PI / 10F);
            this.leg1.rotateAngleZ = -0.07853982F;
        }

        this.rightArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;


        if (this.swingProgress > 0.0F) {

            float f1 = this.swingProgress;
            this.body0.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

            this.rightArm.rotationPointZ = MathHelper.sin(this.body0.rotateAngleY) * 5.0F;
            this.rightArm.rotationPointX = -MathHelper.cos(this.body0.rotateAngleY) * 5.0F;
            this.leftArm.rotationPointZ = -MathHelper.sin(this.body0.rotateAngleY) * 5.0F;
            this.leftArm.rotationPointX = MathHelper.cos(this.body0.rotateAngleY) * 5.0F;
            this.rightArm.rotateAngleY += this.body0.rotateAngleY;
            this.leftArm.rotateAngleY += this.body0.rotateAngleY;
            this.leftArm.rotateAngleX += this.body0.rotateAngleY;
        }


        this.body0.rotateAngleX = 0.0F;
        this.leg0.rotationPointZ = 0.1F;
        this.leg1.rotationPointZ = 0.1F;
        this.leg0.rotationPointY = 12.0F;
        this.leg1.rotationPointY = 12.0F;
        this.head.rotationPointY = 0.0F;
        /*if (((EntityCultist) entityIn.isCasting() || (((EntityCultist) entityIn).getNexus() != null && ((EntityCultist) entityIn).getNexus().getRite() != null && ((EntityCultist) entityIn).getNexus().getRiteTicks() > 0)) {
            doSpellCastingMovement(ageInTicks);
        }*/
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    }

    private void doSpellCastingMovement(float ageInTicks) {
        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.05F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.05F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.027F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.027F) * 0.05F;

        this.rightArm.rotationPointZ = 0.0F;
        this.rightArm.rotationPointX = -5.0F;
        this.leftArm.rotationPointZ = 0.0F;
        this.leftArm.rotationPointX = 5.0F;
        this.rightArm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
        this.leftArm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
        this.rightArm.rotateAngleZ = 2.3561945F;
        this.leftArm.rotateAngleZ = -2.3561945F;
        this.rightArm.rotateAngleY = 0.0F;
        this.leftArm.rotateAngleY = 0.0F;
    }

    @Override
    public void postRenderArm(float v, HandSide handSide) {
        this.getArm(handSide).postRender(0.0625F);
    }

    private RendererModel getArm(HandSide p_191216_1_) {
        return p_191216_1_ == HandSide.LEFT ? this.leftArm : this.rightArm;
    }

    public RendererModel func_205072_a() {
        return this.head;
    }
}
