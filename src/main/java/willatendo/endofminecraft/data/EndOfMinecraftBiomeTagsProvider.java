package willatendo.endofminecraft.data;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BiomeTags;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomeTags;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomes;
import willatendo.simplelibrary.data.SimpleBiomeTagsProvider;
import willatendo.simplelibrary.data.util.ExistingFileHelper;

public class EndOfMinecraftBiomeTagsProvider extends SimpleBiomeTagsProvider {
	public EndOfMinecraftBiomeTagsProvider(FabricDataOutput fabricDataOutput, CompletableFuture<Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
		super(fabricDataOutput, provider, modId, existingFileHelper);
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
