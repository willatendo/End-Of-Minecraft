package endofminecraft.content.client;

import endofminecraft.content.server.init.ParticleInit;
import endofminecraft.library.particle.ModParticle;
import endofminecraft.library.util.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ParticleSetup 
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticles(ParticleFactoryRegisterEvent event) 
	{
		ModUtils.LOGGER.debug("Loading: Setting Up Particle Render");
		
		Minecraft.getInstance().particleEngine.register(ParticleInit.PLANET_ALPHA_PORTAL_PARTICLE, ModParticle.Factory::new);
		
		ModUtils.LOGGER.debug("Finished: Setting Up Particle Render");
	}
}
