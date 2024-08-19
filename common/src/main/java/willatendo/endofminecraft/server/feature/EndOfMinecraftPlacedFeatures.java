package willatendo.endofminecraft.server.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.List;

public class EndOfMinecraftPlacedFeatures {
    public static final ResourceKey<PlacedFeature> LARGE_DEAD_TREE = register("large_dead_tree");
    public static final ResourceKey<PlacedFeature> SMALL_DEAD_TREE = register("small_dead_tree");

    public static final ResourceKey<PlacedFeature> DEAD_TREE_GROVE = register("dead_tree_grove");

    public static ResourceKey<PlacedFeature> register(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, EndOfMinecraftUtils.resource(string));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> bootstapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
        PlacementModifier placementModifier = SurfaceWaterDepthFilter.forMaxDepth(0);

        PlacementUtils.register(bootstapContext, LARGE_DEAD_TREE, configuredFeatures.getOrThrow(EndOfMinecraftConfiguredFeatures.LARGE_DEAD_TREE), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        PlacementUtils.register(bootstapContext, SMALL_DEAD_TREE, configuredFeatures.getOrThrow(EndOfMinecraftConfiguredFeatures.SMALL_DEAD_TREE), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));

        PlacementUtils.register(bootstapContext, DEAD_TREE_GROVE, configuredFeatures.getOrThrow(EndOfMinecraftConfiguredFeatures.DEAD_TREE_GROVE), PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), placementModifier, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
    }
}
