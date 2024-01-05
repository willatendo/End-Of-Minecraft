package willatendo.endofminecraft.server.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.RegistryHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

public class EndOfMinecraftMobEffects {
	public static final SimpleRegistry<MobEffect> MOB_EFFECTS = SimpleRegistry.create(BuiltInRegistries.MOB_EFFECT, EndOfMinecraftUtils.ID);

	public static final RegistryHolder<MobEffect> IRRADIATED = MOB_EFFECTS.register("irradiated", () -> new IrradiatedEffect(MobEffectCategory.HARMFUL, 0x374b37));

	public static void init() {
	}
}
