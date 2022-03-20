package endofminecraft.server;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import java.util.List;
import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.biomes.ScorchlandBiome;
import endofminecraft.server.biomes.WastelandForestBiome;
import endofminecraft.server.biomes.WastelandsBiome;
import endofminecraft.server.block.PlanetAlphaPortalBlock;
import endofminecraft.server.effects.IrradiatedEffect;
import endofminecraft.server.world.preset.EndChunkGenerator;
import endofminecraft.server.world.preset.EndSurfaceRules;
import endofminecraft.server.world.preset.EndWorldPreset;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tyrannotitanlib.library.item.TyrannoArmourMaterial;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class EndRegistry {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EndOfMinecraftMod.ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EndOfMinecraftMod.ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EndOfMinecraftMod.ID);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EndOfMinecraftMod.ID);
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, EndOfMinecraftMod.ID);
	public static final DeferredRegister<ForgeWorldPreset> WORLD_PRESETS = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, EndOfMinecraftMod.ID);

	// Particles
	public static final RegistryObject<ParticleType<?>> PLANET_ALPHA_PORTAL_PARTICLE = PARTICLE_TYPES.register("planet_alpha_portal_particle", () -> new SimpleParticleType(false));

	// Blocks
	public static final RegistryObject<Block> PLANET_ALPHA_PORTAL = BLOCKS.register("planet_alpha_portal", () -> new PlanetAlphaPortalBlock(BlockBehaviour.Properties.of(Material.PORTAL).noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel((p_50870_) -> {
		return 11;
	})));

	// Items
	public static final ArmorMaterial RADIATION_SUIT = new TyrannoArmourMaterial(EndOfMinecraftMod.UTILS.resource("radiation_suit"), 364, new int[] { 1, 1, 2, 3 }, 10, () -> SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT);
	});

	public static final RegistryObject<Item> GEIGER_COUNTER = ITEMS.register("geiger_counter", () -> new Item(new Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> RADIATION_SUIT_HELMET = ITEMS.register("radiation_suit_helmet", () -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.HEAD, new Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> RADIATION_SUIT_CHESTPLATE = ITEMS.register("radiation_suit_chestplate", () -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.CHEST, new Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> RADIATION_SUIT_LEGGINGS = ITEMS.register("radiation_suit_leggings", () -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.LEGS, new Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> RADIATION_SUIT_BOOTS = ITEMS.register("radiation_suit_boots", () -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.FEET, new Properties().tab(CreativeModeTab.TAB_MISC)));

	// Damage
	public static final DamageSource IRRADIATION = (new DamageSource("irradiation")).bypassArmor();

	// Mob Effects
	public static final RegistryObject<MobEffect> IRRADIATED = MOB_EFFECTS.register("irradiated", () -> new IrradiatedEffect());

	// Configured Features
	public static String feature(String name) {
		return "endofminecraft:" + name;
	}

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> DEAD_TREE = FeatureUtils.register(feature("dead_tree"), Feature.TREE, createDeadTree().build());
	public static final Holder<PlacedFeature> CHECKED_DEAD_TREE = PlacementUtils.register(feature("checked_dead_tree"), EndRegistry.DEAD_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> WASTELANDS_TREES = FeatureUtils.register(feature("wastelands_trees"), Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(), EndRegistry.CHECKED_DEAD_TREE));
	public static final Holder<PlacedFeature> PLACED_DEAD_TREE = PlacementUtils.register(feature("placed_dead_tree"), EndRegistry.WASTELANDS_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));

	private static TreeConfiguration.TreeConfigurationBuilder createDeadTree() {
		return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
	}

	// Biomes
	public static final Pair<ResourceKey<Biome>, RegistryObject<Biome>> SCORCHLAND = register(new ScorchlandBiome());
	public static final Pair<ResourceKey<Biome>, RegistryObject<Biome>> WASTELANDS = register(new WastelandsBiome());
	public static final Pair<ResourceKey<Biome>, RegistryObject<Biome>> WASTELAND_FOREST = register(new WastelandForestBiome());

	// Noise
	public static final MultiNoiseBiomeSource.Preset END_OF_THE_WORLD_NOISE = new MultiNoiseBiomeSource.Preset(UTILS.resource("end_of_the_world"), (biomes) -> {
		return new Climate.ParameterList<>(ImmutableList.of(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(EndRegistry.SCORCHLAND.getFirst())), Pair.of(Climate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(EndRegistry.WASTELANDS.getFirst())), Pair.of(Climate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(EndRegistry.WASTELAND_FOREST.getFirst()))));
	});

	// Gen Settings
	public static final ResourceKey<NoiseGeneratorSettings> END_OF_THE_WORLD_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, UTILS.resource("end_of_the_world"));

	public static final NoiseSettings END_OF_THE_WORLD_NOISE_SETTINGS = new NoiseSettings(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 8), new NoiseSlider(0.1171875D, 3, 0), 1, 2, TerrainProvider.overworld(false));

	// Dimensions
	public static final ResourceKey<Level> PLANET_ALPHA = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("planet_alpha"));

	// World Presets
	public static final RegistryObject<EndWorldPreset> END_OF_THE_WORLD = WORLD_PRESETS.register("end_of_the_world", () -> new EndWorldPreset());

	public static Pair<ResourceKey<Biome>, RegistryObject<Biome>> register(TyrannoBiome biome) {
		return Pair.of(biome.getKey(), BIOMES.register(biome.name().getPath(), () -> biome.getBiome()));
	}

	public static NoiseGeneratorSettings endSettings() {
		return new NoiseGeneratorSettings(END_OF_THE_WORLD_NOISE_SETTINGS, Blocks.STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(), NoiseRouterData.overworld(END_OF_THE_WORLD_NOISE_SETTINGS, false), EndSurfaceRules.end(), 0, false, true, true, true);
	}

	public static void register() {
		BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, END_OF_THE_WORLD_SETTINGS.location(), endSettings());
		Registry.register(Registry.CHUNK_GENERATOR, UTILS.resource("end_chunk_generator"), EndChunkGenerator.CODEC);
	}

	public static void init(IEventBus bus) {
		PARTICLE_TYPES.register(bus);
		ITEMS.register(bus);
		BLOCKS.register(bus);
		MOB_EFFECTS.register(bus);
		BIOMES.register(bus);
		WORLD_PRESETS.register(bus);
	}
}
