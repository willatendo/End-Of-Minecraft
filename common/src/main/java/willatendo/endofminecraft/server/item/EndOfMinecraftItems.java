package willatendo.endofminecraft.server.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;
import willatendo.simplelibrary.server.util.SimpleUtils;

import java.util.List;

public class EndOfMinecraftItems {
    public static final SimpleRegistry<Item> ITEMS = SimpleRegistry.create(Registries.ITEM, EndOfMinecraftUtils.ID);

    public static final SimpleHolder<AnomalyDetectorItem> ANOMALY_DETECTOR = ITEMS.register("anomaly_detector", () -> new AnomalyDetectorItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static void init(List<SimpleRegistry<?>> simpleRegistries) {
        SimpleUtils.registerAllItems(EndOfMinecraftItems.ITEMS, EndOfMinecraftBlocks.BLOCKS, EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL);
        simpleRegistries.add(ITEMS);
    }
}
