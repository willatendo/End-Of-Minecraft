package endofminecraft.client;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import endofminecraft.server.EndRegistry;
import endofminecraft.server.radiation.RadiationHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;

public class EndItemProperties {
	public static void registerItemProperties() {
		registerGeigerCounter(EndRegistry.GEIGER_COUNTER.get());
	}

	public static void registerGeigerCounter(Item item) {
		ItemProperties.register(item, UTILS.resource("radiation"), (stack, level, entity, i) -> {
			if (level == null) {
				if (entity.level instanceof ClientLevel client) {
					level = client;
				}
			}

			int radiationLevel = RadiationHandler.getRadiationLevel();

			return entity != null && !level.isRaining() && radiationLevel == 1 ? 1.0F : entity != null && !level.isRaining() && radiationLevel == 2 ? 2.0F : entity != null && !level.isRaining() && radiationLevel == 3 ? 3.0F : entity != null && level.isRaining() ? 4.0F : entity != null && level.isRaining() ? 6.0F : 0.0F;
		});
	}
}