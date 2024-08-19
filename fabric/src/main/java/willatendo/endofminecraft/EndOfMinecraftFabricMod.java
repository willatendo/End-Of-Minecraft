package willatendo.endofminecraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import willatendo.endofminecraft.server.effect.EndOfMinecraftMobEffects;
import willatendo.endofminecraft.server.event.BasicEvents;
import willatendo.simplelibrary.server.registry.FabricRegister;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.ArrayList;
import java.util.List;

public class EndOfMinecraftFabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        List<SimpleRegistry<?>> simpleRegistries = new ArrayList<>();
        EndOfMinecraftMod.onInitialize(simpleRegistries);
        FabricRegister.register(simpleRegistries.toArray(SimpleRegistry[]::new));

        BasicEvents.common();

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
