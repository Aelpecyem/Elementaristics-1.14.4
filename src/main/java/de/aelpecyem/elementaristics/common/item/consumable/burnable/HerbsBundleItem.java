package de.aelpecyem.elementaristics.common.item.consumable.burnable;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.particle.mode.ParticleModes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
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
            doParticles(entity, 30 + world.rand.nextInt(20));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void doParticles(ItemEntity entity, int particleCount) {
        // for (int i = 0; i < particleCount; i++)
        Elementaristics.particles.spawnStandardParticle(entity.world, entity.posX, entity.posY, entity.posZ, ParticleModes.RAINBOWS);
    }
}
