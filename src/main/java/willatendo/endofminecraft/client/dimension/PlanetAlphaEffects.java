package willatendo.endofminecraft.client.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

public class PlanetAlphaEffects extends DimensionSpecialEffects {
	public PlanetAlphaEffects() {
		super(192.0f, true, SkyType.NORMAL, false, false);
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 fogColour, float timeOfDay) {
		return fogColour.multiply(timeOfDay * 0.94f + 0.06f, timeOfDay * 0.94f + 0.06f, timeOfDay * 0.91f + 0.09f);
	}

	@Override
	public boolean isFoggyAt(int x, int y) {
		return false;
	}
}
