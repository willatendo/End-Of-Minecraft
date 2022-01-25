package endofminecraft.library.worldtype;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

public class EndWorldChunkGenerator extends NoiseBasedChunkGenerator {
	public static final Codec<EndWorldChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource), Codec.LONG.fieldOf("seed").orElseGet(() -> EndWorldChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(EndWorldChunkGenerator::getDimensionSettings)).apply(instance, instance.stable(EndWorldChunkGenerator::new)));

	private long seed;
	public static long hackSeed;

	public EndWorldChunkGenerator(Registry<NormalNoise.NoiseParameters> noises, BiomeSource provider, long seed, Supplier<NoiseGeneratorSettings> settingsIn) {
		super(noises, provider, seed, settingsIn);
		this.seed = seed;
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new EndWorldChunkGenerator(this.noises, biomeSource.withSeed(seed), seed, getDimensionSettings());
	}

	private Supplier<NoiseGeneratorSettings> getDimensionSettings() {
		return settings;
	}
}
