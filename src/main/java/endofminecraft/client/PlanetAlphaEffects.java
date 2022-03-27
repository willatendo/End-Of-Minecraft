package endofminecraft.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

public class PlanetAlphaEffects extends DimensionSpecialEffects {
	public PlanetAlphaEffects() {
		super(192.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 vec3d, float brightness) {
		return vec3d.multiply((double) (brightness * 0.94F + 0.06F), (double) (brightness * 0.94F + 0.06F), (double) (brightness * 0.91F + 0.09F));
	}

	@Override
	public boolean isFoggyAt(int x, int z) {
		return false;
	}

	@Override
	public ISkyRenderHandler getSkyRenderHandler() {
		return new PlanetAlphaSkyRenderer();
	}
}
