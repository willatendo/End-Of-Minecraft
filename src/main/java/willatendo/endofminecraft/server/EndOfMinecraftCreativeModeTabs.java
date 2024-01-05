package willatendo.endofminecraft.server;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;
import willatendo.simplelibrary.server.util.SimpleUtils;

public class EndOfMinecraftCreativeModeTabs {
	public static final SimpleRegistry<CreativeModeTab> CREATIVE_MODE_TABS = SimpleRegistry.create(BuiltInRegistries.CREATIVE_MODE_TAB, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<CreativeModeTab> END_OF_MINECRAFT = CREATIVE_MODE_TABS.register("end_of_minecraft", () -> SimpleUtils.create(EndOfMinecraftUtils.ID, EndOfMinecraftUtils.ID, () -> EndOfMinecraftBlocks.ANOMALY_STONE.get().asItem(), (itemDisplayParameters, output) -> {
		SimpleUtils.fillCreativeTab(EndOfMinecraftItems.ITEMS, itemDisplayParameters, output);
	}).build());

	public static void init() {
	}
}
