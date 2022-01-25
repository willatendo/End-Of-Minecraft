package endofminecraft.library.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;

public class ModBiomeMaker {

	private static int calculateSkyColor(float tempature) {
		float tempatureDivided = tempature / 3.0F;
		tempatureDivided = Mth.clamp(tempatureDivided, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - tempatureDivided * 0.05F, 0.5F + tempatureDivided * 0.1F, 1.0F);
	}

	public static Biome wasteland() {
		MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();

		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).addFeature(Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PLACED_DEAD_TREE);

		BiomeDefaultFeatures.addDefaultCarversAndLakes(generationBuilder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(generationBuilder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(generationBuilder);
		BiomeDefaultFeatures.addDefaultOres(generationBuilder);
		BiomeDefaultFeatures.addDefaultSoftDisks(generationBuilder);
		BiomeDefaultFeatures.addDesertVegetation(generationBuilder);
		BiomeDefaultFeatures.addDefaultSprings(generationBuilder);

		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.PLAINS).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(2.0F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}

	public static Biome scorchland() {
		MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();

		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder());

		BiomeDefaultFeatures.addDefaultCarversAndLakes(generationBuilder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(generationBuilder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(generationBuilder);
		BiomeDefaultFeatures.addDefaultOres(generationBuilder);
		BiomeDefaultFeatures.addDefaultSoftDisks(generationBuilder);
		BiomeDefaultFeatures.addDesertVegetation(generationBuilder);
		BiomeDefaultFeatures.addDefaultSprings(generationBuilder);

		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.DESERT).temperature(4.0F).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(2.0F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
}
