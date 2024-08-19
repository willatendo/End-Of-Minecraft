package willatendo.endofminecraft.client.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;
import willatendo.simplelibrary.server.util.SimpleUtils;

import java.util.List;

public class EndOfMinecraftParticleTypes {
    public static final SimpleRegistry<ParticleType<?>> PARTICLE_TYPES = SimpleRegistry.create(Registries.PARTICLE_TYPE, EndOfMinecraftUtils.ID);

    public static final SimpleHolder<SimpleParticleType> PLANET_ALPHA_PORTAL_PARTICLE = PARTICLE_TYPES.register("planet_alpha_portal_particle", () -> SimpleUtils.createParticleType(false));

    public static void init(List<SimpleRegistry<?>> simpleRegistries) {
        simpleRegistries.add(PARTICLE_TYPES);
    }
}
