package willatendo.endofminecraft.server.event;

import net.minecraft.world.entity.npc.VillagerType;
import willatendo.endofminecraft.client.properties.EndOfMinecraftItemProperties;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomes;
import willatendo.endofminecraft.server.entity.EndOfMinecraftVillagerTypes;

public class BasicEvents {
    public static void common() {
        VillagerType.BY_BIOME.put(EndOfMinecraftBiomes.DEAD_TREE_GROVE, EndOfMinecraftVillagerTypes.POST_APOCALYPTIC.get());
        VillagerType.BY_BIOME.put(EndOfMinecraftBiomes.SCORCHLAND, EndOfMinecraftVillagerTypes.POST_APOCALYPTIC.get());
        VillagerType.BY_BIOME.put(EndOfMinecraftBiomes.WASTELAND, EndOfMinecraftVillagerTypes.POST_APOCALYPTIC.get());

        EndOfMinecraftItemProperties.init();
    }
}
