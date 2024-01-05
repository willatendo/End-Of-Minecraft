package willatendo.endofminecraft.server.item;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructureTags;

public class AnomalyDetectorItem extends Item {
	public AnomalyDetectorItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
		if (level instanceof ServerLevel serverLevel) {
			BlockPos structurePos = serverLevel.findNearestMapStructure(EndOfMinecraftStructureTags.ANOMALY_DETECTOR_DETECTABLE, player.blockPosition(), 100, false);
			if (structurePos != null) {
				ItemStack itemStack = new ItemStack(EndOfMinecraftItems.ANOMALY_DETECTOR.get());
				CompoundTag itemTag = itemStack.getOrCreateTag();
				itemTag.putInt("AnomalyX", structurePos.getX());
				itemTag.putInt("AnomalyY", structurePos.getY());
				itemTag.putInt("AnomalyZ", structurePos.getZ());
				player.setItemInHand(interactionHand, itemStack);
				return InteractionResultHolder.success(itemStack);
			}
		}
		return super.use(level, player, interactionHand);
	}

	public static BlockPos getLocation(ItemStack itemStack) {
		CompoundTag itemTag = itemStack.getOrCreateTag();
		return new BlockPos(itemTag.getInt("AnomalyX"), itemTag.getInt("AnomalyY"), itemTag.getInt("AnomalyZ"));
	}

	public static boolean hasLocation(ItemStack itemStack) {
		return itemStack.getOrCreateTag().contains("AnomalyX") && itemStack.getOrCreateTag().contains("AnomalyY") && itemStack.getOrCreateTag().contains("AnomalyZ");
	}

	@Override
	public boolean isFoil(ItemStack itemStack) {
		return hasLocation(itemStack);
	}
}
