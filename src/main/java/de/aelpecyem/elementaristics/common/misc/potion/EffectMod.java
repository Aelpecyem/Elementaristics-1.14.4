package de.aelpecyem.elementaristics.common.misc.potion;

import de.aelpecyem.elementaristics.Elementaristics;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class EffectMod extends Effect {
    public EffectMod(String name, EffectType type, int liquidColorIn) {
        super(type, liquidColorIn);
        setRegistryName(Elementaristics.MODID, name);
    }

    public boolean hasEffect(LivingEntity entity) {
        return hasEffect(entity, this);
    }

    public boolean hasEffect(LivingEntity entity, Effect effect) {
        return entity.getActivePotionEffect(effect) != null;
    }

}
