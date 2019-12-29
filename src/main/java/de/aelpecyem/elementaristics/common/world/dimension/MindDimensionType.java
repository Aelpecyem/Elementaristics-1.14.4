package de.aelpecyem.elementaristics.common.world.dimension;

import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class MindDimensionType extends ModDimension{
    public MindDimensionType(final ResourceLocation registryName) {
        this.setRegistryName(registryName);
    }

    public static DimensionType getDimensionType() {
        return DimensionType.byName(new ResourceLocation(Elementaristics.MODID, "mind"));
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return MindDimension::new;
    }
}
