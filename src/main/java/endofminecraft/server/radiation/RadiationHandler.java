package endofminecraft.server.radiation;

import endofminecraft.EndOfMinecraftMod;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE, modid = EndOfMinecraftMod.ID)
public class RadiationHandler {
	public static int radiationLevel;

	@SubscribeEvent
	public static void worldTick(WorldTickEvent event) {
//		int chanceToIncreaseRadiation = new Random().nextInt(32000);
//		if (chanceToIncreaseRadiation < 6000) {
//			if (radiationLevel <= 3) {
//				radiationLevel = 0;
//			} else {
//				int increaseLevel = new Random().nextInt(3);
//				radiationLevel = radiationLevel + increaseLevel;
//			}
//		}
	}

	public static int getRadiationLevel() {
		return radiationLevel;
	}

	public static void setRadiationLevel(int radiationLevel) {
		RadiationHandler.radiationLevel = radiationLevel;
	}
}
