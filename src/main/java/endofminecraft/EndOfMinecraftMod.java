package endofminecraft;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import endofminecraft.server.ModRegistry;
import endofminecraft.server.world.EndChunkGenerator;
import endofminecraft.server.world.EndSurfaceRules;
import endofminecraft.server.world.EndWorldPreset;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tyrannotitanlib.core.content.UtilitiesRegistry;
import tyrannotitanlib.tyranniregister.TyrannoRegister;
import tyrannotitanlib.tyranniworld.TyrannoBiome;

@Mod("endofminecraft")
public class EndOfMinecraftMod {
	public static final String END_OF_MINECRAFT_ID = "endofminecraft";
	public static final UtilitiesRegistry UTILS = new UtilitiesRegistry(END_OF_MINECRAFT_ID);

	public static final ForgeWorldPreset END_OF_THE_WORLD = TyrannoRegister.registerWorldPreset("end_of_the_world", new EndWorldPreset());
	public static final MultiNoiseBiomeSource.Preset END_OF_THE_WORLD_PRESET = new MultiNoiseBiomeSource.Preset(UTILS.resource("end_of_the_world"), (biomes) -> {
		return new Climate.ParameterList<>(ImmutableList.of(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
			return biomes.getOrThrow(ModRegistry.SCORCHLAND.getFirst());
		})));
	});
	public static final ResourceKey<NoiseGeneratorSettings> END_OF_THE_WORLD_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, UTILS.resource("end_of_the_world"));

	public EndOfMinecraftMod() {
		/*
		 * End of Minecraft:
		 * 
		 * A dead world with a few dead biomes. Set after a nuclear apocalypse, the
		 * player wakes up in a underground bunker with a chest with a single sapling
		 * and seed type. It'll either be an oak sapling and carrot, jungle sapling and
		 * wheat seed, birch sapling and beetroot seeds, or a spruce sapling and
		 * potatoes.
		 * 
		 * Entities: husks, irradiated villagers, and red, blue, and green beetles.
		 * 
		 * End Goal: Reach Planet Alpha
		 * 
		 * Structures: Irradiated Village, Anomaly Cave Bunker
		 * 
		 * Items: Radiation Suit
		 * 
		 * Blocks: Anomaly Stone, Planet Alpha Portal,
		 */

		var bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);

		ModRegistry.init();
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, END_OF_THE_WORLD_SETTINGS.location(), end());
			Registry.register(Registry.CHUNK_GENERATOR, UTILS.resource("end_chunk_generator"), EndChunkGenerator.CODEC);
		});
	}

	public static NoiseGeneratorSettings end() {
		return new NoiseGeneratorSettings(new StructureSettings(true), NoiseSettings.create(-64, 384, new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-0.078125D, 2, 8), new NoiseSlider(0.1171875D, 3, 0), 1, 2, false, false, false, TerrainProvider.overworld(false)), Blocks.STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(), EndSurfaceRules.end(), 1, false, true, true, true, true, false);
	}

	public static Pair<ResourceKey<Biome>, Biome> register(TyrannoBiome biome) {
		return Pair.of(biome.getKey(), TyrannoRegister.registerBiome(biome.name().getPath(), biome.getBiome()));
	}
}
