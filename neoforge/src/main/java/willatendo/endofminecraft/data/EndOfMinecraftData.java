package willatendo.endofminecraft.data;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import willatendo.endofminecraft.data.loot.EndOfMinecraftBlockLootSubProvider;
import willatendo.endofminecraft.data.loot.EndOfMinecraftChestLootSubProvider;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = EndOfMinecraftUtils.ID)
public class EndOfMinecraftData {
    @SubscribeEvent
    public static void gatherDataEvent(GatherDataEvent gatherDataEvent) {
        DataGenerator dataGenerator = gatherDataEvent.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = gatherDataEvent.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> registries = gatherDataEvent.getLookupProvider();

        dataGenerator.addProvider(gatherDataEvent.includeClient(), new EndOfMinecraftItemModelProvider(packOutput, EndOfMinecraftUtils.ID, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeClient(), new EndOfMinecraftBlockStateProvider(packOutput, EndOfMinecraftUtils.ID, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeClient(), new EndOfMinecraftLanguageProvider(packOutput, EndOfMinecraftUtils.ID, "en_us"));

        EndOfMinecraftBuiltinProvider endOfMinecraftBuiltinProvider = new EndOfMinecraftBuiltinProvider(packOutput, registries, EndOfMinecraftUtils.ID);
        dataGenerator.addProvider(gatherDataEvent.includeServer(), endOfMinecraftBuiltinProvider);
        dataGenerator.addProvider(gatherDataEvent.includeServer(), new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(EndOfMinecraftBlockLootSubProvider::new, LootContextParamSets.BLOCK), new LootTableProvider.SubProviderEntry(EndOfMinecraftChestLootSubProvider::new, LootContextParamSets.CHEST))) {
            @Override
            protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
            }
        });
        CompletableFuture<HolderLookup.Provider> modRegistries = endOfMinecraftBuiltinProvider.getRegistryProvider();
        dataGenerator.addProvider(gatherDataEvent.includeServer(), new EndOfMinecraftStructureTagsProvider(packOutput, modRegistries, EndOfMinecraftUtils.ID, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeServer(), new EndOfMinecraftBiomeTagsProvider(packOutput, modRegistries, EndOfMinecraftUtils.ID, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeServer(), new EndOfMinecraftWorldPresetTagsProvider(packOutput, modRegistries, EndOfMinecraftUtils.ID, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeServer(), new PackMetadataGenerator(packOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(EndOfMinecraftUtils.translation("resourcePack", "description"), DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES), Optional.empty())));
    }
}
