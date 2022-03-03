package endofminecraft.server.world;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.dimension.WorldSeedHolder;

public class EndChunkGenerator extends NoiseBasedChunkGenerator {
	public static final Codec<EndChunkGenerator> CODEC = RecordCodecBuilder.create((p_188643_) -> {
		return p_188643_.group(RegistryLookupCodec.create(Registry.NOISE_REGISTRY).forGetter((p_188716_) -> {
			return p_188716_.noises;
		}), BiomeSource.CODEC.fieldOf("biome_source").forGetter((p_188711_) -> {
			return p_188711_.biomeSource;
		}), Codec.LONG.fieldOf("seed").orElseGet(WorldSeedHolder::getSeed).forGetter((p_188690_) -> {
			return p_188690_.seed;
		}), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((p_188652_) -> {
			return p_188652_.settings;
		})).apply(p_188643_, p_188643_.stable(EndChunkGenerator::new));
	});

	public EndChunkGenerator(Registry<NormalNoise.NoiseParameters> noises, BiomeSource source, long seed, Supplier<NoiseGeneratorSettings> settings) {
		super(noises, source, source, seed, settings);
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ChunkGenerator withSeed(long seed) {
		return new EndChunkGenerator(this.noises, this.biomeSource.withSeed(seed), seed, this.settings);
	}
}
