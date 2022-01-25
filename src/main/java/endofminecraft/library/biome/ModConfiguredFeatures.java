package endofminecraft.library.biome;

import java.util.OptionalInt;

import endofminecraft.content.ModRegistry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
	public static final ConfiguredFeature<TreeConfiguration, ?> DEAD_TREE = Feature.TREE.configured(createDeadTree().build());
	public static final PlacedFeature PLACED_DEAD_TREE = DEAD_TREE.filteredByBlockSurvival(Blocks.OAK_SAPLING);

	private static TreeConfiguration.TreeConfigurationBuilder createDeadTree() {
		return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
	}

	public static void init() {
		ModRegistry.configuredFeature("dead_tree", DEAD_TREE);
		ModRegistry.placedFeature("placed_dead_tree", PLACED_DEAD_TREE);
	}
}
