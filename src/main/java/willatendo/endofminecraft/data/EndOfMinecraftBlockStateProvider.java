package willatendo.endofminecraft.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.Direction.Axis;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.block.PlanetAlphaPortalBlock;
import willatendo.simplelibrary.data.SimpleBlockStateProvider;
import willatendo.simplelibrary.data.model.ConfiguredModel;
import willatendo.simplelibrary.data.util.ExistingFileHelper;

public class EndOfMinecraftBlockStateProvider extends SimpleBlockStateProvider {
	public EndOfMinecraftBlockStateProvider(FabricDataOutput fabricDataOutput, String modId, ExistingFileHelper existingFileHelper) {
		super(fabricDataOutput, modId, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.horizontalBlock(EndOfMinecraftBlocks.ANOMALY_STONE.get(), this.models().withExistingParent("anomaly_stone", this.modLoc("anomaly_stone_template")));
		this.simpleBlock(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());
		this.getVariantBuilder(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get()).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.X).addModels(new ConfiguredModel(this.models().withExistingParent("planet_alpha_portal_ns", this.mcLoc("nether_portal_ns")).texture("portal", this.modLoc("block/planet_alpha_portal")).texture("particle", this.modLoc("block/planet_alpha_portal")))).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.Z).addModels(new ConfiguredModel(this.models().withExistingParent("planet_alpha_portal_ew", this.mcLoc("nether_portal_ew")).texture("portal", this.modLoc("block/planet_alpha_portal")).texture("particle", this.modLoc("block/planet_alpha_portal"))));
	}
}
