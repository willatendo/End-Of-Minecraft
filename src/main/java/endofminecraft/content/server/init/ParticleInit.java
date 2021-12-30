package endofminecraft.content.server.init;

import endofminecraft.library.util.ModRegistry;
import net.minecraft.particles.BasicParticleType;

public class ParticleInit 
{
	public static final BasicParticleType PLANET_ALPHA_PORTAL_PARTICLE = ModRegistry.particle("planet_alpha_portal_particle", new BasicParticleType(false));	
	
	public static void init() { }
}
