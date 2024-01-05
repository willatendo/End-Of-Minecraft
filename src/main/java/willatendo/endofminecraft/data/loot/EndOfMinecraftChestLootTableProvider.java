package willatendo.endofminecraft.data.loot;

import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import willatendo.endofminecraft.server.item.EndOfMinecraftLootTables;

public class EndOfMinecraftChestLootTableProvider extends SimpleFabricLootTableProvider {
	public EndOfMinecraftChestLootTableProvider(FabricDataOutput fabricDataOutput) {
		super(fabricDataOutput, LootContextParamSets.CHEST);
	}

	@Override
	public void generate(BiConsumer<ResourceLocation, Builder> chestLoot) {
		chestLoot.accept(EndOfMinecraftLootTables.BURNED_HOUSE_CHEST, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 2.0F)).add(LootItem.lootTableItem(Items.OAK_SAPLING))));
	}
}
