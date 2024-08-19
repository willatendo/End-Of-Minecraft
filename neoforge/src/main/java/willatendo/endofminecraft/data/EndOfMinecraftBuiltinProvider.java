package willatendo.endofminecraft.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.RegistrySetBuilder.RegistryBootstrap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import willatendo.endofminecraft.server.biome.EndOfMinecraftBiomes;
import willatendo.endofminecraft.server.entity.EndOfMinecraftDamageTypes;
import willatendo.endofminecraft.server.feature.EndOfMinecraftConfiguredFeatures;
import willatendo.endofminecraft.server.feature.EndOfMinecraftPlacedFeatures;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructureSets;
import willatendo.endofminecraft.server.structure.EndOfMinecraftStructures;
import willatendo.endofminecraft.server.worldgen.*;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class EndOfMinecraftBuiltinProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, (RegistryBootstrap) EndOfMinecraftConfiguredFeatures::bootstrap).add(Registries.PLACED_FEATURE, (RegistryBootstrap) EndOfMinecraftPlacedFeatures::bootstrap).add(Registries.DAMAGE_TYPE, (RegistryBootstrap) EndOfMinecraftDamageTypes::bootstrap).add(Registries.STRUCTURE, (RegistryBootstrap) EndOfMinecraftStructures::bootstrap).add(Registries.STRUCTURE_SET, (RegistryBootstrap) EndOfMinecraftStructureSets::bootstrap).add(Registries.BIOME, EndOfMinecraftBiomes::bootstrap).add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, EndOfMinecraftMultiNoiseBiomeSourceParameterLists::bootstrap).add(Registries.NOISE_SETTINGS, EndOfMinecraftNoiseSettings::bootstrap).add(Registries.TEMPLATE_POOL, EndOfMinecraftPools::bootstrap).add(Registries.PROCESSOR_LIST, EndOfMinecraftProcessorList::bootstrap).add(Registries.WORLD_PRESET, EndOfMinecraftWorldPresets::bootstrap);

    public EndOfMinecraftBuiltinProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, String modId) {
        super(packOutput, registries, BUILDER, Collections.singleton(modId));
    }
}
