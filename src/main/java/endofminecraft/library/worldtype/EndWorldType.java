package endofminecraft.library.worldtype;

import com.mojang.serialization.Lifecycle;

import endofminecraft.library.dimension.PlanetAlphaBiomeProvider;
import endofminecraft.library.util.ModRegistry;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraftforge.common.world.ForgeWorldType;

public class EndWorldType extends ForgeWorldType
{
	public EndWorldType() 
	{
		super(null);
	}
	
	@Override
	public ChunkGenerator createChunkGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed, String generatorSettings) 
	{
		return new NoiseChunkGenerator(new EndWorldBiomeProvider(seed, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(DimensionSettings.OVERWORLD));
	}
	
	@Override
	public DimensionGeneratorSettings createSettings(DynamicRegistries dynamicRegistries, long seed, boolean generateStructures, boolean generateLoot, String generatorSettings) 
	{
		Registry<Biome> biomeRegistry = dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<DimensionSettings> dimensionSettingsRegistry = dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
		Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);

        return new DimensionGeneratorSettings(seed, generateStructures, generateLoot, DimensionGeneratorSettings.withOverworld(dimensionTypeRegistry, planetAlpha(dimensionTypeRegistry, biomeRegistry, dimensionSettingsRegistry, seed), createChunkGenerator(biomeRegistry, dimensionSettingsRegistry, seed, null)));
	}
	
	private static ChunkGenerator defaultPlanetAlphaGenerator(Registry<Biome> biome, Registry<DimensionSettings> dimensionSettings, long seed) 
	{
		return new NoiseChunkGenerator(new PlanetAlphaBiomeProvider(seed, biome), seed, () -> 
		{    
			return dimensionSettings.getOrThrow(DimensionSettings.OVERWORLD);
		});
	}
	
	public static SimpleRegistry<Dimension> planetAlpha(Registry<DimensionType> dimensionType, Registry<Biome> biome, Registry<DimensionSettings> dimensionSettings, long seed) 
	{
		SimpleRegistry<Dimension> simpleregistry = new SimpleRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
		simpleregistry.register(ModRegistry.PLANET_ALPHA_DIMENSION, new Dimension(() -> 
		{
			return dimensionType.getOrThrow(ModRegistry.PLANET_ALPHA_DIMENSION_TYPE);
		}, defaultPlanetAlphaGenerator(biome, dimensionSettings, seed)), Lifecycle.stable());
		return simpleregistry;
	}
}
