package willatendo.endofminecraft.server.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

public class EndOfMinecraftBlockEntities {
	public static final SimpleRegistry<BlockEntityType<?>> BLOCK_ENTITIES = SimpleRegistry.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<BlockEntityType<AnomalyStoneBlockEntity>> ANOMALY_STONE = BLOCK_ENTITIES.register("anomaly_stone", () -> FabricBlockEntityTypeBuilder.create(AnomalyStoneBlockEntity::new, EndOfMinecraftBlocks.ANOMALY_STONE.get()).build());

	public static void init() {
	}
}
