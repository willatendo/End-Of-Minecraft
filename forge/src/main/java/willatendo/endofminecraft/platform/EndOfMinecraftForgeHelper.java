package willatendo.endofminecraft.platform;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import willatendo.endofminecraft.server.dimension.AnomalyStoneTeleporter;

public class EndOfMinecraftForgeHelper implements EndOfMinecraftModloaderHelper {
    @Override
    public void changeDimensions(Entity entity, ServerLevel serverLevel, PortalInfo portalInfo) {
        entity.changeDimension(serverLevel, new AnomalyStoneTeleporter(portalInfo));
    }
}
