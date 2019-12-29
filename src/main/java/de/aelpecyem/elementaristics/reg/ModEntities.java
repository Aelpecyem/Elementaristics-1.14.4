package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.render.entity.CultistRenderer;
import de.aelpecyem.elementaristics.client.render.entity.PlayerDummyRenderer;
import de.aelpecyem.elementaristics.common.entity.CultistEntity;
import de.aelpecyem.elementaristics.common.entity.PlayerDummyEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    @ObjectHolder(Elementaristics.MODID + ":cultist")
    public static EntityType<CultistEntity> CULTIST;

    @ObjectHolder(Elementaristics.MODID + ":player_dummy")
    public static EntityType<PlayerDummyEntity> PLAYER_DUMMY;
    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(){
        RenderingRegistry.registerEntityRenderingHandler(CultistEntity.class, CultistRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PlayerDummyEntity.class, PlayerDummyRenderer::new);
    }

    @SubscribeEvent
    public static void register(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(EntityType.Builder.create(CultistEntity::new, EntityClassification.MISC)
                .size(0.6F, 1.8F)
                .setShouldReceiveVelocityUpdates(false)
                .build("cultist").setRegistryName(Elementaristics.MODID, "cultist"));
        event.getRegistry().register(EntityType.Builder.create(PlayerDummyEntity::new, EntityClassification.MISC)
                .size(0.6F, 1.8F)
                .setShouldReceiveVelocityUpdates(false)
                .build("player_dummy").setRegistryName(Elementaristics.MODID, "player_dummy"));
    }
}
