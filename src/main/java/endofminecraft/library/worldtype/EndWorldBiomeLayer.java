package endofminecraft.library.worldtype;

import endofminecraft.content.server.init.BiomeInit;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer0;

public class EndWorldBiomeLayer implements AreaTransformer0 {
	protected int[] commonBiomes = new int[] { EndWorldLayerUtil.getBiomeId(BiomeInit.WASTELAND_KEY), EndWorldLayerUtil.getBiomeId(BiomeInit.SCORCHLAND_KEY) };

	public EndWorldBiomeLayer() {
	}

	@Override
	public int applyPixel(Context iNoiseRandom, int rand1, int rand2) {
		return commonBiomes[iNoiseRandom.nextRandom(commonBiomes.length)];
	}
}
