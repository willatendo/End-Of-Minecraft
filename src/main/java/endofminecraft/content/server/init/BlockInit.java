package endofminecraft.content.server.init;

import endofminecraft.content.ModRegistry;
import endofminecraft.library.block.AnomalyStoneBlock;
import endofminecraft.library.block.PlanetAlphaPortalBlock;
import net.minecraft.world.level.block.Block;

public class BlockInit {
	public static final Block ANOMALY_STONE = ModRegistry.block("anomaly_stone", new AnomalyStoneBlock());
	public static final Block PLANET_ALPHA_PORTAL = ModRegistry.block("planet_alpha_portal", new PlanetAlphaPortalBlock());

	public static void init() {
	}
}
