package endofminecraft.content.event;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import endofminecraft.content.ModUtils;
import endofminecraft.content.server.init.EffectInit;
import endofminecraft.content.server.init.ItemInit;
import endofminecraft.content.server.init.StructureInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE, modid = ModUtils.ID)
public class EndOfMinecraftEvents {
	private static final List<Item> RADIATION_SUIT = ImmutableList.of(ItemInit.RADIATION_HELMET, ItemInit.RADIATION_CHESTPLATE, ItemInit.RADIATION_LEGGINGS, ItemInit.RADIATION_BOOTS);

	@SubscribeEvent
	public static void playerTickEvent(PlayerTickEvent event) {
		PlayerEntity entity = event.player;
		World world = entity.level;
		if (!isWearingSuit(entity, EquipmentSlotType.HEAD) && !isWearingSuit(entity, EquipmentSlotType.CHEST) && !isWearingSuit(entity, EquipmentSlotType.LEGS) && !isWearingSuit(entity, EquipmentSlotType.FEET)) {
			if (world.canSeeSky(entity.blockPosition())) {
				if (world.dimension() == World.OVERWORLD) {
					if (world.isRaining()) {
						entity.addEffect(new EffectInstance(EffectInit.IRRADIATED, 500));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void entityJoinWorld(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof WanderingTraderEntity) {
			entity.setItemSlot(EquipmentSlotType.HEAD, ItemInit.RADIATION_HELMET.getDefaultInstance());
			entity.setItemSlot(EquipmentSlotType.CHEST, ItemInit.RADIATION_CHESTPLATE.getDefaultInstance());
			entity.setItemSlot(EquipmentSlotType.LEGS, ItemInit.RADIATION_LEGGINGS.getDefaultInstance());
			entity.setItemSlot(EquipmentSlotType.FEET, ItemInit.RADIATION_BOOTS.getDefaultInstance());
		}
	}

	@SubscribeEvent
	public static void wanderingTraderTrades(WandererTradesEvent event) {
		addWandererTrades(event, new EmeraldForMapTrade(37, StructureInit.ANOMALY_CAVE, MapDecoration.Type.BANNER_GREEN, 1, 10));
	}

	private static void addWandererTrades(WandererTradesEvent event, VillagerTrades.ITrade... trades) {
		for (VillagerTrades.ITrade trade : trades) {
			event.getGenericTrades().add(trade);
		}
	}

	public static boolean isWearingSuit(LivingEntity living, EquipmentSlotType pieceValue) {
		return RADIATION_SUIT.contains(living.getItemBySlot(pieceValue).getItem());
	}

	public static class EmeraldForMapTrade implements VillagerTrades.ITrade {
		private final int emeraldCost;
		private final Structure<?> destination;
		private final MapDecoration.Type destinationType;
		private final int maxUses;
		private final int villagerXp;

		public EmeraldForMapTrade(int emeraldCost, Structure<?> destination, MapDecoration.Type destinationType, int maxUses, int villagerXp) {
			this.emeraldCost = emeraldCost;
			this.destination = destination;
			this.destinationType = destinationType;
			this.maxUses = maxUses;
			this.villagerXp = villagerXp;
		}

		@Nullable
		public MerchantOffer getOffer(Entity entity, Random rand) {
			if (!(entity.level instanceof ServerWorld)) {
				return null;
			} else {
				ServerWorld serverworld = (ServerWorld) entity.level;
				BlockPos blockpos = serverworld.findNearestMapFeature(this.destination, entity.blockPosition(), 100, true);
				if (blockpos != null) {
					ItemStack itemstack = FilledMapItem.create(serverworld, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
					FilledMapItem.renderBiomePreviewMap(serverworld, itemstack);
					MapData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
					itemstack.setHoverName(new TranslationTextComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
					return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
				} else {
					return null;
				}
			}
		}
	}
}
