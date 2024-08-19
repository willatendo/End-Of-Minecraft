package willatendo.endofminecraft;

import willatendo.endofminecraft.client.particle.EndOfMinecraftParticleTypes;
import willatendo.endofminecraft.client.properties.EndOfMinecraftItemProperties;
import willatendo.endofminecraft.server.EndOfMinecraftCreativeModeTabs;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.block.entity.EndOfMinecraftBlockEntities;
import willatendo.endofminecraft.server.effect.EndOfMinecraftMobEffects;
import willatendo.endofminecraft.server.entity.EndOfMinecraftVillagerTypes;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;
import willatendo.endofminecraft.server.worldgen.EndOfMinecraftMultiNoiseBiomeSourceParameterListPresets;
import willatendo.simplelibrary.server.registry.SimpleRegistry;

import java.util.List;

public class EndOfMinecraftMod {
    public static void onInitialize(List<SimpleRegistry<?>> simpleRegistries) {
        EndOfMinecraftParticleTypes.init(simpleRegistries);
        EndOfMinecraftMobEffects.init(simpleRegistries);
        EndOfMinecraftBlocks.init(simpleRegistries);
        EndOfMinecraftBlockEntities.init(simpleRegistries);
        EndOfMinecraftItems.init(simpleRegistries);
        EndOfMinecraftCreativeModeTabs.init(simpleRegistries);
        EndOfMinecraftVillagerTypes.init(simpleRegistries);

        EndOfMinecraftMultiNoiseBiomeSourceParameterListPresets.init();
    }
}
