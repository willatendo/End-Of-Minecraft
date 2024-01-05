package willatendo.endofminecraft.data;

import com.google.gson.JsonObject;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import willatendo.simplelibrary.data.SimpleDimensionProvider;

public class EndOfMinecraftDimensionProvider extends SimpleDimensionProvider {
	public EndOfMinecraftDimensionProvider(FabricDataOutput fabricDataOutput, String modId) {
		super(fabricDataOutput, modId);
	}

	@Override
	public void allDimensions() {
		String dimensionType = this.dimensionType("planet_alpha", false, true, 1, true, false, 0, false, true, false, true, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, new ResourceLocation("minecraft:overworld"), 0, 7, 0);
		JsonObject biomeSource = new JsonObject();
		biomeSource.addProperty("type", "minecraft:multi_noise");
		biomeSource.addProperty("preset", "minecraft:overworld");
		this.dimension("planet_alpha", "endofminecraft:" + dimensionType, "minecraft:noise", "minecraft:overworld", biomeSource);
	}
}
