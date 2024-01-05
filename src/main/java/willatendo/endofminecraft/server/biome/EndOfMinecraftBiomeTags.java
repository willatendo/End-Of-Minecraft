package willatendo.endofminecraft.server.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.util.SimpleUtils;
import willatendo.simplelibrary.server.util.TagRegister;

public class EndOfMinecraftBiomeTags {
	public static final TagRegister<Biome> BIOME_TAGS = SimpleUtils.create(Registries.BIOME, EndOfMinecraftUtils.ID);

	public static final TagKey<Biome> HAS_ANOMALY_CAVE = BIOME_TAGS.register("has_structure/anomaly_cave");
	public static final TagKey<Biome> HAS_VILLAGE_POST_APOCALYPTIC = BIOME_TAGS.register("has_structure/village_post_apocalyptic");
}
