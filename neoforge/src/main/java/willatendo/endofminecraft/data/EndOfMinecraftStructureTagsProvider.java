package willatendo.endofminecraft.data;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.StructureTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructureTags;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructures;

import java.util.concurrent.CompletableFuture;

public class EndOfMinecraftStructureTagsProvider extends StructureTagsProvider {
    public EndOfMinecraftStructureTagsProvider(PackOutput packOutput, CompletableFuture<Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        this.tag(StructureTags.VILLAGE).add(EndOfMinecraftStructures.VILLAGE_POST_APOCALYPTIC);

        this.tag(EndOfMinecraftStructureTags.ANOMALY_DETECTOR_DETECTABLE).add(EndOfMinecraftStructures.ANOMALY_CAVE);
    }
}
