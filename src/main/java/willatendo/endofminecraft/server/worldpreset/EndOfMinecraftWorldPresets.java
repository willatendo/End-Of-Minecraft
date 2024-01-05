package willatendo.endofminecraft.server.worldpreset;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftWorldPresets {
	public static final ResourceKey<WorldPreset> END_OF_MINECRAFT = register("end_of_minecraft");

	public static ResourceKey<WorldPreset> register(String string) {
		return ResourceKey.create(Registries.WORLD_PRESET, EndOfMinecraftUtils.resource(string));
	}
}
