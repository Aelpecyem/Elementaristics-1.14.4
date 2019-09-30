package de.aelpecyem.elementaristics.client.particle.mode;

import de.aelpecyem.elementaristics.client.particle.GlowParticle;

public abstract class ParticleMode {
    public abstract void tick(GlowParticle particle);

    public abstract boolean overridesCompletely();
}
