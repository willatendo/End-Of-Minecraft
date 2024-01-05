package willatendo.endofminecraft;

import net.fabricmc.api.ModInitializer;
import willatendo.endofminecraft.client.particle.EndOfMinecraftParticleTypes;
import willatendo.endofminecraft.client.properties.EndOfMinecraftItemProperties;
import willatendo.endofminecraft.server.EndOfMinecraftCreativeModeTabs;
import willatendo.endofminecraft.server.biome.EndOfMinecraftMultiNoiseBiomeSourceParameterListPresets;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.block.entity.EndOfMinecraftBlockEntities;
import willatendo.endofminecraft.server.entity.EndOfMinecraftVillagerTypes;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;

public class EndOfMinecraftMod implements ModInitializer {
	@Override
	public void onInitialize() {
		EndOfMinecraftParticleTypes.init();
		EndOfMinecraftBlockEntities.init();
		EndOfMinecraftBlocks.init();
		EndOfMinecraftItems.init();
		EndOfMinecraftCreativeModeTabs.init();
		EndOfMinecraftVillagerTypes.init();

		EndOfMinecraftMultiNoiseBiomeSourceParameterListPresets.init();

		EndOfMinecraftItemProperties.init();
	}
}
