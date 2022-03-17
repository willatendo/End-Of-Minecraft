package endofminecraft.data;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.data.client.EndItemProvider;
import endofminecraft.data.client.EndLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = EndOfMinecraftMod.ID)
public class EndDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		dataGenerator.addProvider(new EndItemProvider(dataGenerator, existingFileHelper));
		dataGenerator.addProvider(new EndLangProvider(dataGenerator));
	}
}
