package willatendo.endofminecraft.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;

public class PlanetAlphaPortalParticle extends TextureSheetParticle {
	private final double xStart;
	private final double yStart;
	private final double zStart;

	public PlanetAlphaPortalParticle(ClientLevel clientLevel, double x, double y, double z, double xd, double yd, double zd) {
		super(clientLevel, x, y, z);
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xStart = this.x;
		this.yStart = this.y;
		this.zStart = this.z;
		this.quadSize = 0.1f * (this.random.nextFloat() * 0.2f + 0.5f);
//		float j = this.random.nextFloat() * 0.6f + 0.4f;
//		this.rCol = j * 0.9f;
//		this.gCol = j * 0.3f;
//		this.bCol = j;
		this.lifetime = (int) (Math.random() * 10.0) + 40;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().move(x, y, z));
		this.setLocationFromBoundingbox();
	}

	@Override
	public float getQuadSize(float size) {
		float ticks = ((float) this.age + size) / (float) this.lifetime;
		ticks = 1.0f - ticks;
		ticks *= ticks;
		ticks = 1.0f - ticks;
		return this.quadSize * ticks;
	}

	@Override
	public int getLightColor(float size) {
		BlockPos blockPos = BlockPos.containing(this.x, this.y, this.z);
		if (this.level.hasChunkAt(blockPos)) {
			return LevelRenderer.getLightColor(this.level, blockPos);
		}
		return 0;
	}

	@Override
	public void tick() {
		float ticks;
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
			return;
		}
		float y = ticks = (float) this.age / (float) this.lifetime;
		ticks = -ticks + ticks * ticks * 2.0f;
		ticks = 1.0f - ticks;
		this.x = this.xStart + this.xd * (double) ticks;
		this.y = this.yStart + this.yd * (double) ticks + (double) (1.0f - y);
		this.z = this.zStart + this.zd * (double) ticks;
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final FabricSpriteProvider fabricSpriteProvider;

		public Provider(FabricSpriteProvider fabricSpriteProvider) {
			this.fabricSpriteProvider = fabricSpriteProvider;
		}

		@Override
		public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
			PlanetAlphaPortalParticle portalParticle = new PlanetAlphaPortalParticle(clientLevel, d, e, f, g, h, i);
			portalParticle.pickSprite(this.fabricSpriteProvider);
			return portalParticle;
		}
	}
}
