package endofminecraft.server.radiation;

import java.util.Random;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.EndRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE, modid = EndOfMinecraftMod.ID)
public class RadiationHandler {
	public static int radiationLevel;

	@SubscribeEvent
	public static void worldTick(WorldTickEvent event) {
		int chanceOfRadiationLevelChange = new Random().nextInt(5000);
		var level = event.world;
		if (level.isThundering()) {
			radiationLevel = 6;
			return;
		}

		if (level.isRaining() && !level.isThundering()) {
			radiationLevel = 4;
			return;
		}

		if (!level.isRaining() && !level.isThundering()) {
			if (chanceOfRadiationLevelChange == 250) {
				radiationLevel = new Random().nextInt(4);
			}
			return;
		}
	}

	@SubscribeEvent
	public static void playerTick(PlayerTickEvent event) {
		if (RadiationHandler.isRadiationDeadly()) {
			var player = event.player;
			var level = player.level;
			if (level.canSeeSky(player.blockPosition())) {
				player.addEffect(new MobEffectInstance(EndRegistry.IRRADIATED.get(), 30));
			}
		}
	}

	public static int getRadiationLevel() {
		return radiationLevel;
	}

	public static void setRadiationLevel(int radiationLevel) {
		RadiationHandler.radiationLevel = radiationLevel;
	}

	public static boolean isRadiationDeadly() {
		return RadiationHandler.getRadiationLevel() >= 3;
	}
}
