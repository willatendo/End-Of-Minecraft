package willatendo.endofminecraft.server.event;

import java.util.List;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import willatendo.endofminecraft.server.effect.EndOfMinecraftMobEffects;

public class EndOfMinecraftEvents {
	public static void init() {
		ServerTickEvents.START_WORLD_TICK.register((serverLevel) -> {
			if (serverLevel.dimension() == Level.OVERWORLD) {
				List<ServerPlayer> serverPlayers = serverLevel.getPlayers(serverPlayer -> true);
				if (serverLevel.isRaining()) {
					for (ServerPlayer serverPlayer : serverPlayers) {
						if (serverLevel.canSeeSky(serverPlayer.blockPosition()) && !serverPlayer.hasEffect(EndOfMinecraftMobEffects.IRRADIATED.get())) {
							serverPlayer.addEffect(new MobEffectInstance(EndOfMinecraftMobEffects.IRRADIATED.get(), 1000));
						}
					}
				} else {
					for (ServerPlayer serverPlayer : serverPlayers) {
						serverPlayer.removeEffect(EndOfMinecraftMobEffects.IRRADIATED.get());
					}
				}
			}
		});
	}
}
