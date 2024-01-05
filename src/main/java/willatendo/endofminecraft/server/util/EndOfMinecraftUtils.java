package willatendo.endofminecraft.server.util;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import willatendo.simplelibrary.server.util.SimpleUtils;

public class EndOfMinecraftUtils {
	public static final String ID = "endofminecraft";

	public static ResourceLocation resource(String path) {
		return SimpleUtils.resource(ID, path);
	}

	public static MutableComponent translation(String type, String name) {
		return SimpleUtils.translation(ID, type, name);
	}

	public static MutableComponent translation(String type, String name, Object... args) {
		return SimpleUtils.translation(ID, type, name, args);
	}
}
