package willatendo.endofminecraft.server.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftStructures {
	public static final ResourceKey<Structure> ANOMALY_CAVE = register("anomaly_cave");
	public static final ResourceKey<Structure> VILLAGE_POST_APOCALYPTIC = register("village_post_apocalyptic");

	public static ResourceKey<Structure> register(String id) {
		return ResourceKey.create(Registries.STRUCTURE, EndOfMinecraftUtils.resource(id));
	}
}
