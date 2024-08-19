package willatendo.endofminecraft.client;

import willatendo.endofminecraft.client.dimension.PlanetAlphaSkyRenderer;
import willatendo.endofminecraft.client.model.AnomalyStoneModel;
import willatendo.endofminecraft.client.particle.EndOfMinecraftParticleTypes;
import willatendo.endofminecraft.client.particle.PlanetAlphaPortalParticle;
import willatendo.endofminecraft.client.renderer.AnomalyStoneRenderer;
import willatendo.endofminecraft.server.block.entity.EndOfMinecraftBlockEntities;
import willatendo.endofminecraft.server.dimension.EndOfMinecraftDimensions;
import willatendo.simplelibrary.client.event.ClientEventsHolder;

public class EndOfMinecraftClient {
    public static final ClientEventsHolder CLIENT_EVENTS_HOLDER = new ClientEventsHolder();

    public static void loadModels() {
        CLIENT_EVENTS_HOLDER.addModel(EndOfMinecraftBlockEntities.ANOMALY_STONE.get(), AnomalyStoneRenderer::new);

        CLIENT_EVENTS_HOLDER.addModelLayer(EndOfMinecraftModels.ANOMALY_STONE, AnomalyStoneModel::createBodyLayer);

        //BlockRenderLayerMap.INSTANCE.putBlock(EndOfMinecraftBlocks.ANOMALY_STONE.get(), RenderType.cutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get(), RenderType.translucent());

        CLIENT_EVENTS_HOLDER.addParticleSheet(EndOfMinecraftParticleTypes.PLANET_ALPHA_PORTAL_PARTICLE.get(), PlanetAlphaPortalParticle.Provider::new);
        CLIENT_EVENTS_HOLDER.addSkyRenderer(EndOfMinecraftDimensions.PLANET_ALPHA, (skyRenderer) -> PlanetAlphaSkyRenderer.renderSky(skyRenderer));
    }
}
