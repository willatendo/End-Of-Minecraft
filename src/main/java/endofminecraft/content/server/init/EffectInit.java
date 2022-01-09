package endofminecraft.content.server.init;

import endofminecraft.library.effect.IrradiatedEffect;
import endofminecraft.library.util.ModRegistry;
import net.minecraft.potion.Effect;

public class EffectInit {
	public static final Effect IRRADIATED = ModRegistry.effect("irradiated", new IrradiatedEffect());

	public static void init() {
	}
}
