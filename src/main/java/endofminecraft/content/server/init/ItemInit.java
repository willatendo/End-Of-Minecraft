package endofminecraft.content.server.init;

import endofminecraft.library.util.ModRegistry;
import endofminecraft.library.util.ModUtils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.SoundEvents;
import tyrannotitanlib.library.base.item.TyrannoArmourTeir;

public class ItemInit {
	public static final TyrannoArmourTeir RADIATION_SUIT = new TyrannoArmourTeir(ModUtils.rL("radiation_suit"), 276, new int[] { 3, 1, 2, 1 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0, 0, null);
	
	public static final Item RADIATION_HELMAT = ModRegistry.item("radiation_helmet", new ArmorItem(RADIATION_SUIT, EquipmentSlotType.HEAD, new Properties().tab(ItemGroup.TAB_MISC)));
	public static final Item RADIATION_CHESTPLATE = ModRegistry.item("radiation_chestplate", new ArmorItem(RADIATION_SUIT, EquipmentSlotType.CHEST, new Properties().tab(ItemGroup.TAB_MISC)));
	public static final Item RADIATION_LEGGINGS = ModRegistry.item("radiation_leggings", new ArmorItem(RADIATION_SUIT, EquipmentSlotType.LEGS, new Properties().tab(ItemGroup.TAB_MISC)));
	public static final Item RADIATION_BOOTS = ModRegistry.item("radiation_boots", new ArmorItem(RADIATION_SUIT, EquipmentSlotType.FEET, new Properties().tab(ItemGroup.TAB_MISC)));
	
	public static void init() {
	}
}
