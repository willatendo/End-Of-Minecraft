package willatendo.endofminecraft.server.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.VillagerType;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.List;

public class EndOfMinecraftVillagerTypes {
    public static final SimpleRegistry<VillagerType> VILLAGER_TYPES = SimpleRegistry.create(Registries.VILLAGER_TYPE, EndOfMinecraftUtils.ID);

    public static final SimpleHolder<VillagerType> POST_APOCALYPTIC = VILLAGER_TYPES.register("post_apocalyptic", () -> new VillagerType("post_apocalyptic"));

    public static void init(List<SimpleRegistry<?>> simpleRegistries) {
        simpleRegistries.add(VILLAGER_TYPES);
    }
}
