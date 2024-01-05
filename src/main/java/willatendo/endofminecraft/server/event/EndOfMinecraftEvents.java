package willatendo.endofminecraft.server.event;

import java.util.List;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import willatendo.endofminecraft.server.dimension.EndOfMinecraftDimensions;
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
		EntitySleepEvents.STOP_SLEEPING.register((livingEntity, blockPos) -> {
			Level level = livingEntity.level();
			if (level.dimension() == EndOfMinecraftDimensions.PLANET_ALPHA) {
				if (level instanceof ServerLevel serverLevel) {
					if (serverLevel.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
						long l = level.getLevelData().getDayTime() + 24000L;
						serverLevel.setDayTime(l - l % 24000L);
					}
//					serverLevel.wakeUpAllPlayers();
					if (serverLevel.getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE) && serverLevel.isRaining()) {
						serverLevel.resetWeatherCycle();
					}
				}
			}
		});
//		ServerEntityWorldChangeEvents.AFTER_ENTITY_CHANGE_WORLD.register((originalEntity, newEntity, originServerLevel, destinationServerLevel) -> {
//			if (destinationServerLevel.dimension() == EndOfMinecraftDimensions.PLANET_ALPHA) {
//				// Place Portal
//			}
//		});
//		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((serverPlayer, originServerLevel, destinationServerLevel) -> {
//			if (destinationServerLevel.dimension() == EndOfMinecraftDimensions.PLANET_ALPHA) {
//				// Place Portal
//			}
//		});
	}
}
