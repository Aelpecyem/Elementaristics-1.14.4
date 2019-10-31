package de.aelpecyem.elementaristics.client.particle.mode;

import de.aelpecyem.elementaristics.client.particle.GlowParticle;
import de.aelpecyem.elementaristics.reg.ModParticles;
import net.minecraft.client.particle.IParticleRenderType;

import java.awt.*;

public class ParticleModeSpectrum extends ParticleMode {
    @Override
    public void setUp(GlowParticle particle) {
        int addition = particle.getRandom().nextInt(100);
        particle.setAge(particle.getAge() + addition);
        particle.setMaxAge(particle.getMaxAge() + addition);
        particle.setColor(0, 0, 1);
    }

    @Override
    public void tick(GlowParticle particle) {
        float process = (particle.getAge() % 100) / 100F;
        Color color = Color.getHSBColor(process, 1, 1);
        particle.setColor(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F);
    }

    @Override
    public IParticleRenderType renderType(GlowParticle particle) {
        return ModParticles.RenderTypes.BRIGHT;
    }

    @Override
    public boolean overridesCompletely() {
        return false;
    }
}
