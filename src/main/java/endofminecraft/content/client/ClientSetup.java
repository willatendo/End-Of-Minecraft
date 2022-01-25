package endofminecraft.content.client;

import endofminecraft.content.client.render.AnomalyStoneRenderer;
import endofminecraft.content.server.init.BlockInit;
import endofminecraft.content.server.init.TileEntityInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
	public static void blockEntitityRegisters(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(TileEntityInit.ANONALY_STONE_TILE_ENTITY, AnomalyStoneRenderer::new);
	}

	public static void layers() {
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ANOMALY_STONE, RenderType.cutout());
	}
}
