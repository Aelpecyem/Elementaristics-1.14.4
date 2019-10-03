package de.aelpecyem.elementaristics.client.particle.mode;

import de.aelpecyem.elementaristics.client.particle.GlowParticle;
import de.aelpecyem.elementaristics.reg.ModParticles;
import de.aelpecyem.elementaristics.util.ColorUtil;
import net.minecraft.client.particle.IParticleRenderType;

import java.awt.*;

public class ParticleModeSpectrum extends ParticleMode {
    @Override
    public void setUp(GlowParticle particle) {
        particle.setColor(0, 0, 1);
    }

    @Override
    public void tick(GlowParticle particle) {
        int tick = particle.getAge() % 900;
        int tickParts = tick % 300;
        float blend = (0.5F - Math.abs(0.5F - tickParts / 300F)) * 2F;//tickParts - 200;
        Color color;
        if (tick <= 300) {
            color = ColorUtil.blend(Color.BLUE, Color.RED, blend, 1 - blend);
        } else if (tick <= 600) {
            color = ColorUtil.blend(Color.RED, Color.GREEN, blend, 1 - blend);
        } else {
            color = ColorUtil.blend(Color.GREEN, Color.BLUE, blend, 1 - blend);
        }
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