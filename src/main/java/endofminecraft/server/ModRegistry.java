package endofminecraft.server;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import endofminecraft.server.biomes.ScorchlandBiome;
import endofminecraft.server.world.EndChunkGenerator;
import endofminecraft.server.world.EndSurfaceRules;
import endofminecraft.server.world.EndWorldPreset;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraftforge.common.world.ForgeWorldPreset;
import tyrannotitanlib.tyranniregister.TyrannoRegister;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class ModRegistry {
	// Biomes
	public static final Pair<ResourceKey<Biome>, Biome> SCORCHLAND = register(new ScorchlandBiome());

	// Noise
	public static final MultiNoiseBiomeSource.Preset END_OF_THE_WORLD_NOISE = new MultiNoiseBiomeSource.Preset(UTILS.resource("end_of_the_world"), (biomes) -> {
		return new Climate.ParameterList<>(ImmutableList.of(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
			return biomes.getOrThrow(ModRegistry.SCORCHLAND.getFirst());
		})));
	});

	// Gen Settings
	public static final ResourceKey<NoiseGeneratorSettings> END_OF_THE_WORLD_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, UTILS.resource("end_of_the_world"));

	// World Presets
	public static final ForgeWorldPreset END_OF_THE_WORLD = TyrannoRegister.registerWorldPreset("end_of_the_world", new EndWorldPreset());

	public static Pair<ResourceKey<Biome>, Biome> register(TyrannoBiome biome) {
		return Pair.of(biome.getKey(), TyrannoRegister.registerBiome(biome.name().getPath(), biome.getBiome()));
	}

	public static NoiseGeneratorSettings end() {
		return new NoiseGeneratorSettings(new StructureSettings(true), NoiseSettings.create(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 8), new NoiseSlider(0.1171875D, 3, 0), 1, 2, false, false, false, TerrainProvider.overworld(false)), Blocks.STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(), EndSurfaceRules.end(), 1, false, true, true, true, true, false);
	}

	public static void enqueue() {
		BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, END_OF_THE_WORLD_SETTINGS.location(), end());
		Registry.register(Registry.CHUNK_GENERATOR, UTILS.resource("end_chunk_generator"), EndChunkGenerator.CODEC);
	}

	public static void init() {
	}
}
