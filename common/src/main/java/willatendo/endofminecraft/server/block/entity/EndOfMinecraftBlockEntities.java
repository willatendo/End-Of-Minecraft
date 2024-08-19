package willatendo.endofminecraft.server.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.List;

public class EndOfMinecraftBlockEntities {
    public static final SimpleRegistry<BlockEntityType<?>> BLOCK_ENTITIES = SimpleRegistry.create(Registries.BLOCK_ENTITY_TYPE, EndOfMinecraftUtils.ID);

    public static final SimpleHolder<BlockEntityType<AnomalyStoneBlockEntity>> ANOMALY_STONE = BLOCK_ENTITIES.register("anomaly_stone", () -> BlockEntityType.Builder.of(AnomalyStoneBlockEntity::new, EndOfMinecraftBlocks.ANOMALY_STONE.get()).build(null));

    public static void init(List<SimpleRegistry<?>> simpleRegistries) {
        simpleRegistries.add(BLOCK_ENTITIES);
    }
}
