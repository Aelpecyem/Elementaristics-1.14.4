package de.aelpecyem.elementaristics.common.misc.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class IntoxicatedEffect extends EffectMod {
    public IntoxicatedEffect() {
        super(EffectType.NEUTRAL, 12858015);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
    }
}
