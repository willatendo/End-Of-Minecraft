package willatendo.endofminecraft.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import willatendo.endofminecraft.server.EndOfMinecraftCreativeModeTabs;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.effect.EndOfMinecraftMobEffects;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.simplelibrary.server.util.SimpleUtils;

public class EndOfMinecraftLanguageProvider extends LanguageProvider {
    private final String modId;

    public EndOfMinecraftLanguageProvider(PackOutput packOutput, String modId, String locale) {
        super(packOutput, modId, locale);
        this.modId = modId;
    }

    @Override
    protected void addTranslations() {
        this.add("biome.endofminecraft.dead_tree_grove", "Dead Tree Grove");
        this.add("biome.endofminecraft.scorchland", "Scorchland");
        this.add("biome.endofminecraft.wasteland", "Wasteland");

        this.add(EndOfMinecraftBlocks.ANOMALY_STONE.get());
        this.add(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());

        this.add("death.attack.irradiation", "%1$s was irradiated to death");
        this.add("death.attack.irradiation.player", "%1$s irradiated to death while fighting %2$s");

        this.add(EndOfMinecraftMobEffects.IRRADIATED.get());

        this.add("generator.endofminecraft.end_of_minecraft", "End of Minecraft");

        this.add(EndOfMinecraftItems.ANOMALY_DETECTOR.get());
        this.add("item.endofminecraft.anomaly_detector.uncalibrated", "No Anomaly's Detected!");
        this.add("item.endofminecraft.anomaly_detector.calibrated", "Anomaly Detected!");

        this.add(EndOfMinecraftCreativeModeTabs.END_OF_MINECRAFT.get(), "End of Minecraft");

        this.add("resourcePack.fossilslegacy.description", "End of Minecraft Assets");
    }


    public void add(Block key, String name) {
        this.add(key.getDescriptionId(), name);
    }

    public void add(Item key, String name) {
        this.add(key.getDescriptionId(), name);
    }

    public void add(ItemStack key, String name) {
        this.add(key.getDescriptionId(), name);
    }

    public void add(Enchantment key, String name) {
        this.add(key.getDescriptionId(), name);
    }

    public void add(MobEffect key, String name) {
        this.add(key.getDescriptionId(), name);
    }

    public void add(EntityType<?> key, String name) {
        this.add(key.getDescriptionId(), name);
    }

    public void add(Item item) {
        this.add(item, SimpleUtils.autoName(BuiltInRegistries.ITEM.getKey(item).getPath()));
    }

    public void add(Block block) {
        this.add(block, SimpleUtils.autoName(BuiltInRegistries.BLOCK.getKey(block).getPath()));
    }

    public void add(Enchantment enchantment) {
        this.add(enchantment, SimpleUtils.autoName(BuiltInRegistries.ENCHANTMENT.getKey(enchantment).getPath()));
    }

    public void add(MobEffect mobEffect) {
        this.add(mobEffect, SimpleUtils.autoName(BuiltInRegistries.MOB_EFFECT.getKey(mobEffect).getPath()));
    }

    public void add(EntityType<?> entityType) {
        this.add(entityType, SimpleUtils.autoName(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath()));
    }

    public void add(SoundEvent soundEvent) {
        this.add(soundEvent, SimpleUtils.autoName(BuiltInRegistries.SOUND_EVENT.getKey(soundEvent).getPath()));
    }

    public void add(MenuType menuType) {
        this.add(menuType, SimpleUtils.autoName(BuiltInRegistries.MENU.getKey(menuType).getPath()));
    }

    public void add(String category, String advancement, String title, String desc) {
        this.add("advancements." + this.modId + "." + category + "." + advancement + ".title", title);
        this.add("advancements." + this.modId + "." + category + "." + advancement + ".desc", desc);
    }

    public void add(SoundEvent soundEvent, String name) {
        this.add("sound." + BuiltInRegistries.SOUND_EVENT.getKey(soundEvent).getNamespace() + BuiltInRegistries.SOUND_EVENT.getKey(soundEvent).getPath(), name);
    }

    public void add(MenuType menuType, String name) {
        this.add("menu." + BuiltInRegistries.MENU.getKey(menuType).getNamespace() + BuiltInRegistries.MENU.getKey(menuType).getPath(), name);
    }

    public void add(CreativeModeTab creativeModeTab, String name) {
        this.add(creativeModeTab.getDisplayName().getString(), name);
    }

    public void addDesc(Item item, String... descs) {
        for (int i = 0; i < descs.length; i++) {
            this.add(item.getDescriptionId() + ".desc" + i, descs[i]);
        }
    }

    public void addDesc(Item item, String desc) {
        this.add(item.getDescriptionId() + ".desc", desc);
    }

    public void addDesc(Block block, String... descs) {
        for (int i = 0; i < descs.length; i++) {
            this.add(block.getDescriptionId() + ".desc" + i, descs[i]);
        }
    }

    public void addDesc(Block block, String name) {
        this.add(block.getDescriptionId() + ".desc", name);
    }
}
