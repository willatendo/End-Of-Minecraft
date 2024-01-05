package willatendo.endofminecraft.data;

import java.util.Locale;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.simplelibrary.data.SimpleItemModelProvider;
import willatendo.simplelibrary.data.model.ModelFile;
import willatendo.simplelibrary.data.util.ExistingFileHelper;

public class EndOfMinecraftItemModelProvider extends SimpleItemModelProvider {
	public EndOfMinecraftItemModelProvider(FabricDataOutput fabricDataOutput, String modId, ExistingFileHelper existingFileHelper) {
		super(fabricDataOutput, modId, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.basicBlock(EndOfMinecraftBlocks.ANOMALY_STONE.get());
		this.basicBlock(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());

		for (int i = 0; i < 32; i++) {
			if (i == 16)
				continue;
			ModelFile model = this.getExistingFile(this.mcLoc("item/generated"));
			this.getBuilder(EndOfMinecraftItems.ANOMALY_DETECTOR.getId() + String.format(Locale.ROOT, "_%02d", i)).parent(model).texture("layer0", "item/" + EndOfMinecraftItems.ANOMALY_DETECTOR.getId().getPath() + String.format(Locale.ROOT, "_%02d", i));
		}
	}
}
