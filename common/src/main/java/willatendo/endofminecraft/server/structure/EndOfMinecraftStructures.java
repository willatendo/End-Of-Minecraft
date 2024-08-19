package willatendo.endofminecraft.server.structure;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.PillagerOutpostPools;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomeTags;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.endofminecraft.server.worldgen.AnomalyCavePools;

import java.util.Map;

public class EndOfMinecraftStructures {
    public static final ResourceKey<Structure> ANOMALY_CAVE = register("anomaly_cave");
    public static final ResourceKey<Structure> VILLAGE_POST_APOCALYPTIC = register("village_post_apocalyptic");

    public static ResourceKey<Structure> register(String name) {
        return ResourceKey.create(Registries.STRUCTURE, EndOfMinecraftUtils.resource(name));
    }

    public static void bootstrap(BootstapContext<Structure> bootstapContext) {
        HolderGetter<Biome> biomes = bootstapContext.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePools = bootstapContext.lookup(Registries.TEMPLATE_POOL);
        bootstapContext.register(ANOMALY_CAVE, new JigsawStructure(Structures.structure(biomes.getOrThrow(EndOfMinecraftBiomeTags.HAS_ANOMALY_CAVE), TerrainAdjustment.BEARD_THIN), templatePools.getOrThrow(AnomalyCavePools.START), 6, ConstantHeight.of(VerticalAnchor.absolute(-20)), true, Heightmap.Types.WORLD_SURFACE_WG));
        bootstapContext.register(VILLAGE_POST_APOCALYPTIC, new JigsawStructure(Structures.structure(biomes.getOrThrow(EndOfMinecraftBiomeTags.HAS_VILLAGE_POST_APOCALYPTIC), TerrainAdjustment.BEARD_THIN), templatePools.getOrThrow(PillagerOutpostPools.START), 6, ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG));
    }
}
