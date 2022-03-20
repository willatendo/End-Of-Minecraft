package endofminecraft.client;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.EndRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = EndOfMinecraftMod.ID, value = Dist.CLIENT)
public class ClientEvent {
	@SubscribeEvent
	public static void setupClient(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(EndRegistry.PLANET_ALPHA_PORTAL.get(), RenderType.translucent());
	}

	@SubscribeEvent
	public static void setupParticles(ParticleFactoryRegisterEvent event) {
		ParticleEngine particles = Minecraft.getInstance().particleEngine;
		particles.register((SimpleParticleType) EndRegistry.PLANET_ALPHA_PORTAL_PARTICLE.get(), PlanetAlphaPortalParticle.Provider::new);
	}
}
