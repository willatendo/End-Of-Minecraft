package willatendo.endofminecraft.platform;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;

public class EndOfMinecraftFabricHelper implements EndOfMinecraftModloaderHelper {
    @Override
    public void changeDimensions(Entity entity, ServerLevel serverLevel, PortalInfo portalInfo) {
        FabricDimensions.teleport(entity, serverLevel, portalInfo);
    }
}
