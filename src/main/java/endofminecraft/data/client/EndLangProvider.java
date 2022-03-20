package endofminecraft.data.client;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.EndRegistry;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.common.world.ForgeWorldPreset;

public class EndLangProvider extends LanguageProvider {
	public EndLangProvider(DataGenerator gen) {
		super(gen, EndOfMinecraftMod.ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(EndRegistry.GEIGER_COUNTER.get(), "Geiger Counter");
		this.add(EndRegistry.RADIATION_SUIT_BOOTS.get(), "Radiation Suit Boots");
		this.add(EndRegistry.RADIATION_SUIT_CHESTPLATE.get(), "Radiation Suit Chestplate");
		this.add(EndRegistry.RADIATION_SUIT_HELMET.get(), "Radiation Suit Helmet");
		this.add(EndRegistry.RADIATION_SUIT_LEGGINGS.get(), "Radiation Suit Leggings");
		this.add(EndRegistry.SCORCHLAND.getSecond().get(), "Scorchland");
		this.add(EndRegistry.WASTELANDS.getSecond().get(), "Wastelands");
		this.add(EndRegistry.END_OF_THE_WORLD.get(), "End of Minecraft");
		this.add(EndRegistry.IRRADIATED.get(), "Irradiated");
		this.add("command.endofminecraft.radiation.success", "Successfully set the radiation level of the world to %s");
	}

	public void add(MobEffect mobEffect, String name) {
		this.add(Util.makeDescriptionId("effect", mobEffect.getRegistryName()), name);
	}

	public void add(Biome biome, String name) {
		this.add(Util.makeDescriptionId("biome", biome.getRegistryName()), name);
	}

	public void add(ForgeWorldPreset preset, String name) {
		this.add(preset.getTranslationKey(), name);
	}
}
