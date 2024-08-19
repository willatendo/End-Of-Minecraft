package willatendo.endofminecraft.server.feature;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.List;
import java.util.OptionalInt;

public class EndOfMinecraftConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_DEAD_TREE = register("large_dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_DEAD_TREE = register("small_dead_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TREE_GROVE = register("dead_tree_grove");

    public static ResourceKey<ConfiguredFeature<?, ?>> register(String string) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, EndOfMinecraftUtils.resource(string));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createLargeDeadTree() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves, int baseHeight, int randHeightA, int randHeightB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, randHeightA, randHeightB), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createSmallDeadTree() {
        return createStraightBlobTree(Blocks.OAK_LOG, Blocks.AIR, 4, 2, 0, 2).ignoreVines();
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
        HolderGetter<PlacedFeature> placedFeature = bootstapContext.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(bootstapContext, LARGE_DEAD_TREE, Feature.TREE, createLargeDeadTree().build());
        FeatureUtils.register(bootstapContext, SMALL_DEAD_TREE, Feature.TREE, createSmallDeadTree().build());

        FeatureUtils.register(bootstapContext, DEAD_TREE_GROVE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeature.getOrThrow(EndOfMinecraftPlacedFeatures.LARGE_DEAD_TREE), 0.1F)), placedFeature.getOrThrow(EndOfMinecraftPlacedFeatures.SMALL_DEAD_TREE)));
    }
}
