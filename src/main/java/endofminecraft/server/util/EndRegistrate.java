package endofminecraft.server.util;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import endofminecraft.server.EndRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EndRegistrate extends AbstractRegistrate<EndRegistrate> {
	protected EndRegistrate(String modid) {
		super(modid);

		this.addDataGenerator(ProviderType.LANG, provider -> provider.add(EndRegistry.END_TAB, "End of Minecraft"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add(EndRegistry.IRRADIATED.get(), "Irradiated"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("command.endofminecraft.radiation.success", "Successfully set the radiation level of the world to %s"));
	}

	public static NonNullSupplier<EndRegistrate> lazy(String modid) {
		return NonNullSupplier.of(() -> new EndRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}
}
