package de.aelpecyem.elementaristics.common.item.consumable.burnable;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.particle.GlowParticle;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleModes;
import de.aelpecyem.elementaristics.reg.ModPotions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HerbsBundleItem extends BurnableItem {
    public HerbsBundleItem() {
        super("bundle_herbs");
    }

    @Override
    public void burnEffect(ItemStack stack, ItemEntity entity) {
        World world = entity.world;
        if (world.isRemote) {
            doParticles(entity, 80 + world.rand.nextInt(40));
        }
        world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(10)).forEach(l -> l.addPotionEffect(new EffectInstance(ModPotions.INTOXICATED, 2000, 1)));
    }

    @OnlyIn(Dist.CLIENT)
    public void doParticles(ItemEntity entity, int particleCount) {
        for (int i = 0; i < particleCount; i++) {
            GlowParticle particle = new GlowParticle(entity.world, entity.posX, entity.posY, entity.posZ, entity.world.rand.nextGaussian() * 0.01,
                    entity.world.rand.nextGaussian() * 0.01,
                    entity.world.rand.nextGaussian() * 0.01, 800 + entity.world.rand.nextInt(400), 16711680, 0.9F, 0.8F, 0, true, false, GlowParticle.EnumFadeMode.OUT); //in out
            particle.setMode(ParticleModes.RAINBOWS);
            Elementaristics.particles.spawnParticle(particle);
            //Elementaristics.particles.spawnStandardParticle(entity.world, entity.posX, entity.posY, entity.posZ, ParticleModes.RAINBOWS, 0.01D);
        }
    }
}
