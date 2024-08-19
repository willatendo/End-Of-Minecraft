package willatendo.endofminecraft.server.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import willatendo.endofminecraft.server.feature.EndOfMinecraftPlacedFeatures;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftBiomes {
    public static final ResourceKey<Biome> DEAD_TREE_GROVE = register("dead_tree_grove");
    public static final ResourceKey<Biome> SCORCHLAND = register("scorchland");
    public static final ResourceKey<Biome> WASTELAND = register("wasteland");

    public static ResourceKey<Biome> register(String id) {
        return ResourceKey.create(Registries.BIOME, EndOfMinecraftUtils.resource(id));
    }

    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder biomeGenerationSettings, Music music) {
        return biome(hasPrecipitation, temperature, downfall, 4159204, 329011, null, null, mobSpawnSettings, biomeGenerationSettings, music);
    }

    protected static int calculateSkyColor(float temperature) {
        float color = temperature / 3.0F;
        color = Mth.clamp(color, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - color * 0.05F, 0.5F + color * 0.1F, 1.0F);
    }

    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColour, Integer grassColorOverride, Integer foliageColorOverride, MobSpawnSettings.Builder mobSpwanSettings, BiomeGenerationSettings.Builder biomeGenerationSettings, Music music) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder().waterColor(waterColor).waterFogColor(waterFogColour).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music);
        if (grassColorOverride != null) {
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);
        }

        if (foliageColorOverride != null) {
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);
        }

        return new Biome.BiomeBuilder().hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects(biomespecialeffects$builder.build()).mobSpawnSettings(mobSpwanSettings.build()).generationSettings(biomeGenerationSettings.build()).build();
    }

    private static MobSpawnSettings.Builder monsters() {
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 1, 1, 1));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
        return mobSpawnSettings;
    }


    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder biomeGenerationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSprings(biomeGenerationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeGenerationSettings);
    }

    public static Biome deadTreeGrove(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers) {
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredWorldCarvers);
        BiomeDefaultFeatures.addFossilDecoration(biomeGenerationSettings);
        globalOverworldGeneration(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultGrass(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomeGenerationSettings);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EndOfMinecraftPlacedFeatures.DEAD_TREE_GROVE);

        return biome(true, 2.0F, 0.0F, monsters(), biomeGenerationSettings, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));
    }

    public static Biome scorchland(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers) {
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredWorldCarvers);
        BiomeDefaultFeatures.addFossilDecoration(biomeGenerationSettings);
        globalOverworldGeneration(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultGrass(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomeGenerationSettings);

        return biome(true, 2.0F, 0.0F, monsters(), biomeGenerationSettings, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));
    }

    public static Biome wasteland(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers) {
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredWorldCarvers);
        BiomeDefaultFeatures.addFossilDecoration(biomeGenerationSettings);
        globalOverworldGeneration(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultGrass(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertExtraVegetation(biomeGenerationSettings);
        BiomeDefaultFeatures.addDesertExtraDecoration(biomeGenerationSettings);

        return biome(true, 2.0F, 0.0F, monsters(), biomeGenerationSettings, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));
    }

    public static void bootstrap(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> placedFeatures = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        bootstapContext.register(DEAD_TREE_GROVE, deadTreeGrove(placedFeatures, configuredWorldCarvers));
        bootstapContext.register(SCORCHLAND, scorchland(placedFeatures, configuredWorldCarvers));
        bootstapContext.register(WASTELAND, wasteland(placedFeatures, configuredWorldCarvers));
    }
}
