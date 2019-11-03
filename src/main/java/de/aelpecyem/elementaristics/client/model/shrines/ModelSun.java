package de.aelpecyem.elementaristics.client.model.shrines;

import de.aelpecyem.elementaristics.client.model.ModelBase;
import net.minecraft.client.renderer.entity.model.RendererModel;

/**
 * ModelSun - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelSun extends ModelBase {
    public RendererModel plinth00;
    public RendererModel sun00;
    public RendererModel plinth01;
    public RendererModel sun01;
    public RendererModel sun02;
    public RendererModel sun03;
    public RendererModel sun04;
    public RendererModel ray00;
    public RendererModel ray01L;
    public RendererModel ray01R;
    public RendererModel ray01L_1;
    public RendererModel ray02R;
    public RendererModel ray02L;
    public RendererModel ray02R_1;
    public RendererModel ray03L;
    public RendererModel ray03R;
    public RendererModel ray04L;
    public RendererModel ray04R;
    public RendererModel ray05L;
    public RendererModel ray05R;

    public ModelSun() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.sun00 = new RendererModel(this, 0, 0);
        this.sun00.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.sun00.addBox(-3.0F, 0.0F, -1.0F, 6, 1, 2, 0.0F);
        this.plinth00 = new RendererModel(this, 0, 24);
        this.plinth00.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.plinth00.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.ray01L_1 = new RendererModel(this, 24, 0);
        this.ray01L_1.setRotationPoint(2.9F, 0.6F, 0.0F);
        this.ray01L_1.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(ray01L_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.ray02L = new RendererModel(this, 24, 0);
        this.ray02L.setRotationPoint(4.0F, 2.0F, 0.0F);
        this.ray02L.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(ray02L, 0.0F, 0.0F, 1.1780972450961724F);
        this.ray04L = new RendererModel(this, 24, 0);
        this.ray04L.setRotationPoint(4.0F, 5.0F, 0.0F);
        this.ray04L.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(ray04L, 0.0F, 0.0F, 1.9634954084936207F);
        this.sun04 = new RendererModel(this, 0, 0);
        this.sun04.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.sun04.addBox(-3.0F, -0.5F, -1.0F, 6, 1, 2, 0.0F);
        this.sun03 = new RendererModel(this, 0, 4);
        this.sun03.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.sun03.addBox(-4.0F, -0.5F, -1.0F, 8, 1, 2, 0.0F);
        this.ray02R = new RendererModel(this, 24, 0);
        this.ray02R.setRotationPoint(-2.9F, 0.6F, 0.0F);
        this.ray02R.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(ray02R, 0.0F, 0.0F, -0.7853981633974483F);
        this.ray02R_1 = new RendererModel(this, 24, 0);
        this.ray02R_1.setRotationPoint(-4.0F, 2.0F, 0.0F);
        this.ray02R_1.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(ray02R_1, 0.0F, 0.0F, -1.1780972450961724F);
        this.ray04R = new RendererModel(this, 24, 0);
        this.ray04R.setRotationPoint(-4.0F, 5.0F, 0.0F);
        this.ray04R.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(ray04R, 0.0F, 0.0F, -1.9634954084936207F);
        this.ray01L = new RendererModel(this, 24, 0);
        this.ray01L.setRotationPoint(1.4F, 0.0F, 0.0F);
        this.ray01L.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(ray01L, 0.0F, 0.0F, 0.39269908169872414F);
        this.ray03R = new RendererModel(this, 24, 0);
        this.ray03R.setRotationPoint(-4.0F, 3.5F, 0.0F);
        this.ray03R.addBox(-0.5F, -5.5F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(ray03R, 0.0F, 0.0F, -1.5707963267948966F);
        this.sun01 = new RendererModel(this, 0, 4);
        this.sun01.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.sun01.addBox(-4.0F, -0.5F, -1.0F, 8, 1, 2, 0.0F);
        this.ray05R = new RendererModel(this, 24, 0);
        this.ray05R.setRotationPoint(-4.0F, 6.8F, 0.0F);
        this.ray05R.addBox(-0.5F, -2.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(ray05R, 0.0F, 0.0F, -2.356194490192345F);
        this.ray05L = new RendererModel(this, 24, 0);
        this.ray05L.setRotationPoint(4.0F, 6.8F, 0.0F);
        this.ray05L.addBox(-0.5F, -2.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(ray05L, 0.0F, 0.0F, 2.356194490192345F);
        this.sun02 = new RendererModel(this, 0, 9);
        this.sun02.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.sun02.addBox(-4.5F, -2.5F, -1.0F, 9, 5, 2, 0.0F);
        this.ray03L = new RendererModel(this, 24, 0);
        this.ray03L.setRotationPoint(4.0F, 3.5F, 0.0F);
        this.ray03L.addBox(-0.5F, -5.5F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(ray03L, 0.0F, 0.0F, 1.5707963267948966F);
        this.ray00 = new RendererModel(this, 24, 0);
        this.ray00.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ray00.addBox(-0.5F, -5.5F, -0.5F, 1, 6, 1, 0.0F);
        this.ray01R = new RendererModel(this, 24, 0);
        this.ray01R.setRotationPoint(-1.4F, 0.0F, 0.0F);
        this.ray01R.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(ray01R, 0.0F, 0.0F, -0.39269908169872414F);
        this.plinth01 = new RendererModel(this, 0, 24);
        this.plinth01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.plinth01.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.setRotateAngle(plinth01, 0.0F, 3.141592653589793F, 0.0F);
        this.sun04.addChild(this.ray01L_1);
        this.sun04.addChild(this.ray02L);
        this.sun04.addChild(this.ray04L);
        this.sun03.addChild(this.sun04);
        this.sun02.addChild(this.sun03);
        this.sun04.addChild(this.ray02R);
        this.sun04.addChild(this.ray02R_1);
        this.sun04.addChild(this.ray04R);
        this.sun04.addChild(this.ray01L);
        this.sun04.addChild(this.ray03R);
        this.sun00.addChild(this.sun01);
        this.sun04.addChild(this.ray05R);
        this.sun04.addChild(this.ray05L);
        this.sun01.addChild(this.sun02);
        this.sun04.addChild(this.ray03L);
        this.sun04.addChild(this.ray00);
        this.sun04.addChild(this.ray01R);
        this.plinth00.addChild(this.plinth01);
    }

    @Override
    public void render(float scale) {
        this.sun00.render(scale);
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
