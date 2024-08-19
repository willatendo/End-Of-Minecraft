package willatendo.endofminecraft.server.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.SimpleHolder;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.List;

public class EndOfMinecraftMobEffects {
    public static final SimpleRegistry<MobEffect> MOB_EFFECTS = SimpleRegistry.create(Registries.MOB_EFFECT, EndOfMinecraftUtils.ID);

    public static final SimpleHolder<MobEffect> IRRADIATED = MOB_EFFECTS.register("irradiated", () -> new IrradiatedEffect(MobEffectCategory.HARMFUL, 0x374b37));

    public static void init(List<SimpleRegistry<?>> simpleRegistries) {
        simpleRegistries.add(MOB_EFFECTS);
    }
}
