package de.aelpecyem.elementaristics.proxy;


import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.handler.HUDHandler;
import de.aelpecyem.elementaristics.client.model.tile.ModelBase;
import de.aelpecyem.elementaristics.client.model.tile.shrines.*;
import de.aelpecyem.elementaristics.client.render.tile.BoilingBasinRenderer;
import de.aelpecyem.elementaristics.client.render.tile.ShrineRenderer;
import de.aelpecyem.elementaristics.common.block.tile.BoilingBasingTileEntity;
import de.aelpecyem.elementaristics.common.block.tile.ShrineTileEntity;
import de.aelpecyem.elementaristics.common.misc.pantheon.Deity;
import de.aelpecyem.elementaristics.reg.ModEntities;
import de.aelpecyem.elementaristics.reg.ModRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy extends CommonProxy {
    public static final Map<Deity, ModelBase> DEITY_MODEL_MAP = new HashMap<>();
    public static final Map<Deity, ResourceLocation> DEITY_TEXTURE_MAP = new HashMap<>();


    public static final ResourceLocation THICC_WATER = new ResourceLocation(Elementaristics.MODID, "textures/block/thick_water.png");

    @Override
    public void setup() {
        ModEntities.registerRenderers();
        ClientRegistry.bindTileEntitySpecialRenderer(BoilingBasingTileEntity.class, new BoilingBasinRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ShrineTileEntity.class, new ShrineRenderer());
        registerDeityModel(ModRegistries.DRAGON_AETHER, new ModelDragonAether());
        registerDeityModel(ModRegistries.DRAGON_FIRE, new ModelDragonFire());
        registerDeityModel(ModRegistries.DRAGON_EARTH, new ModelDragonEarth());
        registerDeityModel(ModRegistries.DRAGON_WATER, new ModelDragonWater());
        registerDeityModel(ModRegistries.DRAGON_AIR, new ModelDragonAir());
        registerDeityModel(ModRegistries.SUN, new ModelSun());
        registerDeityModel(ModRegistries.GOAT, new ModelGoat());
        registerDeityModel(ModRegistries.MOTH, new ModelMoth());
        registerDeityModel(ModRegistries.MOON, new ModelMoon());
        registerDeityModel(ModRegistries.WITCH, new ModelWitch());
        super.setup();
    }

    @Override
    public void giveVision(String visionName) {
        String res = Elementaristics.MODID + ":textures/visions/" + visionName + ".png";
        if (!HUDHandler.current_vision.equals(res)) {
            HUDHandler.vision_progress = 0;
            HUDHandler.current_vision = res;
        }
    }

    private void registerDeityModel(Deity deity, ModelBase model) {
        DEITY_MODEL_MAP.put(deity, model);
        DEITY_TEXTURE_MAP.put(deity, new ResourceLocation(Elementaristics.MODID, "textures/block/shrines/" + deity.getName().getPath() + ".png"));
    }
}
