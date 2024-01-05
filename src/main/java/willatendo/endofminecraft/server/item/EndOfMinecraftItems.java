package willatendo.endofminecraft.server.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;
import willatendo.simplelibrary.server.util.SimpleUtils;

public class EndOfMinecraftItems {
	public static final SimpleRegistry<Item> ITEMS = SimpleRegistry.create(BuiltInRegistries.ITEM, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<AnomalyDetectorItem> ANOMALY_DETECTOR = ITEMS.register("anomaly_detector", () -> new AnomalyDetectorItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

	public static void init() {
		SimpleUtils.registerAllItems(EndOfMinecraftItems.ITEMS, EndOfMinecraftBlocks.BLOCKS, EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL);
	}
}
