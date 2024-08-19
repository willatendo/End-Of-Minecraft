package willatendo.endofminecraft.server.worldgen;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftProcessorList {
    public static final ResourceKey<StructureProcessorList> FARM_POST_APOCALYPTIC = register("farm_post_apocalyptic");

    public static ResourceKey<StructureProcessorList> register(String string) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, EndOfMinecraftUtils.resource(string));
    }

    public static void bootstrap(BootstapContext<StructureProcessorList> bootstapContext) {
        bootstapContext.register(FARM_POST_APOCALYPTIC, new StructureProcessorList(ImmutableList.of(new RuleProcessor(ImmutableList.of(new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.3F), AlwaysTrueTest.INSTANCE, Blocks.CARROTS.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.2F), AlwaysTrueTest.INSTANCE, Blocks.POTATOES.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.BEETROOTS.defaultBlockState()))))));
    }
}
