package endofminecraft.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.Registry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import tyrannotitanlib.library.dimension.WorldSeedHolder;

@Mixin(WorldGenSettings.class)
public class SeedFixer {
	@Inject(method = "<init>(JZZLnet/minecraft/core/Registry;Ljava/util/Optional;)V", at = @At(value = "RETURN"))
	private void getSeedFromConstructor(long seed, boolean generateFeatures, boolean bonusChest, Registry<LevelStem> options, Optional<String> legacyOptions, CallbackInfo cir) {
		WorldSeedHolder.setSeed(seed);
	}
}
