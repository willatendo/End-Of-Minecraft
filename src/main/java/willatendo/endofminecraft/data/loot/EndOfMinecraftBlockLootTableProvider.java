package willatendo.endofminecraft.data.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.level.storage.loot.LootTable;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;

public class EndOfMinecraftBlockLootTableProvider extends FabricBlockLootTableProvider {
	public EndOfMinecraftBlockLootTableProvider(FabricDataOutput fabricDataOutput) {
		super(fabricDataOutput);
	}

	@Override
	public void generate() {
		this.add(EndOfMinecraftBlocks.ANOMALY_STONE.get(), LootTable.lootTable());
		this.add(EndOfMinecraftBlocks.ANOMALY_BRICKS.get(), LootTable.lootTable());
		this.add(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get(), LootTable.lootTable());
	}
}
