package de.aelpecyem.elementaristics.client.model.shrines;

import de.aelpecyem.elementaristics.client.model.ModelBase;
import net.minecraft.client.renderer.entity.model.RendererModel;

/**
 * ModelWitch - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelWitch extends ModelBase {
    public RendererModel plinth00;
    public RendererModel pentacle00;
    public RendererModel pentacle01;
    public RendererModel pentacle02;
    public RendererModel plinth01;
    public RendererModel pentacle03;
    public RendererModel pentacle04;

    public ModelWitch() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.plinth00 = new RendererModel(this, 0, 24);
        this.plinth00.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.plinth00.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.pentacle00 = new RendererModel(this, 0, 4);
        this.pentacle00.setRotationPoint(0.0F, 8.7F, 0.0F);
        this.pentacle00.addBox(-1.0F, 0.0F, -0.48F, 2, 13, 1, 0.0F);
        this.setRotateAngle(pentacle00, 0.0F, 0.0F, 0.2617993877991494F);
        this.pentacle02 = new RendererModel(this, 3, 0);
        this.pentacle02.setRotationPoint(0.0F, 14.1F, 0.0F);
        this.pentacle02.addBox(-6.5F, -1.0F, -0.52F, 13, 1, 1, 0.0F);
        this.pentacle03 = new RendererModel(this, 8, 4);
        this.pentacle03.setRotationPoint(5.7F, -0.1F, 0.0F);
        this.pentacle03.addBox(-0.5F, -0.5F, -0.51F, 1, 11, 1, 0.0F);
        this.setRotateAngle(pentacle03, 0.0F, 0.0F, 0.8726646259971648F);
        this.pentacle04 = new RendererModel(this, 8, 4);
        this.pentacle04.setRotationPoint(-5.7F, -0.1F, 0.0F);
        this.pentacle04.addBox(-0.5F, -0.5F, -0.49F, 1, 11, 1, 0.0F);
        this.setRotateAngle(pentacle04, 0.0F, 0.0F, -0.8726646259971648F);
        this.plinth01 = new RendererModel(this, 0, 24);
        this.plinth01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.plinth01.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.setRotateAngle(plinth01, 0.0F, 3.141592653589793F, 0.0F);
        this.pentacle01 = new RendererModel(this, 0, 4);
        this.pentacle01.setRotationPoint(0.0F, 8.7F, 0.0F);
        this.pentacle01.addBox(-1.0F, -0.0F, -0.5F, 2, 13, 1, 0.0F);
        this.setRotateAngle(pentacle01, 0.0F, 0.0F, -0.2617993877991494F);
        this.pentacle02.addChild(this.pentacle03);
        this.pentacle02.addChild(this.pentacle04);
        this.plinth00.addChild(this.plinth01);
    }

    @Override
    public void render(float scale) {
        this.plinth00.render(scale);
        this.pentacle00.render(scale);
        this.pentacle02.render(scale);
        this.pentacle01.render(scale);
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
