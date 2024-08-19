package willatendo.endofminecraft.client.properties;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class AnomalyDetectorItemPropertyFunction implements ClampedItemPropertyFunction {
	private final CompassWobble wobble = new CompassWobble();
	private final CompassWobble wobbleRandom = new CompassWobble();
	public final CompassTarget compassTarget;

	public AnomalyDetectorItemPropertyFunction(CompassTarget compassTarget) {
		this.compassTarget = compassTarget;
	}

	@Override
	public float unclampedCall(ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) {
		Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
		if (entity == null) {
			return 0.0f;
		}
		if ((clientLevel = this.tryFetchLevelIfMissing(entity, clientLevel)) == null) {
			return 0.0f;
		}
		return this.getCompassRotation(itemStack, clientLevel, i, entity);
	}

	private float getCompassRotation(ItemStack itemStack, ClientLevel clientLevel, int i, Entity entity) {
		GlobalPos globalPos = this.compassTarget.getPos(clientLevel, itemStack, entity);
		long gameTime = clientLevel.getGameTime();
		if (!this.isValidCompassTargetPos(entity, globalPos)) {
			return this.getRandomlySpinningRotation(i, gameTime);
		}
		return this.getRotationTowardsCompassTarget(entity, gameTime, globalPos.pos());
	}

	private float getRandomlySpinningRotation(int i, long l) {
		if (this.wobbleRandom.shouldUpdate(l)) {
			this.wobbleRandom.update(l, Math.random());
		}
		double d = this.wobbleRandom.rotation + (double) ((float) this.hash(i) / 2.14748365E9f);
		return Mth.positiveModulo((float) d, 1.0f);
	}

	private float getRotationTowardsCompassTarget(Entity entity, long l, BlockPos blockPos) {
		double f;
		double d = this.getAngleFromEntityToPos(entity, blockPos);
		double e = this.getWrappedVisualRotationY(entity);
		if (entity instanceof Player && ((Player) entity).isLocalPlayer()) {
			if (this.wobble.shouldUpdate(l)) {
				this.wobble.update(l, 0.5 - (e - 0.25));
			}
			f = d + this.wobble.rotation;
		} else {
			f = 0.5 - (e - 0.25 - d);
		}
		return Mth.positiveModulo((float) f, 1.0f);
	}

	@Nullable
	private ClientLevel tryFetchLevelIfMissing(Entity entity, ClientLevel clientLevel) {
		if (clientLevel == null && entity.level() instanceof ClientLevel) {
			return (ClientLevel) entity.level();
		}
		return clientLevel;
	}

	private boolean isValidCompassTargetPos(Entity entity, GlobalPos globalPos) {
		if (globalPos.pos() == null) {
			return false;
		}
		return globalPos != null && globalPos.dimension() == entity.level().dimension() && !(globalPos.pos().distToCenterSqr(entity.position()) < (double) 1.0E-5f);
	}

	private double getAngleFromEntityToPos(Entity entity, BlockPos blockPos) {
		Vec3 vec3 = Vec3.atCenterOf(blockPos);
		return Math.atan2(vec3.z() - entity.getZ(), vec3.x() - entity.getX()) / 6.2831854820251465;
	}

	private double getWrappedVisualRotationY(Entity entity) {
		return Mth.positiveModulo((double) (entity.getVisualRotationYInDegrees() / 360.0f), 1.0);
	}

	private int hash(int i) {
		return i * 1327217883;
	}

	public static class CompassWobble {
		double rotation;
		private double deltaRotation;
		private long lastUpdateTick;

		public CompassWobble() {
		}

		public boolean shouldUpdate(long lastUpdateTick) {
			return this.lastUpdateTick != lastUpdateTick;
		}

		public void update(long lastUpdateTick, double tick) {
			this.lastUpdateTick = lastUpdateTick;
			double rotation = tick - this.rotation;
			rotation = Mth.positiveModulo(rotation + 0.5, 1.0) - 0.5;
			this.deltaRotation += rotation * 0.1;
			this.deltaRotation *= 0.8;
			this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0);
		}
	}

	public static interface CompassTarget {
		@Nullable
		public GlobalPos getPos(ClientLevel clientLevel, ItemStack itemStack, Entity entity);
	}

}
