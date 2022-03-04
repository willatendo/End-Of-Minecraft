package endofminecraft.server.event;

import java.util.List;

import endofminecraft.EndOfMinecraftMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, modid = EndOfMinecraftMod.ID)
public class EndHandlerData {
	public EndData worldFileCache;
	public static BlockPos spawnLocation;
	public static boolean spawnSet;
	public static boolean bunkerSpawned;

	@SubscribeEvent
	public void loadData(Load event) {
		LevelAccessor level = event.getWorld();
		if (level instanceof ServerLevel server) {
			this.worldFileCache = new EndData(server.getDataStorage() + "end_of_minecraft_cache.dat");
			if (!this.worldFileCache.checkIfExists()) {
				bunkerSpawned = false;
				spawnSet = false;
				this.worldFileCache.createFile();
			} else if (this.worldFileCache.loadSpawnLoc().getY() != 0) {
				spawnLocation = this.worldFileCache.loadSpawnLoc();
			}
		}
	}

	@SubscribeEvent
	public void onRespawnEvent(LivingUpdateEvent event) {
		if (event.getEntityLiving() instanceof Player) {
			Player player = (Player) event.getEntityLiving();
			if (!spawnSet && spawnLocation != null) {
				BlockPos upPos = spawnLocation.above(1);
				if (player.blockPosition().getY() == upPos.getY()) {
					spawnSet = true;
				}

				player.moveTo(spawnLocation.above(1), 0.0F, 0.0F);
			}
		}
	}

	@SubscribeEvent
	public void setSpawnpointEvent(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof Player player) {
			Level level = event.getWorld();
			if (this.isNewPlayer(player)) {
				if (spawnLocation == null && player.blockPosition() != BlockPos.ZERO) {
					spawnLocation = player.blockPosition().below(9);
				}

				if (!spawnSet && spawnLocation != null) {
					if (!bunkerSpawned) {
						if (!event.getWorld().isClientSide) {
							this.spawnBunker(event.getWorld());
						}

						this.worldFileCache.saveSpawnLoc(spawnLocation);
						bunkerSpawned = true;
					}

					BlockPos pos = spawnLocation.above();
					if (player instanceof ServerPlayer server) {
						server.setRespawnPosition(level.dimension(), pos, 0.0F, true, false);
					}
				}

				this.worldFileCache.savePlayerName(player.getDisplayName().getString());
			}
		}
	}

	private boolean isNewPlayer(Player player) {
		List loadedPlayers = this.worldFileCache.getPlayerNames();
		return loadedPlayers == null || !loadedPlayers.contains(player.getDisplayName().getString());
	}

	public static void spawnBunker(Level level) {

	}
}
