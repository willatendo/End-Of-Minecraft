package endofminecraft.content;

import endofminecraft.content.server.init.BiomeInit;
import endofminecraft.content.server.init.BlockInit;
import endofminecraft.content.server.init.EffectInit;
import endofminecraft.content.server.init.ItemInit;
import endofminecraft.content.server.init.ParticleInit;
import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.biome.ModConfiguredFeatures;
import endofminecraft.library.dimension.PlanetAlphaBiomeSource;
import endofminecraft.library.dimension.PlanetAlphaChunkGenerator;
import endofminecraft.library.worldtype.EndOfTheWorldPreset;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.ForgeRegistries;
import tyrannotitanlib.library.tyrannoregister.TyrannoRegister;

public class ModRegistry {
	public static SimpleParticleType particle(String id, SimpleParticleType type) {
		TyrannoRegister.registerParticle(id, type);
		return type;
	}

	public static Block block(String id, Block block) {
		TyrannoRegister.registerBlock(id, block);
		TyrannoRegister.registerItem(id, new BlockItem(block, new Properties()));
		return block;
	}

	public static Item item(String id, Item item) {
		TyrannoRegister.registerItem(id, item);
		return item;
	}

	public static MobEffect effect(String id, MobEffect effect) {
		TyrannoRegister.registerEffect(id, effect);
		return effect;
	}

	public static BlockEntityType<?> blockEntity(String id, BlockEntityType<?> entity) {
		TyrannoRegister.registerBlockEntity(id, entity);
		return entity;
	}

	public static EntityType<?> entity(String id, EntityType<?> entity) {
		TyrannoRegister.registerEntity(id, entity);
		return entity;
	}

	public static StructurePieceType structurePeice(String id, StructurePieceType structure) {
		TyrannoRegister.registerStructurePiece(ModUtils.ID, id, structure);
		return structure;
	}

	public static StructureFeature<NoneFeatureConfiguration> structure(String id, StructureFeature<NoneFeatureConfiguration> structure) {
		TyrannoRegister.registerStructure(id, structure);
		return structure;
	}

	public static Biome biome(String id, Biome biome) {
		TyrannoRegister.registerBiome(id, biome);
		return biome;
	}

	public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> configuredFeature(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ModUtils.rL(id), configuredFeature);
	}

	public static PlacedFeature placedFeature(String id, PlacedFeature placedFeature) {
		return Registry.register(BuiltinRegistries.PLACED_FEATURE, ModUtils.rL(id), placedFeature);
	}

	public static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> structureFeature(String id, ConfiguredStructureFeature<FC, F> feature) {
		TyrannoRegister.registerConfiguredStructure(ModUtils.ID, id, feature);
		return feature;
	}

	public static final ResourceKey<Level> PLANET_ALPHA_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, ModUtils.rL("planet_alpha"));
	public static final ResourceKey<DimensionType> PLANET_ALPHA_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModUtils.rL("planet_alpha"));
	public static final ResourceKey<LevelStem> PLANET_ALPHA_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, ModUtils.rL("planet_alpha"));
	public static final ResourceKey<NoiseGeneratorSettings> PLANET_ALPHA_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, ModUtils.rL("planet_alpha"));

	public static final EndOfTheWorldPreset END_WORLD_TYPE = new EndOfTheWorldPreset();

	public static void registry() {
		ModUtils.LOGGER.debug("Starting: Initialisation");

		ItemInit.init();
		EffectInit.init();
		ParticleInit.init();
		TileEntityInit.init();
		BlockInit.init();
		ModConfiguredFeatures.init();
		BiomeInit.init();

		Registry.register(Registry.BIOME_SOURCE, ModUtils.rL("planet_alpha_biomes"), PlanetAlphaBiomeSource.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, ModUtils.rL("planet_alpha_chunk_generator"), PlanetAlphaChunkGenerator.CODEC);

		END_WORLD_TYPE.setRegistryName(ModUtils.rL("end_world_type"));
		ForgeRegistries.WORLD_TYPES.register(END_WORLD_TYPE);

		ModUtils.LOGGER.debug("Finished: Initialisation");
	}
}
