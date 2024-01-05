package willatendo.endofminecraft.server.biome;

import java.util.List;
import java.util.function.Function;

import com.mojang.datafixers.util.Pair;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList.Preset;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList.Preset.SourceProvider;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftMultiNoiseBiomeSourceParameterListPresets {
	public static final Preset END_OF_MINECRAFT = new Preset(EndOfMinecraftUtils.resource("end_of_minecraft"), new SourceProvider() {
		@Override
		public <T> Climate.ParameterList<T> apply(Function<ResourceKey<Biome>, T> function) {
			return new Climate.ParameterList<T>(List.of(Pair.of(Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), function.apply(EndOfMinecraftBiomes.WASTELAND)), Pair.of(Climate.parameters(0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), function.apply(EndOfMinecraftBiomes.SCORCHLAND)), Pair.of(Climate.parameters(0.4f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), function.apply(EndOfMinecraftBiomes.DEAD_TREE_GROVE))));
		}
	});

	public static void init() {
		Preset.BY_NAME.put(EndOfMinecraftUtils.resource("end_of_minecraft"), END_OF_MINECRAFT);
	}
}
