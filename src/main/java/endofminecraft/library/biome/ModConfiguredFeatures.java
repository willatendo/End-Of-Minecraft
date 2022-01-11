package endofminecraft.library.biome;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import endofminecraft.content.ModRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

public class ModConfiguredFeatures {
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> DEAD_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
	public static final ConfiguredFeature<?, ?> SINGLE_DEAD_TREE = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(DEAD_TREE.weighted(0.33333334F)), DEAD_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1)));

	public static void init() {
		ModRegistry.configuredFeature("single_dead_tree", SINGLE_DEAD_TREE);
	}
}
