package endofminecraft.library.worldtype;

import endofminecraft.content.server.init.BiomeInit;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class EndWorldBiomeLayer implements IAreaTransformer0 {
	protected int[] commonBiomes = new int[] { EndWorldLayerUtil.getBiomeId(BiomeInit.WASTELAND_KEY), EndWorldLayerUtil.getBiomeId(BiomeInit.SCORCHLAND_KEY) };

	public EndWorldBiomeLayer() {
	}

	@Override
	public int applyPixel(INoiseRandom iNoiseRandom, int rand1, int rand2) {
		return commonBiomes[iNoiseRandom.nextRandom(commonBiomes.length)];
	}
}
