package willatendo.endofminecraft.server.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import willatendo.endofminecraft.server.entity.EndOfMinecraftDamageTypes;

public class IrradiatedEffect extends MobEffect {
	public IrradiatedEffect(MobEffectCategory mobEffectCategory, int i) {
		super(mobEffectCategory, i);
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplification) {
		livingEntity.hurt(livingEntity.damageSources().source(EndOfMinecraftDamageTypes.IRRADIATION), 2.0F);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplification) {
		int i = 40 >> amplification;
		if (i > 0) {
			return duration % i == 0;
		} else {
			return true;
		}
	}
}
