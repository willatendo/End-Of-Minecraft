package endofminecraft.server.world.preset;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import endofminecraft.server.EndRegistry;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class EndSurfaceRules {
	public static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
	public static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
	public static final SurfaceRules.RuleSource WHITE_TERRACOTTA = makeStateRule(Blocks.WHITE_TERRACOTTA);
	public static final SurfaceRules.RuleSource ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
	public static final SurfaceRules.RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
	public static final SurfaceRules.RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
	public static final SurfaceRules.RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
	public static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
	public static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
	public static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
	public static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
	public static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
	public static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
	public static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
	public static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
	public static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
	public static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
	public static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
	public static final SurfaceRules.RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
	public static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
	public static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
	public static final SurfaceRules.RuleSource ICE = makeStateRule(Blocks.ICE);
	public static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
	public static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);
	public static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
	public static final SurfaceRules.RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
	public static final SurfaceRules.RuleSource SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
	public static final SurfaceRules.RuleSource BASALT = makeStateRule(Blocks.BASALT);
	public static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
	public static final SurfaceRules.RuleSource WARPED_WART_BLOCK = makeStateRule(Blocks.WARPED_WART_BLOCK);
	public static final SurfaceRules.RuleSource WARPED_NYLIUM = makeStateRule(Blocks.WARPED_NYLIUM);
	public static final SurfaceRules.RuleSource NETHER_WART_BLOCK = makeStateRule(Blocks.NETHER_WART_BLOCK);
	public static final SurfaceRules.RuleSource CRIMSON_NYLIUM = makeStateRule(Blocks.CRIMSON_NYLIUM);
	public static final SurfaceRules.RuleSource ENDSTONE = makeStateRule(Blocks.END_STONE);

	public static SurfaceRules.RuleSource makeStateRule(Block block) {
		return SurfaceRules.state(block.defaultBlockState());
	}

	public static SurfaceRules.RuleSource end() {
		SurfaceRules.ConditionSource surfacerules$conditionsource = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
		SurfaceRules.ConditionSource surfacerules$conditionsource1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
		SurfaceRules.ConditionSource surfacerules$conditionsource2 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
		SurfaceRules.ConditionSource surfacerules$conditionsource3 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
		SurfaceRules.ConditionSource surfacerules$conditionsource4 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
		SurfaceRules.ConditionSource surfacerules$conditionsource5 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
		SurfaceRules.ConditionSource surfacerules$conditionsource6 = SurfaceRules.waterBlockCheck(-1, 0);
		SurfaceRules.ConditionSource surfacerules$conditionsource7 = SurfaceRules.waterBlockCheck(0, 0);
		SurfaceRules.ConditionSource surfacerules$conditionsource8 = SurfaceRules.waterStartCheck(-6, -1);
		SurfaceRules.ConditionSource surfacerules$conditionsource9 = SurfaceRules.hole();
		SurfaceRules.ConditionSource surfacerules$conditionsource10 = SurfaceRules.isBiome(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
		SurfaceRules.ConditionSource surfacerules$conditionsource11 = SurfaceRules.steep();
		SurfaceRules.RuleSource surfacerules$rulesource = SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource6, GRASS_BLOCK), DIRT);
		SurfaceRules.RuleSource surfacerules$rulesource1 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND);
		SurfaceRules.RuleSource wasteland = SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource6, COARSE_DIRT), DIRT);
		SurfaceRules.RuleSource surfacerules$rulesource2 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);
		SurfaceRules.ConditionSource surfacerules$conditionsource12 = SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.DESERT, Biomes.BEACH, Biomes.SNOWY_BEACH, EndRegistry.SCORCHLAND.getFirst());
		SurfaceRules.ConditionSource isWasteland = SurfaceRules.isBiome(EndRegistry.WASTELANDS.getFirst());
		SurfaceRules.RuleSource surfacerules$rulesource3 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.STONY_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125D, 0.0125D), CALCITE), STONE)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.STONY_SHORE), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05D, 0.05D), surfacerules$rulesource2), STONE)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_HILLS), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE)), SurfaceRules.ifTrue(surfacerules$conditionsource12, surfacerules$rulesource1), SurfaceRules.ifTrue(isWasteland, wasteland), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE));
		SurfaceRules.RuleSource surfacerules$rulesource4 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D), POWDER_SNOW);
		SurfaceRules.RuleSource surfacerules$rulesource5 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D), POWDER_SNOW);
		SurfaceRules.RuleSource surfacerules$rulesource6 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource11, PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5D, 0.2D), PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625D, 0.025D), ICE), SNOW_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SNOWY_SLOPES), SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource11, STONE), surfacerules$rulesource4, SNOW_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), STONE), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(surfacerules$rulesource4, DIRT)), surfacerules$rulesource3, SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA), SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), surfacerules$rulesource2), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), DIRT), surfacerules$rulesource2)), DIRT);
		SurfaceRules.RuleSource surfacerules$rulesource7 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource11, PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0D, 0.2D), PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0D, 0.025D), ICE), SNOW_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SNOWY_SLOPES), SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource11, STONE), surfacerules$rulesource5, SNOW_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource11, STONE), SNOW_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(surfacerules$rulesource5, SNOW_BLOCK)), surfacerules$rulesource3, SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5D), COARSE_DIRT))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), surfacerules$rulesource2), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), surfacerules$rulesource), surfacerules$rulesource2)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), COARSE_DIRT), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95D), PODZOL))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.ICE_SPIKES), SNOW_BLOCK), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM), surfacerules$rulesource);
		SurfaceRules.ConditionSource surfacerules$conditionsource13 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
		SurfaceRules.ConditionSource surfacerules$conditionsource14 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818D, 0.1818D);
		SurfaceRules.ConditionSource surfacerules$conditionsource15 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);
		SurfaceRules.RuleSource surfacerules$rulesource8 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WOODED_BADLANDS), SurfaceRules.ifTrue(surfacerules$conditionsource, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource13, COARSE_DIRT), SurfaceRules.ifTrue(surfacerules$conditionsource14, COARSE_DIRT), SurfaceRules.ifTrue(surfacerules$conditionsource15, COARSE_DIRT), surfacerules$rulesource))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SWAMP), SurfaceRules.ifTrue(surfacerules$conditionsource4, SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource5), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)))))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource1, ORANGE_TERRACOTTA), SurfaceRules.ifTrue(surfacerules$conditionsource3, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource13, TERRACOTTA), SurfaceRules.ifTrue(surfacerules$conditionsource14, TERRACOTTA), SurfaceRules.ifTrue(surfacerules$conditionsource15, TERRACOTTA), SurfaceRules.bandlands())), SurfaceRules.ifTrue(surfacerules$conditionsource6, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE), RED_SAND)), SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource9), ORANGE_TERRACOTTA), SurfaceRules.ifTrue(surfacerules$conditionsource8, WHITE_TERRACOTTA), surfacerules$rulesource2)), SurfaceRules.ifTrue(surfacerules$conditionsource2, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource5, SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource3), ORANGE_TERRACOTTA)), SurfaceRules.bandlands())), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.ifTrue(surfacerules$conditionsource8, WHITE_TERRACOTTA)))), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(surfacerules$conditionsource6, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource10, SurfaceRules.ifTrue(surfacerules$conditionsource9, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource7, AIR), SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE), WATER))), surfacerules$rulesource7))), SurfaceRules.ifTrue(surfacerules$conditionsource8, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(surfacerules$conditionsource10, SurfaceRules.ifTrue(surfacerules$conditionsource9, WATER))), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, surfacerules$rulesource6), SurfaceRules.ifTrue(surfacerules$conditionsource12, SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR), SANDSTONE)))), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS), STONE), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN), surfacerules$rulesource1), surfacerules$rulesource2)));
		Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));

		SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), surfacerules$rulesource8);
		builder.add(surfacerules$rulesource9);
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
		return SurfaceRules.sequence(builder.build().toArray((rule) -> {
			return new SurfaceRules.RuleSource[rule];
		}));
	}

	private static SurfaceRules.ConditionSource surfaceNoiseAbove(double noise) {
		return SurfaceRules.noiseCondition(Noises.SURFACE, noise / 8.25D, Double.MAX_VALUE);
	}
}
