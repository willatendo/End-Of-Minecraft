package endofminecraft.library.dimension;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

public class PlanetAlphaDimensionRenderInfo extends DimensionSpecialEffects {
	public PlanetAlphaDimensionRenderInfo() {
		super(128.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 vec3d, float colour) {
		return vec3d.multiply((double) (colour * 0.94F + 0.06F), (double) (colour * 0.94F + 0.06F), (double) (colour * 0.91F + 0.09F));
	}

	@Override
	public boolean isFoggyAt(int i1, int i2) {
		return false;
	}

	@Nonnull
	@Override
	public ISkyRenderHandler getSkyRenderHandler() {
		return new PlanetAlphaSkyRender();
	}
}
