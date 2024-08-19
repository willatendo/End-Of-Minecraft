package willatendo.endofminecraft.data.loot;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.endofminecraft.server.item.EndOfMinecraftLootTables;

import java.util.function.BiConsumer;

public class EndOfMinecraftChestLootSubProvider implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, Builder> chestLoot) {
        chestLoot.accept(EndOfMinecraftLootTables.VILLAGE_POST_APOCALYPTIC_HOUSE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0f, 8.0f)).add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))).add(LootItem.lootTableItem(Items.DEAD_BUSH).setWeight(3)).add(LootItem.lootTableItem(Items.POTATO).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 7.0f)))).add(LootItem.lootTableItem(Items.BREAD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))).add(LootItem.lootTableItem(Items.APPLE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 5.0f)))).add(LootItem.lootTableItem(Items.BOOK).setWeight(1)).add(LootItem.lootTableItem(Items.FEATHER).setWeight(1)).add(LootItem.lootTableItem(Items.EMERALD).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))).add(LootItem.lootTableItem(Blocks.OAK_SAPLING).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))).add(LootItem.lootTableItem(EndOfMinecraftItems.ANOMALY_DETECTOR.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));
    }
}
