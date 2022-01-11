package endofminecraft.content;

import endofminecraft.content.client.render.AnomalyStoneRenderer;
import endofminecraft.content.server.init.BlockInit;
import endofminecraft.content.server.init.StructureInit;
import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.dimension.PlanetAlphaDimensionRenderInfo;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

		ModRegistry.registry();

		forgeBus.addListener(this::biomeModification);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> bus.addListener(ClientSetup::registerRenderers));
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			StructureInit.registerNoiseSettings();
			StructureInit.Configured.registerConfiguredFeatures();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		DimensionRenderInfo planet_alpha_render = new PlanetAlphaDimensionRenderInfo();
		DimensionRenderInfo.EFFECTS.put(new ResourceLocation(ModUtils.ID, "planet_alpha_render"), planet_alpha_render);
	}

	private void biomeModification(BiomeLoadingEvent event) {
		if (event.getName() == ModUtils.rL("scorchland") || event.getName() == ModUtils.rL("wasteland")) {
			event.getGeneration().getStructures().add(() -> StructureInit.Configured.CONFIGURED_ANOMALY_CAVE);
		}
	}

	static class ClientSetup {
		public static void registerRenderers(final FMLClientSetupEvent event) {
			ClientRegistry.bindTileEntityRenderer(TileEntityInit.ANONALY_STONE_TILE_ENTITY, AnomalyStoneRenderer::new);

			RenderTypeLookup.setRenderLayer(BlockInit.ANOMALY_STONE, RenderType.cutout());
		}
	}
}
