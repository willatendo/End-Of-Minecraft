package endofminecraft.server;

import static endofminecraft.EndOfMinecraftMod.ID;
import static endofminecraft.EndOfMinecraftMod.UTILS;

import java.util.List;
import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.biomes.ScorchlandBiome;
import endofminecraft.server.biomes.WastelandForestBiome;
import endofminecraft.server.biomes.WastelandsBiome;
import endofminecraft.server.block.PlanetAlphaPortalBlock;
import endofminecraft.server.effects.IrradiatedEffect;
import endofminecraft.server.util.EndRegistrate;
import endofminecraft.server.world.preset.EndChunkGenerator;
import endofminecraft.server.world.preset.EndSurfaceRules;
import endofminecraft.server.world.preset.EndWorldPreset;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
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
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
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
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tyrannotitanlib.library.item.TyrannoArmourMaterial;
import tyrannotitanlib.library.itemgroup.BasicCreativeTab;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class EndRegistry {
	public static final EndRegistrate REGISTRATE = EndOfMinecraftMod.CENTRAL_REGISTRATE.get().creativeModeTab(() -> EndRegistry.END_TAB);

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EndOfMinecraftMod.ID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EndOfMinecraftMod.ID);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EndOfMinecraftMod.ID);
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, EndOfMinecraftMod.ID);
	public static final DeferredRegister<ForgeWorldPreset> WORLD_PRESETS = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, EndOfMinecraftMod.ID);

	public static final BasicCreativeTab END_TAB = new BasicCreativeTab(ID, "end_tab");

	// Sounds
	public static final RegistryObject<SoundEvent> RADIATION_FUZZ = SOUND_EVENTS.register("sound.endofminecraft.radiation.fuzz", () -> new SoundEvent(UTILS.mod("sound.endofminecraft.radiation.fuzz")));

	// Particles
	public static final RegistryObject<ParticleType<?>> PLANET_ALPHA_PORTAL_PARTICLE = PARTICLE_TYPES.register("planet_alpha_portal_particle", () -> new SimpleParticleType(false));

	// Blocks
	public static final BlockEntry<PlanetAlphaPortalBlock> PLANET_ALPHA_PORTAL = REGISTRATE.block("planet_alpha_portal", PlanetAlphaPortalBlock::new).properties(properties -> properties.of(Material.PORTAL).noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> {
		return 11;
	})).blockstate((block, provider) -> portalBlock(provider, block.get())).loot((provider, block) -> provider.add(block, new LootTable.Builder())).register();

	// Items
	public static final ArmorMaterial RADIATION_SUIT = new TyrannoArmourMaterial(EndOfMinecraftMod.UTILS.mod("radiation_suit"), 364, new int[] { 1, 1, 2, 3 }, 10, () -> SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT);
	});

	public static final ItemEntry<Item> GEIGER_COUNTER = REGISTRATE.item("geiger_counter", Item::new).setData(ProviderType.ITEM_MODEL, (item, provider) -> {
		ModelFile model0 = provider.getExistingFile(provider.modLoc("item/geiger_counter_0"));
		ModelFile model1 = provider.getExistingFile(provider.modLoc("item/geiger_counter_1"));
		ModelFile model2 = provider.getExistingFile(provider.modLoc("item/geiger_counter_2"));
		ModelFile model3 = provider.getExistingFile(provider.modLoc("item/geiger_counter_3"));
		ModelFile model4 = provider.getExistingFile(provider.modLoc("item/geiger_counter_4"));
		ModelFile model5 = provider.getExistingFile(provider.modLoc("item/geiger_counter_5"));
		ModelFile model6 = provider.getExistingFile(provider.modLoc("item/geiger_counter_6"));
		provider.getBuilder(item.get().getRegistryName().getPath()).parent(new UncheckedModelFile("item/generated")).texture("layer0", "item/" + item.get().getRegistryName().getPath()).override().predicate(UTILS.mod("radiation"), 0.0F).model(model0).end().override().predicate(UTILS.mod("radiation"), 1.0F).model(model1).end().override().predicate(UTILS.mod("radiation"), 2.0F).model(model2).end().override().predicate(UTILS.mod("radiation"), 3.0F).model(model3).end().override().predicate(UTILS.mod("radiation"), 4.0F).model(model4).end().override().predicate(UTILS.mod("radiation"), 5.0F).model(model5).end().override().predicate(UTILS.mod("radiation"), 6.0F).model(model6).end();
	}).register();

	public static final ItemEntry<ArmorItem> RADIATION_SUIT_HELMET = REGISTRATE.item("radiation_suit_helmet", provider -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.HEAD, provider)).register();
	public static final ItemEntry<ArmorItem> RADIATION_SUIT_CHESTPLATE = REGISTRATE.item("radiation_suit_chestplate", provider -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.CHEST, provider)).register();
	public static final ItemEntry<ArmorItem> RADIATION_SUIT_LEGGINGS = REGISTRATE.item("radiation_suit_leggings", provider -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.LEGS, provider)).register();
	public static final ItemEntry<ArmorItem> RADIATION_SUIT_BOOTS = REGISTRATE.item("radiation_suit_boots", provider -> new ArmorItem(RADIATION_SUIT, EquipmentSlot.FEET, provider)).register();

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
	public static final MultiNoiseBiomeSource.Preset END_OF_THE_WORLD_NOISE = new MultiNoiseBiomeSource.Preset(UTILS.mod("end_of_the_world"), (biomes) -> {
		return new Climate.ParameterList<>(ImmutableList.of(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(EndRegistry.SCORCHLAND.getFirst())), Pair.of(Climate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(EndRegistry.WASTELANDS.getFirst())), Pair.of(Climate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrCreateHolder(EndRegistry.WASTELAND_FOREST.getFirst()))));
	});

	// Gen Settings
	public static final ResourceKey<NoiseGeneratorSettings> END_OF_THE_WORLD_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, UTILS.mod("end_of_the_world"));

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
		Registry.register(Registry.CHUNK_GENERATOR, UTILS.mod("end_chunk_generator"), EndChunkGenerator.CODEC);
	}

	public static void portalBlock(RegistrateBlockstateProvider provider, Block block) {
		String name = block.getRegistryName().getPath();
		ModelFile nsModel = provider.models().getBuilder(name + "_ns").texture("particle", "block/" + name).texture("portal", "block/" + name).element().from(0, 0, 6).to(16, 16, 10).face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#portal").end().face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#portal").end().end();
		ModelFile ewModel = provider.models().getBuilder(name + "_ew").texture("particle", "block/" + name).texture("portal", "block/" + name).element().from(6, 0, 0).to(10, 16, 16).face(Direction.EAST).uvs(0, 0, 16, 16).texture("#portal").end().face(Direction.WEST).uvs(0, 0, 16, 16).texture("#portal").end().end();
		provider.getVariantBuilder(block).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.X).addModels(new ConfiguredModel(nsModel)).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.Z).addModels(new ConfiguredModel(ewModel));
	}

	public static void init(IEventBus bus) {
		PARTICLE_TYPES.register(bus);
		MOB_EFFECTS.register(bus);
		BIOMES.register(bus);
		WORLD_PRESETS.register(bus);
	}
}
