package de.aelpecyem.elementaristics.common.misc.potion;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleModes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IntoxicatedEffect extends EffectMod {
    public IntoxicatedEffect() {
        super("intoxicated", EffectType.NEUTRAL, 12858015);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, false, false));
        entityLivingBaseIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 0, false, false, false));
        entityLivingBaseIn.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, false));
        entityLivingBaseIn.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0, false, false, false));
        //proper distortion effects

        if (entityLivingBaseIn instanceof MobEntity && entityLivingBaseIn.getRNG().nextInt(100) <= amplifier) {
            ((MobEntity) entityLivingBaseIn).setAttackTarget(null);
            ((MobEntity) entityLivingBaseIn).setAggroed(false);
        }
        if (entityLivingBaseIn.world.isRemote)
            Elementaristics.particles.spawnEntityParticles(entityLivingBaseIn, 6666, ParticleModes.RAINBOWS, 0.002D);

    }

    @OnlyIn(Dist.CLIENT)
    private void handleDistortion() {
        //have fancy stuff I guess?
    }
}
