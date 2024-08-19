package willatendo.endofminecraft.server.dimension;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.neoforged.neoforge.common.util.ITeleporter;

import java.util.function.Function;

public class AnomalyStoneTeleporter implements ITeleporter {
    private final PortalInfo portalInfo;

    public AnomalyStoneTeleporter(PortalInfo portalInfo) {
        this.portalInfo = portalInfo;
    }

    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destinationLevel, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        return this.portalInfo;
    }

    @Override
    public Entity placeEntity(Entity newEntity, ServerLevel currentLevel, ServerLevel destinationLevel, float yaw, Function<Boolean, Entity> repositionEntity) {
        newEntity.fallDistance = 0;
        return repositionEntity.apply(false);
    }
}
