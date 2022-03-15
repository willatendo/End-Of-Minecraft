package endofminecraft.server;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import java.util.List;
import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.biomes.ScorchlandBiome;
import endofminecraft.server.biomes.WastelandsBiome;
import endofminecraft.server.world.EndChunkGenerator;
import endofminecraft.server.world.EndSurfaceRules;
import endofminecraft.server.world.EndWorldPreset;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class ModRegistry {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, EndOfMinecraftMod.ID);
	public static final DeferredRegister<ForgeWorldPreset> WORLD_PRESETS = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, EndOfMinecraftMod.ID);

	// Configured Features
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> DEAD_TREE = FeatureUtils.register("dead_tree", Feature.TREE, createDeadTree().build());
	public static final Holder<PlacedFeature> CHECKED_DEAD_TREE = PlacementUtils.register("placed_dead_tree", ModRegistry.DEAD_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> WASTELAND_DEAD_TREE = FeatureUtils.register("wasteland_dead_tree", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ModRegistry.CHECKED_DEAD_TREE, 0.1F)), ModRegistry.CHECKED_DEAD_TREE));
	public static final Holder<PlacedFeature> PLACED_DEAD_TREE = PlacementUtils.register("placed_dead_tree", ModRegistry.DEAD_TREE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));

	private static TreeConfiguration.TreeConfigurationBuilder createDeadTree() {
		return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
	}

	// Biomes
	public static final Pair<ResourceKey<Biome>, RegistryObject<Biome>> SCORCHLAND = register(new ScorchlandBiome());
	public static final Pair<ResourceKey<Biome>, RegistryObject<Biome>> WASTELANDS = register(new WastelandsBiome());

	// Noise
	public static final MultiNoiseBiomeSource.Preset END_OF_THE_WORLD_NOISE = new MultiNoiseBiomeSource.Preset(UTILS.resource("end_of_the_world"), (biomes) -> {
		return new Climate.ParameterList<>(ImmutableList.of(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(ModRegistry.SCORCHLAND.getFirst())), Pair.of(Climate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(ModRegistry.WASTELANDS.getFirst()))));
	});

	// Gen Settings
	public static final ResourceKey<NoiseGeneratorSettings> END_OF_THE_WORLD_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, UTILS.resource("end_of_the_world"));

	public static final NoiseSettings END_OF_THE_WORLD_NOISE_SETTINGS = new NoiseSettings(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 8), new NoiseSlider(0.1171875D, 3, 0), 1, 2, TerrainProvider.overworld(false));

	// World Presets
	public static final RegistryObject<EndWorldPreset> END_OF_THE_WORLD = WORLD_PRESETS.register("end_of_the_world", () -> new EndWorldPreset());

	public static Pair<ResourceKey<Biome>, RegistryObject<Biome>> register(TyrannoBiome biome) {
		return Pair.of(biome.getKey(), BIOMES.register(biome.name().getPath(), () -> biome.getBiome()));
	}

	public static NoiseGeneratorSettings endSettings() {
		return new NoiseGeneratorSettings(END_OF_THE_WORLD_NOISE_SETTINGS, Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.overworld(END_OF_THE_WORLD_NOISE_SETTINGS, false), EndSurfaceRules.end(), 32, false, false, false, true);
	}

	public static void enqueue() {
		BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, END_OF_THE_WORLD_SETTINGS.location(), endSettings());
		Registry.register(Registry.CHUNK_GENERATOR, UTILS.resource("end_chunk_generator"), EndChunkGenerator.CODEC);
	}

	public static void init(IEventBus bus) {
		BIOMES.register(bus);
		WORLD_PRESETS.register(bus);
	}
}
