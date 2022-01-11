package endofminecraft.content.server.init;

import endofminecraft.content.ModRegistry;
import endofminecraft.library.tileentity.AnomalyStoneTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityInit {
	public static final TileEntityType<?> ANONALY_STONE_TILE_ENTITY = ModRegistry.blockEntity("anomaly_stone_tile_entity", TileEntityType.Builder.of(AnomalyStoneTileEntity::new, BlockInit.ANOMALY_STONE).build(null));

	public static void init() {
	}
}
