package endofminecraft.server.biomes;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class ScorchlandBiome extends TyrannoBiome {
	private static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	private static final MobSpawnSettings.Builder MOB_SPAWNS = new MobSpawnSettings.Builder();

	public ScorchlandBiome() {
		super(new Biome.BiomeBuilder().biomeCategory(BiomeCategory.DESERT).precipitation(Precipitation.RAIN).downfall(0.0F).temperature(2.0F).specialEffects(new BiomeSpecialEffects.Builder().fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).skyColor(BaseBiomeInfo.calculateSkyColor(2.0F)).waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).build()).generationSettings(GENERATION.build()).mobSpawnSettings(MOB_SPAWNS.build()));
	}

	static {
		globalOverworldGeneration(GENERATION);
		BiomeDefaultFeatures.addDefaultOres(GENERATION);
		BiomeDefaultFeatures.addDesertVegetation(GENERATION);
		BiomeDefaultFeatures.addDefaultMushrooms(GENERATION);
	}

	private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
		BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
		BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
	}

	@Override
	public ResourceLocation name() {
		return UTILS.resource("scorchland");
	}
}
