package endofminecraft.content.server.init;

import endofminecraft.content.ModRegistry;
import endofminecraft.library.effect.IrradiatedEffect;
import net.minecraft.world.effect.MobEffect;

public class EffectInit {
	public static final MobEffect IRRADIATED = ModRegistry.effect("irradiated", new IrradiatedEffect());

	public static void init() {
	}
}
