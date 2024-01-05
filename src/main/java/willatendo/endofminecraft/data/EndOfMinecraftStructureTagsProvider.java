package willatendo.endofminecraft.data;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.StructureTags;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructureTags;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructures;
import willatendo.simplelibrary.data.SimpleStructureTagsProvider;
import willatendo.simplelibrary.data.util.ExistingFileHelper;

public class EndOfMinecraftStructureTagsProvider extends SimpleStructureTagsProvider {
	public EndOfMinecraftStructureTagsProvider(FabricDataOutput fabricDataOutput, CompletableFuture<Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
		super(fabricDataOutput, provider, modId, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.tag(StructureTags.VILLAGE).add(EndOfMinecraftStructures.VILLAGE_POST_APOCALYPTIC);

		this.tag(EndOfMinecraftStructureTags.ANOMALY_DETECTOR_DETECTABLE).add(EndOfMinecraftStructures.ANOMALY_CAVE);
	}
}
