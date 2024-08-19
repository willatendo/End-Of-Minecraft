package willatendo.endofminecraft.server.worldgen;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.function.Function;

public class EndOfMinecraftPools {
    public static ResourceKey<StructureTemplatePool> create(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, EndOfMinecraftUtils.resource(name));
    }

    public static void register(BootstapContext<StructureTemplatePool> bootstapContext, String name, StructureTemplatePool structureTemplatePool) {
        bootstapContext.register(EndOfMinecraftPools.create(name), structureTemplatePool);
    }

    public static Function<StructureTemplatePool.Projection, LegacySinglePoolElement> legacy(String name) {
        return projection -> new LegacySinglePoolElement(Either.left(EndOfMinecraftUtils.resource(name)), StructurePoolElement.EMPTY, projection);
    }


    public static Function<StructureTemplatePool.Projection, LegacySinglePoolElement> legacy(String name, Holder<StructureProcessorList> processorList) {
        return projection -> new LegacySinglePoolElement(Either.left(EndOfMinecraftUtils.resource(name)), processorList, projection);
    }

    public static void bootstrap(BootstapContext<StructureTemplatePool> bootstapContext) {
        AnomalyCavePools.bootstrap(bootstapContext);
        PostApocalypticVillagePools.bootstrap(bootstapContext);
    }
}
