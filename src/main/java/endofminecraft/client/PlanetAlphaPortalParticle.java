package endofminecraft.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

public class PlanetAlphaPortalParticle extends TextureSheetParticle {
	private final double xStart;
	private final double yStart;
	private final double zStart;

	protected PlanetAlphaPortalParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
		super(level, x, y, z);
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xStart = this.x;
		this.yStart = this.y;
		this.zStart = this.z;
		this.quadSize = 0.1F * (this.random.nextFloat() * 0.2F + 0.5F);
		float f = this.random.nextFloat() * 0.6F + 0.4F;
		this.rCol = f * 0.9F;
		this.gCol = f * 0.3F;
		this.bCol = f;
		this.lifetime = (int) (Math.random() * 10.0D) + 40;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double p_107560_, double p_107561_, double p_107562_) {
		this.setBoundingBox(this.getBoundingBox().move(p_107560_, p_107561_, p_107562_));
		this.setLocationFromBoundingbox();
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			float f = (float) this.age / (float) this.lifetime;
			float f1 = -f + f * f * 2.0F;
			float f2 = 1.0F - f1;
			this.x = this.xStart + this.xd * (double) f2;
			this.y = this.yStart + this.yd * (double) f2 + (double) (1.0F - f);
			this.z = this.zStart + this.zd * (double) f2;
		}
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public Provider(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType particle, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
			var planetAlphaPortalParticle = new PlanetAlphaPortalParticle(level, x, y, z, xd, yd, zd);
			planetAlphaPortalParticle.pickSprite(this.sprite);
			return planetAlphaPortalParticle;
		}
	}
}
