package willatendo.endofminecraft.server.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import willatendo.endofminecraft.server.effect.EndOfMinecraftMobEffects;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = EndOfMinecraftUtils.ID)
public class ForgeServerEvents {
    @SubscribeEvent
    public static void irradiate(TickEvent.ServerTickEvent event) {
        ServerLevel serverLevel = event.getServer().overworld();
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
}
