package endofminecraft.server.world.dimension;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.EndRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = EndOfMinecraftMod.ID)
public class DimensionHelper {
	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) {
		LevelAccessor level = event.getWorld();
		if (level instanceof ServerLevel server) {
			if (server.dimension() == EndRegistry.PLANET_ALPHA) {
				if (level.getLevelData() instanceof DerivedLevelData data) {
					data.wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
