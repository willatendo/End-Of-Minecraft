package willatendo.endofminecraft.data;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomeTags;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomes;

import java.util.concurrent.CompletableFuture;

public class EndOfMinecraftBiomeTagsProvider extends BiomeTagsProvider {
    public EndOfMinecraftBiomeTagsProvider(PackOutput packOutput, CompletableFuture<Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        this.tag(BiomeTags.HAS_DESERT_PYRAMID).add(EndOfMinecraftBiomes.SCORCHLAND);
        this.tag(BiomeTags.HAS_MINESHAFT).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE).add(EndOfMinecraftBiomes.SCORCHLAND).add(EndOfMinecraftBiomes.WASTELAND);
        this.tag(BiomeTags.HAS_RUINED_PORTAL_DESERT).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE).add(EndOfMinecraftBiomes.SCORCHLAND).add(EndOfMinecraftBiomes.WASTELAND);
        this.tag(BiomeTags.HAS_SHIPWRECK_BEACHED).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE).add(EndOfMinecraftBiomes.SCORCHLAND).add(EndOfMinecraftBiomes.WASTELAND);
        this.tag(BiomeTags.HAS_STRONGHOLD).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE).add(EndOfMinecraftBiomes.SCORCHLAND).add(EndOfMinecraftBiomes.WASTELAND);
        this.tag(BiomeTags.HAS_TRAIL_RUINS).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE).add(EndOfMinecraftBiomes.SCORCHLAND).add(EndOfMinecraftBiomes.WASTELAND);

        this.tag(EndOfMinecraftBiomeTags.HAS_ANOMALY_CAVE).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE, EndOfMinecraftBiomes.WASTELAND);
        this.tag(EndOfMinecraftBiomeTags.HAS_VILLAGE_POST_APOCALYPTIC).add(EndOfMinecraftBiomes.DEAD_TREE_GROVE, EndOfMinecraftBiomes.WASTELAND);
    }
}
