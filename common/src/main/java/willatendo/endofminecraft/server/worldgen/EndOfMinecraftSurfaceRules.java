package willatendo.endofminecraft.server.worldgen;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomes;

public class EndOfMinecraftSurfaceRules {
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource prehistoric() {
        SurfaceRules.ConditionSource belowWater = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource inWater = SurfaceRules.waterBlockCheck(0, 0);
        SurfaceRules.ConditionSource aroundWater = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.RuleSource basicGrass = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(inWater, COARSE_DIRT), DIRT});
        SurfaceRules.RuleSource replaceCeilingSandWithSandstone = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND});
        SurfaceRules.RuleSource replaceCeilingGravelWithStone = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL});
        SurfaceRules.ConditionSource isSandy = SurfaceRules.isBiome(new ResourceKey[]{EndOfMinecraftBiomes.SCORCHLAND});
        SurfaceRules.RuleSource stoneNoiseInStonyAreas = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(isSandy, replaceCeilingSandWithSandstone)});
        SurfaceRules.RuleSource surface1 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{stoneNoiseInStonyAreas, DIRT});
        SurfaceRules.RuleSource surface2 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{stoneNoiseInStonyAreas, basicGrass});
        SurfaceRules.RuleSource surface3 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(belowWater, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{surface2}))), SurfaceRules.ifTrue(aroundWater, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, surface1), SurfaceRules.ifTrue(isSandy, SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE))})), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{replaceCeilingSandWithSandstone, replaceCeilingGravelWithStone}))});
        ImmutableList.Builder<SurfaceRules.RuleSource> surfaceRuleSource = ImmutableList.builder();
        surfaceRuleSource.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        surfaceRuleSource.add(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), surface3));
        surfaceRuleSource.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
        return SurfaceRules.sequence((SurfaceRules.RuleSource[]) surfaceRuleSource.build().toArray((i) -> {
            return new SurfaceRules.RuleSource[i];
        }));
    }
}
