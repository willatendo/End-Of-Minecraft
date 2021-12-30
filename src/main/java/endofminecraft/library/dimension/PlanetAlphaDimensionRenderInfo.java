package endofminecraft.library.dimension;

import javax.annotation.Nonnull;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.ISkyRenderHandler;

public class PlanetAlphaDimensionRenderInfo extends DimensionRenderInfo
{	
	public PlanetAlphaDimensionRenderInfo() 
	{
		super(128.0F, true, DimensionRenderInfo.FogType.NORMAL, false, false);
	}
	
	@Override
	public Vector3d getBrightnessDependentFogColor(Vector3d vec3d, float colour) 
	{
		return vec3d.multiply((double)(colour * 0.94F + 0.06F), (double)(colour * 0.94F + 0.06F), (double)(colour * 0.91F + 0.09F));
	}
	
	@Override
	public boolean isFoggyAt(int i1, int i2) 
	{
		return false;
	}
	
	@Nonnull
	@Override
	public ISkyRenderHandler getSkyRenderHandler() 
	{
		return new PlanetAlphaSkyRender();
	}
}
