package endofminecraft.client;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import endofminecraft.server.EndRegistry;
import endofminecraft.server.radiation.RadiationHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;

public class EndItemProperties {
	public static void registerItemProperties() {
		ItemProperties.register(EndRegistry.GEIGER_COUNTER.get(), UTILS.mod("radiation"), (stack, level, entity, i) -> {
			if (level == null) {
				if (entity.level instanceof ClientLevel client) {
					level = client;
				}
			}

			int radiationLevel = level.dimension() == EndRegistry.PLANET_ALPHA ? 0 : RadiationHandler.getRadiationLevel();

			return entity != null && radiationLevel == 1 ? 1.0F : entity != null && radiationLevel == 2 ? 2.0F : entity != null && radiationLevel == 3 ? 3.0F : entity != null && radiationLevel == 4 ? 4.0F : entity != null && radiationLevel == 5 ? 5.0F : entity != null && radiationLevel == 6 ? 6.0F : 0.0F;
		});
	}
}