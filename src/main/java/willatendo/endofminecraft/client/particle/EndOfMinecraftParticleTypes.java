package willatendo.endofminecraft.client.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

public class EndOfMinecraftParticleTypes {
	public static final SimpleRegistry<ParticleType<?>> PARTICLE_TYPES = SimpleRegistry.create(BuiltInRegistries.PARTICLE_TYPE, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<SimpleParticleType> PLANET_ALPHA_PORTAL_PARTICLE = PARTICLE_TYPES.register("planet_alpha_portal_particle", () -> FabricParticleTypes.simple());

	public static void init() {
	}
}
