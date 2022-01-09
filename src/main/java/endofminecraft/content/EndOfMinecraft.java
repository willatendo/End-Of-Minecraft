package endofminecraft.content;

import endofminecraft.content.client.render.AnomalyStoneRenderer;
import endofminecraft.content.server.init.BlockInit;
import endofminecraft.content.server.init.StructureInit;
import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.dimension.PlanetAlphaDimensionRenderInfo;
import endofminecraft.library.util.ModRegistry;
import endofminecraft.library.util.ModUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModUtils.ID)
public class EndOfMinecraft {
	public EndOfMinecraft() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

		ModRegistry.registry();

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> bus.addListener(ClientSetup::registerRenderers));
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			StructureInit.registerNoiseSettings();
			StructureInit.Configured.registerConfiguredFeatures();
		});
	}

	private void setupClient(FMLClientSetupEvent event) {
		DimensionRenderInfo planet_alpha_render = new PlanetAlphaDimensionRenderInfo();
		DimensionRenderInfo.EFFECTS.put(new ResourceLocation(ModUtils.ID, "planet_alpha_render"), planet_alpha_render);
	}

	static class ClientSetup {
		public static void registerRenderers(final FMLClientSetupEvent event) {
			ClientRegistry.bindTileEntityRenderer(TileEntityInit.ANONALY_STONE_TILE_ENTITY, AnomalyStoneRenderer::new);

			RenderTypeLookup.setRenderLayer(BlockInit.ANOMALY_STONE, RenderType.cutout());
		}
	}
}
