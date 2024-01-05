package willatendo.endofminecraft.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import willatendo.endofminecraft.server.EndOfMinecraftCreativeModeTabs;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.simplelibrary.data.SimpleLanguageProvider;

public class EndOfMinecraftLanguageProvider extends SimpleLanguageProvider {
	public EndOfMinecraftLanguageProvider(FabricDataOutput fabricDataOutput, String modId, String locale) {
		super(fabricDataOutput, modId, locale);
	}

	@Override
	protected void addTranslations() {
		this.add("biome.endofminecraft.dead_tree_grove", "Dead Tree Grove");
		this.add("biome.endofminecraft.scorchland", "Scorchland");
		this.add("biome.endofminecraft.wasteland", "Wasteland");

		this.add(EndOfMinecraftBlocks.ANOMALY_STONE.get());
		this.add(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());

		this.add("generator.endofminecraft.end_of_minecraft", "End of Minecraft");

		this.add(EndOfMinecraftItems.ANOMALY_DETECTOR.get());

		this.add(EndOfMinecraftCreativeModeTabs.END_OF_MINECRAFT.get(), "End of Minecraft");
	}
}
