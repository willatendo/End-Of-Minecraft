package willatendo.endofminecraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod(EndOfMinecraftUtils.ID)
public class EndOfMinecraftNeoforgeMod {
    public static final List<SimpleRegistry<?>> REGISTRIES = new ArrayList<>();

    public EndOfMinecraftNeoforgeMod(IEventBus modEventBus) {
        EndOfMinecraftMod.onInitialize(REGISTRIES);
    }
}
