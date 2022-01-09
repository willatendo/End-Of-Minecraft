package endofminecraft.content.server.init;

import endofminecraft.library.biome.ModBiomeMaker;
import endofminecraft.library.util.ModRegistry;
import endofminecraft.library.util.ModUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeInit {
	public static final Biome WASTELAND = ModRegistry.biome("wasteland", ModBiomeMaker.wasteland());
	public static final Biome SCORCHLAND = ModRegistry.biome("scorchland", ModBiomeMaker.scorchland());

	public static final RegistryKey<Biome> WASTELAND_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, ModUtils.rL("wasteland"));
	public static final RegistryKey<Biome> SCORCHLAND_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, ModUtils.rL("scorchland"));

	public static void init() {
	}
}
