package endofminecraft.data.client;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.EndRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EndItemProvider extends ItemModelProvider {
	public EndItemProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, EndOfMinecraftMod.ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.generated(EndRegistry.RADIATION_SUIT_BOOTS.get());
		this.generated(EndRegistry.RADIATION_SUIT_CHESTPLATE.get());
		this.generated(EndRegistry.RADIATION_SUIT_HELMET.get());
		this.generated(EndRegistry.RADIATION_SUIT_LEGGINGS.get());
	}

	public void generated(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/generated"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + item.getRegistryName().getPath());
	}
}
