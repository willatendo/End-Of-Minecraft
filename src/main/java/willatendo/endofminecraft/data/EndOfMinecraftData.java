package willatendo.endofminecraft.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import willatendo.endofminecraft.data.loot.EndOfMinecraftBlockLootTableProvider;
import willatendo.endofminecraft.data.loot.EndOfMinecraftChestLootTableProvider;
import willatendo.simplelibrary.data.DataHandler;

public class EndOfMinecraftData implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		DataHandler dataHandler = new DataHandler(fabricDataGenerator);
		dataHandler.addProvider(EndOfMinecraftBlockStateProvider::new);
		dataHandler.addProvider(EndOfMinecraftItemModelProvider::new);
		dataHandler.addLanguageProvider((fabricDataOutput, modId) -> new EndOfMinecraftLanguageProvider(fabricDataOutput, modId, "en_us"));

		dataHandler.addProvider(EndOfMinecraftDimensionProvider::new);
		dataHandler.addProvider((fabricDataOutput, modId) -> new EndOfMinecraftBlockLootTableProvider(fabricDataOutput));
		dataHandler.addProvider((fabricDataOutput, modId) -> new EndOfMinecraftChestLootTableProvider(fabricDataOutput));
		dataHandler.addProvider(EndOfMinecraftStructureTagsProvider::new);
		dataHandler.addProvider(EndOfMinecraftBiomeTagsProvider::new);
		dataHandler.addProvider(EndOfMinecraftWorldPresetTagsProvider::new);
	}
}
