package willatendo.endofminecraft.server.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftDimensions {
	public static final ResourceKey<Level> PLANET_ALPHA = register("planet_alpha");

	public static ResourceKey<Level> register(String id) {
		return ResourceKey.create(Registries.DIMENSION, EndOfMinecraftUtils.resource(id));
	}
}
