package endofminecraft.content.server.init;

import endofminecraft.library.structure.AnomalyCave;
import endofminecraft.library.util.ModRegistry;
import endofminecraft.library.util.ModUtils;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class StructureInit 
{
	public static final Structure<NoFeatureConfig> ANOMALY_CAVE = ModRegistry.structure("anomaly_cave", new AnomalyCave(NoFeatureConfig.CODEC));
	
	public static final class Configured 
	{
		public static final StructureFeature<NoFeatureConfig, ?> CONFIGURED_ANOMALY_CAVE = StructureInit.ANOMALY_CAVE.configured(IFeatureConfig.NONE);
		
		private static <FC extends IFeatureConfig> void register(String id, StructureFeature<FC, ?> stuctureFeature) 
		{
			Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, ModUtils.rL(id), stuctureFeature);
		}

		public static void registerConfiguredFeatures() 
		{
			register("anomaly_cave", CONFIGURED_ANOMALY_CAVE);
			FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.ANOMALY_CAVE, CONFIGURED_ANOMALY_CAVE);
		}
	}
	
	public static void registerNoiseSettings()
	{
		Structure.STRUCTURES_REGISTRY.put("anomaly_cave", StructureInit.ANOMALY_CAVE);
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> 
		{
			settings.structureSettings().structureConfig().put(StructureInit.ANOMALY_CAVE, new StructureSeparationSettings(32, 8, 951304639));
		});
	}
	
	public static void init() { }
}
