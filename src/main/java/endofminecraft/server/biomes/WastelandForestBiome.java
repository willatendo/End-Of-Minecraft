package endofminecraft.server.biomes;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import endofminecraft.server.EndRegistry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class WastelandForestBiome extends TyrannoBiome {
	private static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	private static final MobSpawnSettings.Builder MOB_SPAWNS = new MobSpawnSettings.Builder();

	public WastelandForestBiome() {
		super(new Biome.BiomeBuilder().biomeCategory(BiomeCategory.PLAINS).precipitation(Precipitation.RAIN).downfall(0.0F).temperature(2.0F).specialEffects(new BiomeSpecialEffects.Builder().fogColor(BASE_FOG_COLOUR).skyColor(calculateSkyColor(2.0F)).waterColor(BASE_WATER_COLOUR).waterFogColor(BASE_WATER_FOG_COLOUR).build()).generationSettings(GENERATION.build()).mobSpawnSettings(MOB_SPAWNS.build()));
	}

	static {
		globalOverworldGeneration(GENERATION);
		BiomeDefaultFeatures.addDefaultOres(GENERATION);
		BiomeDefaultFeatures.addDesertVegetation(GENERATION);
		BiomeDefaultFeatures.addDefaultMushrooms(GENERATION);
		GENERATION.addFeature(Decoration.VEGETAL_DECORATION, EndRegistry.PLACED_DEAD_TREE);
	}

	@Override
	public ResourceLocation name() {
		return UTILS.mod("wasteland_forest");
	}
}
