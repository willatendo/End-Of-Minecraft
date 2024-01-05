package willatendo.endofminecraft.server.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.npc.VillagerType;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomes;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

public class EndOfMinecraftVillagerTypes {
	public static final SimpleRegistry<VillagerType> VILLAGER_TYPES = SimpleRegistry.create(BuiltInRegistries.VILLAGER_TYPE, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<VillagerType> POST_APOCALYPTIC = VILLAGER_TYPES.register("post_apocalyptic", () -> new VillagerType("post_apocalyptic"));

	public static void init() {
		VillagerType.BY_BIOME.put(EndOfMinecraftBiomes.DEAD_TREE_GROVE, EndOfMinecraftVillagerTypes.POST_APOCALYPTIC.get());
		VillagerType.BY_BIOME.put(EndOfMinecraftBiomes.SCORCHLAND, EndOfMinecraftVillagerTypes.POST_APOCALYPTIC.get());
		VillagerType.BY_BIOME.put(EndOfMinecraftBiomes.WASTELAND, EndOfMinecraftVillagerTypes.POST_APOCALYPTIC.get());
	}
}
