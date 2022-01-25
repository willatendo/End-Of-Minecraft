package endofminecraft.content;

import endofminecraft.content.client.ClientSetup;
import endofminecraft.library.dimension.PlanetAlphaDimensionRenderInfo;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModUtils.ID)
public class EndOfMinecraft {
	public EndOfMinecraft() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

		ModRegistry.registry();

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> bus.addListener(ClientSetup::blockEntitityRegisters));
	}

	private void clientSetup(FMLClientSetupEvent event) {
		ClientSetup.layers();

		DimensionSpecialEffects planet_alpha_render = new PlanetAlphaDimensionRenderInfo();
		DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(ModUtils.ID, "planet_alpha_render"), planet_alpha_render);
	}
}
