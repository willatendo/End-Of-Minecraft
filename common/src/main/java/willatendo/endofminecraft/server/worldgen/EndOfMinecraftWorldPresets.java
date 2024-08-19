package willatendo.endofminecraft.server.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.Map;

public class EndOfMinecraftWorldPresets {
    public static final ResourceKey<WorldPreset> END_OF_MINECRAFT = register("end_of_minecraft");

    public static ResourceKey<WorldPreset> register(String string) {
        return ResourceKey.create(Registries.WORLD_PRESET, EndOfMinecraftUtils.resource(string));
    }

    public static void bootstrap(BootstapContext<WorldPreset> bootstapContext) {
        HolderGetter<DimensionType> dimensionTypes = bootstapContext.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<MultiNoiseBiomeSourceParameterList> multiNoiseBiomeSourceParameterLists = bootstapContext.lookup(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = bootstapContext.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<Biome> biomes = bootstapContext.lookup(Registries.BIOME);
        bootstapContext.register(END_OF_MINECRAFT, new WorldPreset(Map.of(LevelStem.OVERWORLD, new LevelStem(dimensionTypes.getOrThrow(BuiltinDimensionTypes.OVERWORLD), new NoiseBasedChunkGenerator(MultiNoiseBiomeSource.createFromPreset(multiNoiseBiomeSourceParameterLists.getOrThrow(EndOfMinecraftMultiNoiseBiomeSourceParameterLists.END_OF_MINECRAFT)), noiseSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD))), LevelStem.NETHER, new LevelStem(dimensionTypes.getOrThrow(BuiltinDimensionTypes.NETHER), new NoiseBasedChunkGenerator(MultiNoiseBiomeSource.createFromPreset(multiNoiseBiomeSourceParameterLists.getOrThrow(MultiNoiseBiomeSourceParameterLists.NETHER)), noiseSettings.getOrThrow(NoiseGeneratorSettings.NETHER))), LevelStem.END, new LevelStem(dimensionTypes.getOrThrow(BuiltinDimensionTypes.END), new NoiseBasedChunkGenerator(TheEndBiomeSource.create(biomes), noiseSettings.getOrThrow(NoiseGeneratorSettings.END))))));
    }
}
