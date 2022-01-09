package endofminecraft.library.event;

import endofminecraft.content.server.init.EffectInit;
import endofminecraft.library.util.ModUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE, modid = ModUtils.ID)
public class EndOfMinecraftEvents {
	@SubscribeEvent
	public static void livingEvent(PlayerTickEvent event) {
		PlayerEntity entity = event.player;
		World world = entity.level;
		if (world.canSeeSky(entity.blockPosition())) {
			if (world.dimension() == World.OVERWORLD) {
				if (world.isRaining()) {
					entity.addEffect(new EffectInstance(EffectInit.IRRADIATED, 500));
				}
			}
		}
	}
}
