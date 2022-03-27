package endofminecraft.data.client;

import static endofminecraft.EndOfMinecraftMod.ID;

import endofminecraft.server.EndRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EndItems extends ItemModelProvider {
	public EndItems(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (int i = 0; i < 7; i++) {
			ModelFile model = getExistingFile(this.mcLoc("item/generated"));
			this.getBuilder(EndRegistry.GEIGER_COUNTER.get().getRegistryName().getPath() + String.format("_%01d", i)).parent(model).texture("layer0", "item/" + EndRegistry.GEIGER_COUNTER.get().getRegistryName().getPath() + String.format("_%01d", i));
		}
	}
}
