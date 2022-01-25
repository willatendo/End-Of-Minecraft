package endofminecraft.library.worldtype;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import endofminecraft.content.server.init.BiomeInit;
import net.minecraft.resources.ResourceKey;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.data.worldgen.biome.Biomes;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.newbiome.layer.Layer;

public class EndWorldBiomeProvider extends BiomeSource {
	public static final Codec<EndWorldBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").orElse(EndWorldChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(EndWorldBiomeProvider::new)));

	private final long seed;
	private final Registry<Biome> registry;
	private final Layer genBiomes;
	private static final List<ResourceKey<Biome>> BIOMES = ImmutableList.of(BiomeInit.WASTELAND_KEY, BiomeInit.SCORCHLAND_KEY);

	public EndWorldBiomeProvider(long seed, Registry<Biome> registry) {
		super(BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
		this.seed = seed;
		this.registry = registry;
		this.genBiomes = EndWorldLayerUtil.makeLayers(seed, registry);
	}

	@Override
	public BiomeSource withSeed(long s) {
		return new EndWorldBiomeProvider(s, registry);
	}

	@Override
	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return this.getBiomeFromPos(registry, x, z);
	}

	public Biome getBiomeFromPos(Registry<Biome> registry, int x, int z) {
		int i = genBiomes.area.get(x, z);
		Biome biome = registry.byId(i);
		if (biome == null) {
			if (SharedConstants.IS_RUNNING_IN_IDE) {
				throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
			} else {
				return registry.get(Biomes.byId(0));
			}
		} else {
			return biome;
		}
	}
}
