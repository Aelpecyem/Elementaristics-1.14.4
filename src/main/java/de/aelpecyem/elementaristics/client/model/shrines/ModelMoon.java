package de.aelpecyem.elementaristics.client.model.shrines;

import de.aelpecyem.elementaristics.client.model.ModelBase;
import net.minecraft.client.renderer.entity.model.RendererModel;

/**
 * ModelMoon - cybecat5555
 * Created using Tabula 7.0.1
 */
public class ModelMoon extends ModelBase {
    public RendererModel plinth00;
    public RendererModel moon00;
    public RendererModel plinth01;
    public RendererModel moon01;
    public RendererModel moon02;
    public RendererModel moon03;
    public RendererModel moon04;

    public ModelMoon() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.moon00 = new RendererModel(this, 0, 0);
        this.moon00.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.moon00.addBox(-3.0F, 0.0F, -1.0F, 6, 1, 2, 0.0F);
        this.plinth00 = new RendererModel(this, 0, 24);
        this.plinth00.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.plinth00.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.moon04 = new RendererModel(this, 0, 0);
        this.moon04.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.moon04.addBox(-3.0F, -0.5F, -1.0F, 6, 1, 2, 0.0F);
        this.plinth01 = new RendererModel(this, 0, 24);
        this.plinth01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.plinth01.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.setRotateAngle(plinth01, 0.0F, 3.141592653589793F, 0.0F);
        this.moon01 = new RendererModel(this, 0, 4);
        this.moon01.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.moon01.addBox(-4.0F, -0.5F, -1.0F, 8, 1, 2, 0.0F);
        this.moon02 = new RendererModel(this, 0, 9);
        this.moon02.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.moon02.addBox(-4.5F, -2.5F, -1.0F, 9, 5, 2, 0.0F);
        this.moon03 = new RendererModel(this, 0, 4);
        this.moon03.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.moon03.addBox(-4.0F, -0.5F, -1.0F, 8, 1, 2, 0.0F);
        this.moon03.addChild(this.moon04);
        this.plinth00.addChild(this.plinth01);
        this.moon00.addChild(this.moon01);
        this.moon01.addChild(this.moon02);
        this.moon02.addChild(this.moon03);
    }

    @Override
    public void render(float scale) {
        this.moon00.render(scale);
        this.plinth00.render(scale);
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
