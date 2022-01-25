package endofminecraft.content.event;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import endofminecraft.content.ModUtils;
import endofminecraft.content.server.init.EffectInit;
import endofminecraft.content.server.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE, modid = ModUtils.ID)
public class EndOfMinecraftEvents {
	private static final List<Item> RADIATION_SUIT = ImmutableList.of(ItemInit.RADIATION_HELMET, ItemInit.RADIATION_CHESTPLATE, ItemInit.RADIATION_LEGGINGS, ItemInit.RADIATION_BOOTS);

	@SubscribeEvent
	public static void playerTickEvent(PlayerTickEvent event) {
		Player entity = event.player;
		Level world = entity.level;
		if (!(isWearingSuit(entity, EquipmentSlot.HEAD) && isWearingSuit(entity, EquipmentSlot.CHEST) && isWearingSuit(entity, EquipmentSlot.LEGS) && isWearingSuit(entity, EquipmentSlot.FEET))) {
			if (world.canSeeSky(entity.blockPosition())) {
				if (world.dimension() == Level.OVERWORLD) {
					if (world.isRaining()) {
						entity.addEffect(new MobEffectInstance(EffectInit.IRRADIATED, 500));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void entityJoinWorld(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof WanderingTrader) {
			entity.setItemSlot(EquipmentSlot.HEAD, ItemInit.RADIATION_HELMET.getDefaultInstance());
			entity.setItemSlot(EquipmentSlot.CHEST, ItemInit.RADIATION_CHESTPLATE.getDefaultInstance());
			entity.setItemSlot(EquipmentSlot.LEGS, ItemInit.RADIATION_LEGGINGS.getDefaultInstance());
			entity.setItemSlot(EquipmentSlot.FEET, ItemInit.RADIATION_BOOTS.getDefaultInstance());
		}
	}

//	@SubscribeEvent
//	public static void wanderingTraderTrades(WandererTradesEvent event) {
//		addWandererTrades(event, new EmeraldForMapTrade(37, StructureInit.ANOMALY_CAVE, MapDecoration.Type.BANNER_GREEN, 1, 10));
//	}

//	private static void addWandererTrades(WandererTradesEvent event, VillagerTrades.ItemListing... trades) {
//		for (VillagerTrades.ItemListing trade : trades) {
//			event.getGenericTrades().add(trade);
//		}
//	}

	public static boolean isWearingSuit(LivingEntity living, EquipmentSlot pieceValue) {
		return RADIATION_SUIT.contains(living.getItemBySlot(pieceValue).getItem());
	}

	public static class EmeraldForMapTrade implements VillagerTrades.ItemListing {
		private final int emeraldCost;
		private final StructureFeature<?> destination;
		private final MapDecoration.Type destinationType;
		private final int maxUses;
		private final int villagerXp;

		public EmeraldForMapTrade(int emeraldCost, StructureFeature<?> destination, MapDecoration.Type destinationType, int maxUses, int villagerXp) {
			this.emeraldCost = emeraldCost;
			this.destination = destination;
			this.destinationType = destinationType;
			this.maxUses = maxUses;
			this.villagerXp = villagerXp;
		}

		@Nullable
		public MerchantOffer getOffer(Entity entity, Random rand) {
			if (!(entity.level instanceof ServerLevel)) {
				return null;
			} else {
				ServerLevel serverworld = (ServerLevel) entity.level;
				BlockPos blockpos = serverworld.findNearestMapFeature(this.destination, entity.blockPosition(), 100, true);
				if (blockpos != null) {
					ItemStack itemstack = MapItem.create(serverworld, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
					MapItem.renderBiomePreviewMap(serverworld, itemstack);
					MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
					itemstack.setHoverName(new TranslatableComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
					return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
				} else {
					return null;
				}
			}
		}
	}
}
