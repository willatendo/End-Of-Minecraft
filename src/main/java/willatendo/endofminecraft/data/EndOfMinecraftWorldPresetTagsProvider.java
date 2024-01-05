package willatendo.endofminecraft.data;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.WorldPresetTags;
import willatendo.endofminecraft.server.worldpreset.EndOfMinecraftWorldPresets;
import willatendo.simplelibrary.data.SimpleWorldPresetTagsProvider;
import willatendo.simplelibrary.data.util.ExistingFileHelper;

public class EndOfMinecraftWorldPresetTagsProvider extends SimpleWorldPresetTagsProvider {
	public EndOfMinecraftWorldPresetTagsProvider(FabricDataOutput fabricDataOutput, CompletableFuture<Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
		super(fabricDataOutput, provider, modId, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.tag(WorldPresetTags.NORMAL).add(EndOfMinecraftWorldPresets.END_OF_MINECRAFT);
	}
}
