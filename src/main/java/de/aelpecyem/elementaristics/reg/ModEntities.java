package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.entity.CultistEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    @ObjectHolder(Elementaristics.MODID + ":cultist")
    public static EntityType<CultistEntity> CULTIST;

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(){

    }

    @SubscribeEvent
    public static void register(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(EntityType.Builder.create(CultistEntity::new, EntityClassification.MISC)
                .size(0.6F, 1.8F)
                .setShouldReceiveVelocityUpdates(false)
                .build("cultist").setRegistryName(Elementaristics.MODID, "cultist"));
    }
}
