package willatendo.endofminecraft.platform;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import willatendo.simplelibrary.server.util.SimpleUtils;

public interface EndOfMinecraftModloaderHelper {
    public static final EndOfMinecraftModloaderHelper INSTANCE = SimpleUtils.loadModloaderHelper(EndOfMinecraftModloaderHelper.class);

    void changeDimensions(Entity entity, ServerLevel serverLevel, PortalInfo portalInfo);
}
