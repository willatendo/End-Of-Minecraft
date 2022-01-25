package endofminecraft.content.server.init;

import endofminecraft.content.ModRegistry;
import endofminecraft.content.ModUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import tyrannotitanlib.library.tyrannotitanlib.item.TyrannoArmourMaterial;

public class ItemInit {
	private static final TyrannoArmourMaterial RADIATION_SUIT = new TyrannoArmourMaterial(ModUtils.rL("radiation_suit"), 10, new int[] { 1, 1, 2, 3 }, 10, () -> SoundEvents.ARMOR_EQUIP_IRON, 0, 0, null); 

	public static final Item RADIATION_HELMET = ModRegistry.item("radiation_helmet", new ArmorItem(RADIATION_SUIT, EquipmentSlot.HEAD, new Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final Item RADIATION_CHESTPLATE = ModRegistry.item("radiation_chestplate", new ArmorItem(RADIATION_SUIT, EquipmentSlot.CHEST, new Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final Item RADIATION_LEGGINGS = ModRegistry.item("radiation_leggings", new ArmorItem(RADIATION_SUIT, EquipmentSlot.LEGS, new Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final Item RADIATION_BOOTS = ModRegistry.item("radiation_boots", new ArmorItem(RADIATION_SUIT, EquipmentSlot.FEET, new Properties().tab(CreativeModeTab.TAB_MISC)));

	public static void init() {
	}
}
