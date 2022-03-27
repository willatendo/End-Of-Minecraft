package endofminecraft;

import com.tterrag.registrate.util.nullness.NonNullSupplier;

import endofminecraft.client.EndItemProperties;
import endofminecraft.client.PlanetAlphaEffects;
import endofminecraft.data.client.EndItems;
import endofminecraft.server.EndRegistry;
import endofminecraft.server.radiation.RadiationCommand;
import endofminecraft.server.util.EndRegistrate;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import tyrannotitanlib.core.content.UtilitiesRegistry;

@Mod(EndOfMinecraftMod.ID)
public class EndOfMinecraftMod {
	public static final String ID = "endofminecraft";
	public static final UtilitiesRegistry UTILS = new UtilitiesRegistry(ID);
	public static final NonNullSupplier<EndRegistrate> CENTRAL_REGISTRATE = EndRegistrate.lazy(ID);

	public EndOfMinecraftMod() {
		/*
		 * End of Minecraft:
		 * 
		 * A dead world with a few dead biomes. Set after a nuclear apocalypse, the
		 * player wakes up in a underground bunker with a chest with a single sapling
		 * and seed type. It'll either be an oak sapling and carrot, jungle sapling and
		 * wheat seed, birch sapling and beetroot seeds, or a spruce sapling and
		 * potatoes.
		 * 
		 * When it rains, the player can become irradiated (slowly kills the player and
		 * prevents regeneration of heath)
		 * 
		 * Entities: husks, irradiated villagers, and red, blue, and green beetles.
		 * 
		 * End Goal: Reach Planet Alpha
		 * 
		 * Structures: Irradiated Village, Anomaly Cave, Bunker
		 * 
		 * Items: Radiation Suit, Geiger Counter
		 * 
		 * Blocks: Anomaly Stone, Planet Alpha Portal,
		 */

		var bus = FMLJavaModLoadingContext.get().getModEventBus();
		var forge = MinecraftForge.EVENT_BUS;

		forge.addListener(this::commandSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

		EndRegistry.init(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commandSetup(RegisterCommandsEvent event) {
		RadiationCommand.register(event.getDispatcher());

		EndRegistry.END_TAB.setItemIcon(EndRegistry.GEIGER_COUNTER.asStack());
	}

	private void clientSetup(FMLClientSetupEvent event) {
		EndItemProperties.registerItemProperties();

		DimensionSpecialEffects planetAlphaEffects = new PlanetAlphaEffects();
		DimensionSpecialEffects.EFFECTS.put(UTILS.mod("planet_alpha_render"), planetAlphaEffects);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			EndRegistry.register();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		var dataGenerator = event.getGenerator();
		var existingFileHelper = event.getExistingFileHelper();
		dataGenerator.addProvider(new EndItems(dataGenerator, existingFileHelper));
	}
}
