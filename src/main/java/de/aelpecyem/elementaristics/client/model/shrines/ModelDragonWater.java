package de.aelpecyem.elementaristics.client.model.shrines;

import de.aelpecyem.elementaristics.client.model.ModelBase;
import net.minecraft.client.renderer.entity.model.RendererModel;

/**
 * ModelDragonWater - cybecat5555
 * Created using Tabula 7.0.1
 */
public class ModelDragonWater extends ModelBase {
    public RendererModel plinth00;
    public RendererModel body00;
    public RendererModel tail00;
    public RendererModel plinth01;
    public RendererModel water00a;
    public RendererModel water00b;
    public RendererModel water00c;
    public RendererModel water00d;
    public RendererModel water00e;
    public RendererModel water00f;
    public RendererModel water01a;
    public RendererModel water02a;
    public RendererModel water03a;
    public RendererModel water04a;
    public RendererModel water05a;
    public RendererModel water06a;
    public RendererModel water05b;
    public RendererModel water06b;
    public RendererModel water01b;
    public RendererModel water02b;
    public RendererModel water03b;
    public RendererModel water04b;
    public RendererModel neck;
    public RendererModel lArm00;
    public RendererModel rArm00;
    public RendererModel body01;
    public RendererModel head;
    public RendererModel snout;
    public RendererModel lHorn00;
    public RendererModel rHorn00;
    public RendererModel lHorn01;
    public RendererModel rHorn01;
    public RendererModel lArm01;
    public RendererModel rArm01;
    public RendererModel tail01;
    public RendererModel tail02;
    public RendererModel tail03;

    public ModelDragonWater() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body00 = new RendererModel(this, 0, 12);
        this.body00.setRotationPoint(2.2F, 18.2F, -2.4F);
        this.body00.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
        this.setRotateAngle(body00, -0.6981317007977318F, 0.2617993877991494F, 0.0F);
        this.tail02 = new RendererModel(this, 3, 14);
        this.tail02.setRotationPoint(0.0F, -0.3F, 3.4F);
        this.tail02.addBox(-1.0F, -1.01F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(tail02, -0.8726646259971648F, -0.6283185307179586F, 0.22689280275926282F);
        this.neck = new RendererModel(this, 0, 6);
        this.neck.setRotationPoint(0.0F, 0.0F, -1.6F);
        this.neck.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(neck, 1.2217304763960306F, 0.0F, 0.0F);
        this.water00e = new RendererModel(this, 10, 14);
        this.water00e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.water00e.addBox(2.5F, -0.7F, -4.5F, 2, 1, 9, 0.0F);
        this.lHorn00 = new RendererModel(this, 21, 0);
        this.lHorn00.setRotationPoint(1.1F, -2.0F, 0.3F);
        this.lHorn00.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lHorn00, 0.45378560551852565F, 0.17453292519943295F, 0.0F);
        this.water00b = new RendererModel(this, 12, 19);
        this.water00b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.water00b.addBox(-3.0F, -1.0F, 0.0F, 6, 1, 4, 0.0F);
        this.setRotateAngle(water00b, 0.0F, 3.141592653589793F, 0.0F);
        this.water00c = new RendererModel(this, 13, 22);
        this.water00c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.water00c.addBox(-3.0F, -0.7F, 3.5F, 6, 1, 1, 0.0F);
        this.water06a = new RendererModel(this, 26, 24);
        this.water06a.mirror = true;
        this.water06a.setRotationPoint(-2.0F, 0.0F, 3.8F);
        this.water06a.addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);
        this.setRotateAngle(water06a, 0.0F, 0.0F, -0.6981317007977318F);
        this.lArm01 = new RendererModel(this, 25, 5);
        this.lArm01.setRotationPoint(1.1F, 2.0F, 0.0F);
        this.lArm01.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lArm01, -0.2617993877991494F, 0.0F, 0.0F);
        this.lArm00 = new RendererModel(this, 25, 11);
        this.lArm00.setRotationPoint(0.2F, -0.2F, -1.0F);
        this.lArm00.addBox(0.7F, -0.5F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(lArm00, 0.6981317007977318F, 0.0F, 0.0F);
        this.rArm00 = new RendererModel(this, 25, 11);
        this.rArm00.mirror = true;
        this.rArm00.setRotationPoint(-0.4F, -0.2F, -1.0F);
        this.rArm00.addBox(-1.7F, -0.5F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(rArm00, 0.3839724354387525F, 0.0F, 0.0F);
        this.rHorn01 = new RendererModel(this, 22, 1);
        this.rHorn01.mirror = true;
        this.rHorn01.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.rHorn01.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHorn01, 0.3141592653589793F, -0.2792526803190927F, 0.0F);
        this.water05a = new RendererModel(this, 0, 20);
        this.water05a.setRotationPoint(-2.0F, -0.4F, 1.7F);
        this.water05a.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(water05a, 0.6981317007977318F, -0.6806784082777886F, 0.0F);
        this.water03a = new RendererModel(this, 0, 20);
        this.water03a.setRotationPoint(-3.5F, -0.8F, -1.2F);
        this.water03a.addBox(-0.5F, -2.0F, -1.6F, 1, 2, 2, 0.0F);
        this.setRotateAngle(water03a, 0.5235987755982988F, 0.3490658503988659F, 0.0F);
        this.water06b = new RendererModel(this, 6, 20);
        this.water06b.setRotationPoint(-0.0F, -1.8F, 0.0F);
        this.water06b.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(water06b, 0.0F, 0.0F, -0.2617993877991494F);
        this.body01 = new RendererModel(this, 2, 14);
        this.body01.setRotationPoint(0.0F, 0.0F, 1.9F);
        this.body01.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(body01, -0.2617993877991494F, -0.2617993877991494F, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, -3.7F, -0.4F);
        this.head.addBox(-1.5F, -2.5F, -2.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(head, -0.5235987755982988F, 0.0F, 0.0F);
        this.snout = new RendererModel(this, 12, 0);
        this.snout.setRotationPoint(0.0F, -0.5F, -2.0F);
        this.snout.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);
        this.rHorn00 = new RendererModel(this, 21, 0);
        this.rHorn00.mirror = true;
        this.rHorn00.setRotationPoint(-1.1F, -2.0F, 0.3F);
        this.rHorn00.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rHorn00, 0.45378560551852565F, -0.17453292519943295F, 0.0F);
        this.tail03 = new RendererModel(this, 14, 5);
        this.tail03.setRotationPoint(0.0F, -0.1F, 2.5F);
        this.tail03.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(tail03, -0.7853981633974483F, 0.0F, 0.0F);
        this.water04b = new RendererModel(this, 6, 20);
        this.water04b.setRotationPoint(0.0F, -1.8F, -0.4F);
        this.water04b.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(water04b, 0.2617993877991494F, 0.0F, 0.0F);
        this.water00a = new RendererModel(this, 12, 19);
        this.water00a.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.water00a.addBox(-3.0F, -1.0F, 0.0F, 6, 1, 4, 0.0F);
        this.water00d = new RendererModel(this, 13, 22);
        this.water00d.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.water00d.addBox(-3.0F, -0.7F, -4.5F, 6, 1, 1, 0.0F);
        this.water05b = new RendererModel(this, 6, 20);
        this.water05b.setRotationPoint(-0.0F, -1.8F, 0.0F);
        this.water05b.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(water05b, 0.2617993877991494F, 0.0F, 0.0F);
        this.water03b = new RendererModel(this, 6, 20);
        this.water03b.setRotationPoint(0.0F, -1.8F, -0.4F);
        this.water03b.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(water03b, 0.2617993877991494F, 0.0F, 0.0F);
        this.rArm01 = new RendererModel(this, 25, 5);
        this.rArm01.mirror = true;
        this.rArm01.setRotationPoint(-1.1F, 2.0F, 0.0F);
        this.rArm01.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.water04a = new RendererModel(this, 0, 20);
        this.water04a.setRotationPoint(-3.7F, -0.8F, 0.7F);
        this.water04a.addBox(-0.5F, -2.0F, -1.6F, 1, 2, 2, 0.0F);
        this.setRotateAngle(water04a, 0.6981317007977318F, 0.5235987755982988F, -0.3490658503988659F);
        this.plinth00 = new RendererModel(this, 0, 24);
        this.plinth00.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.plinth00.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.tail00 = new RendererModel(this, 2, 13);
        this.tail00.setRotationPoint(-2.3F, 22.0F, 3.0F);
        this.tail00.addBox(-1.0F, -1.5F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail00, 1.1344640137963142F, -2.1816615649929116F, 0.0F);
        this.tail01 = new RendererModel(this, 2, 13);
        this.tail01.setRotationPoint(0.0F, -0.9F, 3.1F);
        this.tail01.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail01, -0.8726646259971648F, 0.0F, 0.5235987755982988F);
        this.water02b = new RendererModel(this, 6, 20);
        this.water02b.setRotationPoint(0.0F, -1.8F, -0.4F);
        this.water02b.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(water02b, 0.3490658503988659F, 0.0F, 0.0F);
        this.lHorn01 = new RendererModel(this, 22, 1);
        this.lHorn01.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.lHorn01.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHorn01, 0.3141592653589793F, 0.2792526803190927F, 0.0F);
        this.water01b = new RendererModel(this, 6, 20);
        this.water01b.setRotationPoint(-0.2F, -1.8F, 0.4F);
        this.water01b.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(water01b, 0.2617993877991494F, 0.0F, 0.0F);
        this.water01a = new RendererModel(this, 0, 20);
        this.water01a.setRotationPoint(-1.3F, -0.8F, -0.7F);
        this.water01a.addBox(-0.7F, -2.0F, -0.5F, 1, 2, 2, 0.0F);
        this.setRotateAngle(water01a, 0.6981317007977318F, -0.5235987755982988F, 0.3490658503988659F);
        this.plinth01 = new RendererModel(this, 0, 24);
        this.plinth01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.plinth01.addBox(-5.0F, -1.0F, -5.0F, 10, 3, 5, 0.0F);
        this.setRotateAngle(plinth01, 0.0F, 3.141592653589793F, 0.0F);
        this.water02a = new RendererModel(this, 0, 20);
        this.water02a.setRotationPoint(-2.5F, -0.8F, -1.2F);
        this.water02a.addBox(-0.5F, -2.0F, -1.6F, 1, 2, 2, 0.0F);
        this.setRotateAngle(water02a, 0.5235987755982988F, -0.3490658503988659F, 0.0F);
        this.water00f = new RendererModel(this, 10, 14);
        this.water00f.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.water00f.addBox(-4.5F, -0.7F, -4.5F, 2, 1, 9, 0.0F);
        this.tail01.addChild(this.tail02);
        this.body00.addChild(this.neck);
        this.water00a.addChild(this.water00e);
        this.head.addChild(this.lHorn00);
        this.water00a.addChild(this.water00b);
        this.water00a.addChild(this.water00c);
        this.water00b.addChild(this.water06a);
        this.lArm00.addChild(this.lArm01);
        this.body00.addChild(this.lArm00);
        this.body00.addChild(this.rArm00);
        this.rHorn00.addChild(this.rHorn01);
        this.water00b.addChild(this.water05a);
        this.water00a.addChild(this.water03a);
        this.water06a.addChild(this.water06b);
        this.body00.addChild(this.body01);
        this.neck.addChild(this.head);
        this.head.addChild(this.snout);
        this.head.addChild(this.rHorn00);
        this.tail02.addChild(this.tail03);
        this.water04a.addChild(this.water04b);
        this.plinth01.addChild(this.water00a);
        this.water00a.addChild(this.water00d);
        this.water05a.addChild(this.water05b);
        this.water03a.addChild(this.water03b);
        this.rArm00.addChild(this.rArm01);
        this.water00a.addChild(this.water04a);
        this.tail00.addChild(this.tail01);
        this.water02a.addChild(this.water02b);
        this.lHorn00.addChild(this.lHorn01);
        this.water01a.addChild(this.water01b);
        this.water00a.addChild(this.water01a);
        this.plinth00.addChild(this.plinth01);
        this.water00a.addChild(this.water02a);
        this.water00a.addChild(this.water00f);
    }

    @Override
    public void render(float f5) {
        this.body00.render(f5);
        this.plinth00.render(f5);
        this.tail00.render(f5);
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
