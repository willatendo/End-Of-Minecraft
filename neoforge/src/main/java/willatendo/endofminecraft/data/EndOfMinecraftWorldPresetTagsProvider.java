package willatendo.endofminecraft.data;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.WorldPresetTagsProvider;
import net.minecraft.tags.WorldPresetTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import willatendo.endofminecraft.server.worldgen.EndOfMinecraftWorldPresets;

import java.util.concurrent.CompletableFuture;

public class EndOfMinecraftWorldPresetTagsProvider extends WorldPresetTagsProvider {
    public EndOfMinecraftWorldPresetTagsProvider(PackOutput packOutput, CompletableFuture<Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        this.tag(WorldPresetTags.NORMAL).add(EndOfMinecraftWorldPresets.END_OF_MINECRAFT);
    }
}
