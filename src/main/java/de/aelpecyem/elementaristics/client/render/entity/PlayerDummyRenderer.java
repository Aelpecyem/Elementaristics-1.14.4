package de.aelpecyem.elementaristics.client.render.entity;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.model.entity.CultistModel;
import de.aelpecyem.elementaristics.common.entity.CultistEntity;
import de.aelpecyem.elementaristics.common.entity.PlayerDummyEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class PlayerDummyRenderer extends LivingRenderer<PlayerDummyEntity, PlayerModel<PlayerDummyEntity>>{
    public PlayerDummyRenderer(EntityRendererManager manager) {
        super(manager, new PlayerModel<>(1, false), 0.5F);
    }

    @Override
    public boolean shouldRender(PlayerDummyEntity livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return livingEntity.getPlayer() != null && super.shouldRender(livingEntity, camera, camX, camY, camZ);
    }

    @Override
    protected void preRenderCallback(PlayerDummyEntity entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.getPlayer() instanceof AbstractClientPlayerEntity){
            entityModel = new PlayerModel<>(1, DefaultPlayerSkin.getSkinType(entitylivingbaseIn.getPlayerUUID()).equals("default"));
        }
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(PlayerDummyEntity playerDummyEntity) {
        if (playerDummyEntity.getPlayer() != null){
            Minecraft mc = Minecraft.getInstance();
            if (!(mc.getRenderViewEntity() instanceof AbstractClientPlayerEntity))
                return DefaultPlayerSkin.getDefaultSkinLegacy();
            return ((AbstractClientPlayerEntity) mc.getRenderViewEntity()).getLocationSkin();
        }
        return null;
    }
}
