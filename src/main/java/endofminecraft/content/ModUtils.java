package endofminecraft.content;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

public class ModUtils {
	public static final Logger LOGGER = LogManager.getLogger(ModUtils.ID);
	public static boolean DISABLE_IN_DEV = false;

	public static final String ID = "endofminecraft";

	public static ResourceLocation rL(String location) {
		return new ResourceLocation(ID, location);
	}

	public static TranslatableComponent tTC(String type, String key) {
		return new TranslatableComponent(type + "." + ID + "." + key);
	}

	public static TranslatableComponent cTC(String type, String key, ChatFormatting colour) {
		TranslatableComponent text = tTC(type, key);
		text.withStyle(colour);
		return text;
	}

	public static TranslatableComponent gTC(String type, String key) {
		TranslatableComponent text = tTC(type, key);
		text.withStyle(ChatFormatting.GRAY);
		return text;
	}
}
