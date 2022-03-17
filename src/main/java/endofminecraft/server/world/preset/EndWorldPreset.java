package endofminecraft.server.world.preset;

import com.mojang.serialization.Lifecycle;

import endofminecraft.server.EndRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.world.ForgeWorldPreset;

public class EndWorldPreset extends ForgeWorldPreset {
	public EndWorldPreset() {
		super(null);
	}

	@Override
	public ChunkGenerator createChunkGenerator(RegistryAccess registryAccess, long seed, String generatorSettings) {
		Registry<Biome> biome = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<NoiseGeneratorSettings> dimensionSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
		Registry<NormalNoise.NoiseParameters> noise = registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);
		Registry<StructureSet> structures = registryAccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY);
		return new EndChunkGenerator(structures, noise, EndRegistry.END_OF_THE_WORLD_NOISE.biomeSource(biome), seed, Holder.direct(dimensionSettings.getOrThrow(EndRegistry.END_OF_THE_WORLD_SETTINGS)));
	}

	@Override
	public WorldGenSettings createSettings(RegistryAccess registryAccess, long seed, boolean generateStructures, boolean generateLoot, String generatorSettings) {
		Registry<Biome> biome = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<NoiseGeneratorSettings> dimensionSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
		Registry<DimensionType> dimensionTypeRegistry = registryAccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
		return new WorldGenSettings(seed, generateStructures, generateLoot, WorldGenSettings.withOverworld(dimensionTypeRegistry, this.planetAlpha(dimensionTypeRegistry, biome, dimensionSettings, seed), this.createChunkGenerator(registryAccess, seed, null)));
	}

	public MappedRegistry<LevelStem> planetAlpha(Registry<DimensionType> dimensionType, Registry<Biome> biome, Registry<NoiseGeneratorSettings> dimensionSettings, long seed) {
		MappedRegistry<LevelStem> simpleregistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.stable(), null);
		return simpleregistry;
	}
}
