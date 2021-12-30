package endofminecraft.library.biome;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModBiomeMaker 
{
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DIRT = SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState()));

	private static int calculateSkyColor(float tempature) 
	{
		float tempatureDivided = tempature / 3.0F;
		tempatureDivided = MathHelper.clamp(tempatureDivided, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - tempatureDivided * 0.05F, 0.5F + tempatureDivided * 0.1F, 1.0F);
	}
	
	public static Biome wasteland() 
	{
		MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();
		DefaultBiomeFeatures.desertSpawns(mobSpawnBuilder);
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(DIRT).addFeature(Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.SINGLE_DEAD_TREE);
		//generationBuilder.addStructureStart(Configured.CONFIGURED_ANOMALY_CAVE);

		DefaultBiomeFeatures.addDefaultCarvers(generationBuilder);
		DefaultBiomeFeatures.addDesertLakes(generationBuilder);
		DefaultBiomeFeatures.addDefaultMonsterRoom(generationBuilder);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(generationBuilder);
		DefaultBiomeFeatures.addDefaultOres(generationBuilder);
		DefaultBiomeFeatures.addDefaultSoftDisks(generationBuilder);
		DefaultBiomeFeatures.addDesertVegetation(generationBuilder);
		DefaultBiomeFeatures.addDefaultSprings(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(2.0F)).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome scorchland()
	{
		MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();
		DefaultBiomeFeatures.desertSpawns(mobSpawnBuilder);
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.DESERT);
					
		DefaultBiomeFeatures.addDefaultCarvers(generationBuilder);
		DefaultBiomeFeatures.addDesertLakes(generationBuilder);
		DefaultBiomeFeatures.addDefaultMonsterRoom(generationBuilder);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(generationBuilder);
		DefaultBiomeFeatures.addDefaultOres(generationBuilder);
		DefaultBiomeFeatures.addDefaultSoftDisks(generationBuilder);
		DefaultBiomeFeatures.addDesertVegetation(generationBuilder);
		DefaultBiomeFeatures.addDefaultSprings(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(4.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(2.0F)).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
}
