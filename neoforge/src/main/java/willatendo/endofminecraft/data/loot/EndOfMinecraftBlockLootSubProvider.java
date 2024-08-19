package willatendo.endofminecraft.data.loot;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class EndOfMinecraftBlockLootSubProvider extends BlockLootSubProvider {
    public EndOfMinecraftBlockLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(EndOfMinecraftBlocks.ANOMALY_STONE.get(), LootTable.lootTable());
        this.add(EndOfMinecraftBlocks.ANOMALY_BRICKS.get(), LootTable.lootTable());
        this.add(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get(), LootTable.lootTable());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream().filter(block -> EndOfMinecraftUtils.ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toSet());
    }
}
