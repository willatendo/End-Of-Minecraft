package willatendo.endofminecraft.server;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;
import willatendo.simplelibrary.server.util.SimpleUtils;

import java.util.List;

public class EndOfMinecraftCreativeModeTabs {
    public static final SimpleRegistry<CreativeModeTab> CREATIVE_MODE_TABS = SimpleRegistry.create(Registries.CREATIVE_MODE_TAB, EndOfMinecraftUtils.ID);

    public static final SimpleHolder<CreativeModeTab> END_OF_MINECRAFT = CREATIVE_MODE_TABS.register("end_of_minecraft", () -> SimpleUtils.create(EndOfMinecraftUtils.ID, EndOfMinecraftUtils.ID, () -> EndOfMinecraftBlocks.ANOMALY_STONE.get().asItem(), (itemDisplayParameters, output) -> {
        output.accept(EndOfMinecraftItems.ANOMALY_DETECTOR.get());
        output.accept(EndOfMinecraftBlocks.ANOMALY_STONE.get());
        output.accept(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());
    }).build());

    public static void init(List<SimpleRegistry<?>> simpleRegistries) {
        simpleRegistries.add(CREATIVE_MODE_TABS);
    }
}
