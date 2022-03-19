package endofminecraft;

import endofminecraft.client.EndItemProperties;
import endofminecraft.server.EndRegistry;
import endofminecraft.server.radiation.RadiationCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tyrannotitanlib.core.content.UtilitiesRegistry;

@Mod(EndOfMinecraftMod.ID)
public class EndOfMinecraftMod {
	public static final String ID = "endofminecraft";
	public static final UtilitiesRegistry UTILS = new UtilitiesRegistry(ID);

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
		 * Structures: Irradiated Village, Anomaly Cave Bunker
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

		EndRegistry.init(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commandSetup(RegisterCommandsEvent event) {
		RadiationCommand.register(event.getDispatcher());
	}

	private void clientSetup(FMLClientSetupEvent event) {
		EndItemProperties.registerItemProperties();
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			EndRegistry.register();
		});
	}
}
