package willatendo.endofminecraft.server.structure;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.PillagerOutpostPools;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomeTags;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.List;

public class EndOfMinecraftStructureSets {
    public static final ResourceKey<StructureSet> ANOMALY_CAVE = register("anomaly_cave");
    public static final ResourceKey<StructureSet> VILLAGE_POST_APOCALYPTIC = register("village_post_apocalyptic");

    public static ResourceKey<StructureSet> register(String id) {
        return ResourceKey.create(Registries.STRUCTURE_SET, EndOfMinecraftUtils.resource(id));
    }

    public static void bootstrap(BootstapContext<StructureSet> bootstapContext) {
        HolderGetter<Biome> biomes = bootstapContext.lookup(Registries.BIOME);
        HolderGetter<Structure> structures = bootstapContext.lookup(Registries.STRUCTURE);
        bootstapContext.register(ANOMALY_CAVE, new StructureSet(List.of(new StructureSet.StructureSelectionEntry(structures.getOrThrow(EndOfMinecraftStructures.ANOMALY_CAVE), 1)), new ConcentricRingsStructurePlacement(64, 3, 3, biomes.getOrThrow(EndOfMinecraftBiomeTags.HAS_ANOMALY_CAVE))));
        bootstapContext.register(VILLAGE_POST_APOCALYPTIC, new StructureSet(List.of(new StructureSet.StructureSelectionEntry(structures.getOrThrow(EndOfMinecraftStructures.VILLAGE_POST_APOCALYPTIC), 1)), new RandomSpreadStructurePlacement(64, 8, RandomSpreadType.TRIANGULAR, 36784536)));
    }
}
