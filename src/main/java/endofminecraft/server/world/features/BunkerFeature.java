package endofminecraft.server.world.features;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BunkerFeature extends Feature<NoneFeatureConfiguration> {
	public BunkerFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> config) {
		WorldGenLevel worldgenlevel = config.level();
		BlockPos pos = config.origin();
		worldgenlevel.setBlock(pos, Blocks.BELL.defaultBlockState(), 3);
		return true;
	}
}
