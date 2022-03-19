package endofminecraft.data.client;

import static endofminecraft.EndOfMinecraftMod.UTILS;

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

		for (int i = 0; i < 7; i++) {
			ModelFile model = getExistingFile(this.mcLoc("item/generated"));
			this.getBuilder(EndRegistry.GEIGER_COUNTER.get().getRegistryName().getPath() + String.format("_%01d", i)).parent(model).texture("layer0", "item/" + EndRegistry.GEIGER_COUNTER.get().getRegistryName().getPath() + String.format("_%01d", i));
		}
		this.geigerCounter(EndRegistry.GEIGER_COUNTER.get());
	}

	public void generated(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/generated"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + item.getRegistryName().getPath());
	}

	public void geigerCounter(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/generated"));
		ModelFile model0 = getExistingFile(this.modLoc("item/geiger_counter_0"));
		ModelFile model1 = getExistingFile(this.modLoc("item/geiger_counter_1"));
		ModelFile model2 = getExistingFile(this.modLoc("item/geiger_counter_2"));
		ModelFile model3 = getExistingFile(this.modLoc("item/geiger_counter_3"));
		ModelFile model4 = getExistingFile(this.modLoc("item/geiger_counter_4"));
		ModelFile model5 = getExistingFile(this.modLoc("item/geiger_counter_5"));
		ModelFile model6 = getExistingFile(this.modLoc("item/geiger_counter_6"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + item.getRegistryName().getPath()).override().predicate(UTILS.resource("radiation"), 0.0F).model(model0).end().override().predicate(UTILS.resource("radiation"), 1.0F).model(model1).end().override().predicate(UTILS.resource("radiation"), 2.0F).model(model2).end().override().predicate(UTILS.resource("radiation"), 3.0F).model(model3).end().override().predicate(UTILS.resource("radiation"), 4.0F).model(model4).end().override().predicate(UTILS.resource("radiation"), 5.0F).model(model5).end().override().predicate(UTILS.resource("radiation"), 6.0F).model(model6).end();
	}
}
