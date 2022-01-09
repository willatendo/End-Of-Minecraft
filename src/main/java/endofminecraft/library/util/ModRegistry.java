package endofminecraft.library.util;

import endofminecraft.content.server.init.BiomeInit;
import endofminecraft.content.server.init.BlockInit;
import endofminecraft.content.server.init.EffectInit;
import endofminecraft.content.server.init.ItemInit;
import endofminecraft.content.server.init.ParticleInit;
import endofminecraft.content.server.init.StructureInit;
import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.biome.ModConfiguredFeatures;
import endofminecraft.library.dimension.PlanetAlphaBiomeProvider;
import endofminecraft.library.dimension.PlanetAlphaChunkGenerator;
import endofminecraft.library.worldtype.EndWorldType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.registries.ForgeRegistries;
import tyrannotitanlib.library.tyrannoregister.TyrannoRegister;

public class ModRegistry {
	public static BasicParticleType particle(String id, BasicParticleType type) {
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

	public static Effect effect(String id, Effect effect) {
		TyrannoRegister.registerEffect(id, effect);
		return effect;
	}

	public static TileEntityType<?> blockEntity(String id, TileEntityType<?> entity) {
		TyrannoRegister.registerBlockEntity(id, entity);
		return entity;
	}

	public static EntityType<?> entity(String id, EntityType<?> entity) {
		TyrannoRegister.registerEntity(id, entity);
		return entity;
	}

	public static Structure<NoFeatureConfig> structure(String id, Structure<NoFeatureConfig> structure) {
		TyrannoRegister.registerStructure(id, structure);
		return structure;
	}

	public static Biome biome(String id, Biome biome) {
		TyrannoRegister.registerBiome(id, biome);
		return biome;
	}

	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> configuredFeature(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ModUtils.rL(id), configuredFeature);
	}

	public static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> structureFeature(String id, StructureFeature<FC, F> feature) {
		TyrannoRegister.registerConfiguredStructure(ModUtils.ID, id, feature);
		return feature;
	}

	public static final RegistryKey<World> PLANET_ALPHA_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, ModUtils.rL("planet_alpha"));
	public static final RegistryKey<DimensionType> PLANET_ALPHA_DIMENSION_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModUtils.rL("planet_alpha"));
	public static final RegistryKey<Dimension> PLANET_ALPHA_DIMENSION = RegistryKey.create(Registry.LEVEL_STEM_REGISTRY, ModUtils.rL("planet_alpha"));
	public static final RegistryKey<DimensionSettings> PLANET_ALPHA_SETTINGS = RegistryKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, ModUtils.rL("planet_alpha"));

	public static final EndWorldType END_WORLD_TYPE = new EndWorldType();

	public static void registry() {
		ModUtils.LOGGER.debug("Starting: Initialisation");

		ItemInit.init();
		EffectInit.init();
		ParticleInit.init();
		TileEntityInit.init();
		BlockInit.init();
		StructureInit.init();
		ModConfiguredFeatures.init();
		BiomeInit.init();

		Registry.register(Registry.BIOME_SOURCE, ModUtils.rL("planet_alpha_biomes"), PlanetAlphaBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, ModUtils.rL("planet_alpha_chunk_generator"), PlanetAlphaChunkGenerator.CODEC);

		END_WORLD_TYPE.setRegistryName(ModUtils.rL("end_world_type"));
		ForgeRegistries.WORLD_TYPES.register(END_WORLD_TYPE);

		ModUtils.LOGGER.debug("Finished: Initialisation");
	}
}
