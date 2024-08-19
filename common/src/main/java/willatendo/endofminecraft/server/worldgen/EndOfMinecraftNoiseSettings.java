package willatendo.endofminecraft.server.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

import java.util.List;

public class EndOfMinecraftNoiseSettings {
    public static final ResourceKey<NoiseGeneratorSettings> END_OF_MINECRAFT = register("end_of_minecraft");

    public static ResourceKey<NoiseGeneratorSettings> register(String string) {
        return ResourceKey.create(Registries.NOISE_SETTINGS, EndOfMinecraftUtils.resource(string));
    }

    public static void bootstrap(BootstapContext<NoiseGeneratorSettings> bootstapContext) {
        bootstapContext.register(END_OF_MINECRAFT, new NoiseGeneratorSettings(new NoiseSettings(-64, 384, 1, 2), Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.overworld(bootstapContext.lookup(Registries.DENSITY_FUNCTION), bootstapContext.lookup(Registries.NOISE), false, false), EndOfMinecraftSurfaceRules.prehistoric(), List.of(), 0, false, true, true, false));
    }
}
