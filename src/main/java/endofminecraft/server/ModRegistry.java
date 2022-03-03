package endofminecraft.server;

import com.mojang.datafixers.util.Pair;

import endofminecraft.server.biomes.ScorchlandBiome;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import tyrannotitanlib.tyranniregister.TyrannoRegister;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

public class ModRegistry {
	// Biomes
	public static final Pair<ResourceKey<Biome>, Biome> SCORCHLAND = register(new ScorchlandBiome());

	public static Pair<ResourceKey<Biome>, Biome> register(TyrannoBiome biome) {
		return Pair.of(biome.getKey(), TyrannoRegister.registerBiome(biome.name().getPath(), biome.getBiome()));
	}

	public static void init() {
	}
}
