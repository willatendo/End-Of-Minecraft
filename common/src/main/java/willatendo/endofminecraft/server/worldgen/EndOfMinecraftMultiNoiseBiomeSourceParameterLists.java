package willatendo.endofminecraft.server.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftMultiNoiseBiomeSourceParameterLists {
    public static final ResourceKey<MultiNoiseBiomeSourceParameterList> END_OF_MINECRAFT = register("end_of_minecraft");

    public static ResourceKey<MultiNoiseBiomeSourceParameterList> register(String string) {
        return ResourceKey.create(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, EndOfMinecraftUtils.resource(string));
    }

    public static void bootstrap(BootstapContext<MultiNoiseBiomeSourceParameterList> bootstapContext) {
        HolderGetter<Biome> biomes = bootstapContext.lookup(Registries.BIOME);
        bootstapContext.register(END_OF_MINECRAFT, new MultiNoiseBiomeSourceParameterList(EndOfMinecraftMultiNoiseBiomeSourceParameterListPresets.END_OF_MINECRAFT, biomes));
    }
}
