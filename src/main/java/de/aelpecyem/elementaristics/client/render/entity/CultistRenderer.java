package de.aelpecyem.elementaristics.client.render.entity;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.model.entity.CultistModel;
import de.aelpecyem.elementaristics.common.entity.CultistEntity;
import de.aelpecyem.elementaristics.common.entity.PlayerDummyEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CultistRenderer extends LivingRenderer<CultistEntity, CultistModel>{
    public static ResourceLocation STANDARD_TEX = new ResourceLocation(Elementaristics.MODID, "textures/entity/cultists/cultist_light.png");
    public CultistRenderer(EntityRendererManager manager) {
        super(manager, new CultistModel(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(CultistEntity cultistEntity) {
        return STANDARD_TEX;
    }

    @Override
    protected boolean canRenderName(CultistEntity entity) {
        return entity.hasCustomName();
    }
}
