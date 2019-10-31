package de.aelpecyem.elementaristics.common.misc.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class EffectMod extends Effect {
    public EffectMod(EffectType type, int liquidColorIn) {
        super(type, liquidColorIn);
    }

    public boolean hasEffect(LivingEntity entity) {
        return hasEffect(entity, this);
    }

    public boolean hasEffect(LivingEntity entity, Effect effect) {
        return entity.getActivePotionEffect(effect) != null;
    }

}
