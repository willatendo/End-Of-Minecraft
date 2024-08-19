package willatendo.endofminecraft.server.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class AnomalyCavePools {
    public static final ResourceKey<StructureTemplatePool> START = EndOfMinecraftPools.create("anomaly_cave/entrance");

    public static void bootstrap(BootstapContext<StructureTemplatePool> bootstapContext) {
        HolderGetter<StructureProcessorList> processorLists = bootstapContext.lookup(Registries.PROCESSOR_LIST);
        HolderGetter<StructureTemplatePool> templatePools = bootstapContext.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> emptyPool = templatePools.getOrThrow(Pools.EMPTY);
        bootstapContext.register(START, new StructureTemplatePool(emptyPool, ImmutableList.of(Pair.of(EndOfMinecraftPools.legacy("anomaly_cave/entrance"), 1)), StructureTemplatePool.Projection.RIGID));
        EndOfMinecraftPools.register(bootstapContext, "anomaly_cave/final_corridor", new StructureTemplatePool(emptyPool, ImmutableList.of(Pair.of(EndOfMinecraftPools.legacy("anomaly_cave/final_corridor"), 1)), StructureTemplatePool.Projection.RIGID));
        EndOfMinecraftPools.register(bootstapContext, "anomaly_cave/portal_room", new StructureTemplatePool(emptyPool, ImmutableList.of(Pair.of(EndOfMinecraftPools.legacy("anomaly_cave/portal_room"), 1)), StructureTemplatePool.Projection.RIGID));
    }
}
