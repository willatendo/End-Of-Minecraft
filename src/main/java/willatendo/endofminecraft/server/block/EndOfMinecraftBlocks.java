  package willatendo.endofminecraft.server.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

public class EndOfMinecraftBlocks {
	public static final SimpleRegistry<Block> BLOCKS = SimpleRegistry.create(BuiltInRegistries.BLOCK, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<AnomalyStoneBlock> ANOMALY_STONE = BLOCKS.register("anomaly_stone", () -> new AnomalyStoneBlock(BlockBehaviour.Properties.of().strength(-1.0F).noOcclusion().pushReaction(PushReaction.BLOCK)));
	public static final RegistryHolder<Block> ANOMALY_BRICKS = BLOCKS.register("anomaly_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(-1.0F).noOcclusion().pushReaction(PushReaction.BLOCK)));
	public static final RegistryHolder<PlanetAlphaPortalBlock> PLANET_ALPHA_PORTAL = BLOCKS.register("planet_alpha_portal", () -> new PlanetAlphaPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().strength(-1.0f).sound(SoundType.GLASS).lightLevel(blockState -> 11).pushReaction(PushReaction.BLOCK)));

	public static void init() {
	}
}
