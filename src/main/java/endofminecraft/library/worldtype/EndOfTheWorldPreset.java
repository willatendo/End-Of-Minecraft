package endofminecraft.library.worldtype;

import com.mojang.serialization.Lifecycle;

import endofminecraft.content.ModRegistry;
import endofminecraft.library.dimension.PlanetAlphaBiomeSource;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraftforge.common.world.ForgeWorldPreset;

public class EndOfTheWorldPreset extends ForgeWorldPreset {
	public EndOfTheWorldPreset() {
		super(null);
	}
	
	@Override
	public ChunkGenerator createChunkGenerator(RegistryAccess registryAccess, long seed, String generatorSettings) {
		return MultiNoiseBiomeSource.Preset.OVERWORLD;
	}

	@Override
	public WorldGenSettings createSettings(RegistryAccess dynamicRegistries, long seed, boolean generateStructures, boolean generateLoot, String generatorSettings) {
		Registry<Biome> biomeRegistry = dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<NoiseGeneratorSettings> dimensionSettingsRegistry = dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
		Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);

		return new WorldGenSettings(seed, generateStructures, generateLoot, WorldGenSettings.withOverworld(dimensionTypeRegistry, this.planetAlpha(dimensionTypeRegistry, biomeRegistry, dimensionSettingsRegistry, seed), this.createChunkGenerator(biomeRegistry, dimensionSettingsRegistry, seed, null)));
	}

	private ChunkGenerator defaultPlanetAlphaGenerator(Registry<Biome> biome, Registry<NoiseGeneratorSettings> dimensionSettings, long seed) {
		return new NoiseBasedChunkGenerator(new PlanetAlphaBiomeSource(seed, biome), seed, () -> {
			return dimensionSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD);
		});
	}

	public MappedRegistry<LevelStem> planetAlpha(Registry<DimensionType> dimensionType, Registry<Biome> biome, Registry<NoiseGeneratorSettings> dimensionSettings, long seed) {
		MappedRegistry<LevelStem> simpleregistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
		simpleregistry.register(ModRegistry.PLANET_ALPHA_DIMENSION, new LevelStem(() -> {
			return dimensionType.getOrThrow(ModRegistry.PLANET_ALPHA_DIMENSION_TYPE);
		}, defaultPlanetAlphaGenerator(biome, dimensionSettings, seed)), Lifecycle.stable());
		return simpleregistry;
	}
}
