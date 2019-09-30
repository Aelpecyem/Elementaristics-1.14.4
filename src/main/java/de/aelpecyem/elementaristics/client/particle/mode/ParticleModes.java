package de.aelpecyem.elementaristics.client.particle.mode;

import de.aelpecyem.elementaristics.client.particle.GlowParticle;

public class ParticleModes {
    public static final ParticleMode STANDARD = null;


    public static final ParticleMode TEST = new ParticleMode() {
        @Override
        public void tick(GlowParticle particle) {
            particle.moveRelative(0.1, 0, 0);
        }

        @Override
        public boolean overridesCompletely() {
            return false;
        }
    };
}
