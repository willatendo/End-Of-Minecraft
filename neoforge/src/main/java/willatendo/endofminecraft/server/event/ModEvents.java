package willatendo.endofminecraft.server.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import willatendo.endofminecraft.EndOfMinecraftNeoforgeMod;
import willatendo.endofminecraft.server.entity.EndOfMinecraftVillagerTypes;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.registry.NeoForgeRegister;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = EndOfMinecraftUtils.ID)
public class ModEvents {
    @SubscribeEvent
    public static void common(FMLCommonSetupEvent event) {
        BasicEvents.common();
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        NeoForgeRegister.register(event, EndOfMinecraftNeoforgeMod.REGISTRIES.toArray(SimpleRegistry[]::new));
    }
}
