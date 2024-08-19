package willatendo.endofminecraft.client.properties;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.level.Level;
import willatendo.endofminecraft.server.item.AnomalyDetectorItem;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftItemProperties {
	public static void init() {
		ItemProperties.register(EndOfMinecraftItems.ANOMALY_DETECTOR.get(), EndOfMinecraftUtils.resource("anomaly_direction"), new AnomalyDetectorItemPropertyFunction((clientLevel, itemStack, entity) -> {
			return GlobalPos.of(Level.OVERWORLD, AnomalyDetectorItem.hasLocation(itemStack) ? AnomalyDetectorItem.getLocation(itemStack) : null);
		}));
	}
}
