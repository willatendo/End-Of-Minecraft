package endofminecraft.content.server.init;

import endofminecraft.content.ModRegistry;
import endofminecraft.content.ModUtils;
import endofminecraft.library.biome.ModBiomeMaker;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;

public class BiomeInit {
	public static final Biome WASTELAND = ModRegistry.biome("wasteland", ModBiomeMaker.wasteland());
	public static final Biome SCORCHLAND = ModRegistry.biome("scorchland", ModBiomeMaker.scorchland());

	public static final ResourceKey<Biome> WASTELAND_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, ModUtils.rL("wasteland"));
	public static final ResourceKey<Biome> SCORCHLAND_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, ModUtils.rL("scorchland"));

	public static void init() {
	}
}
