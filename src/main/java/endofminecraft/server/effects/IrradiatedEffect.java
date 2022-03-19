package endofminecraft.server.effects;

import java.util.List;

import endofminecraft.server.EndRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class IrradiatedEffect extends MobEffect {
	public IrradiatedEffect() {
		super(MobEffectCategory.HARMFUL, 0x374b37);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		return List.of();
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplification) {
		float damage = amplification * 0.5F;
		if (entity.getActiveEffects().contains(MobEffects.REGENERATION)) {
			damage = amplification * 1.0F;
		}
		entity.hurt(EndRegistry.IRRADIATION, damage);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplification) {
		int i = 40 >> amplification;
		if (i > 0) {
			return duration % i == 0;
		} else {
			return true;
		}
	}
}
