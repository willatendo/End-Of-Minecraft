package willatendo.endofminecraft.server.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftBiomes {
	public static final ResourceKey<Biome> DEAD_TREE_GROVE = register("dead_tree_grove");
	public static final ResourceKey<Biome> SCORCHLAND = register("scorchland");
	public static final ResourceKey<Biome> WASTELAND = register("wasteland");

	public static ResourceKey<Biome> register(String id) {
		return ResourceKey.create(Registries.BIOME, EndOfMinecraftUtils.resource(id));
	}
}
