package endofminecraft.content.server.init;

import endofminecraft.content.ModRegistry;
import endofminecraft.library.structure.AnomalyCave;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class StructureInit {
	public static final Structure<NoFeatureConfig> ANOMALY_CAVE = ModRegistry.structure("anomaly_cave", new AnomalyCave(NoFeatureConfig.CODEC));
//	public static final IStructurePieceType ANOMALY_CAVE_PIECE = ModRegistry.structurePeice("anomaly_cave", null);

	public static final class Configured {
		public static final StructureFeature<NoFeatureConfig, ?> CONFIGURED_ANOMALY_CAVE = ModRegistry.structureFeature("anomaly_cave", StructureInit.ANOMALY_CAVE.configured(IFeatureConfig.NONE));

		public static void registerConfiguredFeatures() {
			FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.ANOMALY_CAVE, CONFIGURED_ANOMALY_CAVE);
		}
	}

	public static void registerNoiseSettings() {
		Structure.STRUCTURES_REGISTRY.put("anomaly_cave", StructureInit.ANOMALY_CAVE);
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> {
			settings.structureSettings().structureConfig().put(StructureInit.ANOMALY_CAVE, new StructureSeparationSettings(32, 8, 951304639));
		});
	}

	public static void init() {
	}
}
