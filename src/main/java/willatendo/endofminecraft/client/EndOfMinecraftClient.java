package willatendo.endofminecraft.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import willatendo.endofminecraft.client.model.AnomalyStoneModel;
import willatendo.endofminecraft.client.particle.EndOfMinecraftParticleTypes;
import willatendo.endofminecraft.client.particle.PlanetAlphaPortalParticle;
import willatendo.endofminecraft.client.renderer.AnomalyStoneRenderer;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.block.entity.EndOfMinecraftBlockEntities;

public class EndOfMinecraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(EndOfMinecraftBlocks.ANOMALY_STONE.get(), RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get(), RenderType.translucent());

		ParticleFactoryRegistry.getInstance().register(EndOfMinecraftParticleTypes.PLANET_ALPHA_PORTAL_PARTICLE.get(), PlanetAlphaPortalParticle.Provider::new);

		EntityModelLayerRegistry.registerModelLayer(EndOfMinecraftModels.ANOMALY_STONE, AnomalyStoneModel::createBodyLayer);

		BlockEntityRenderers.register(EndOfMinecraftBlockEntities.ANOMALY_STONE.get(), AnomalyStoneRenderer::new);
	}
}
