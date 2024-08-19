package willatendo.endofminecraft.data;

import net.minecraft.core.Direction.Axis;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.block.PlanetAlphaPortalBlock;

public class EndOfMinecraftBlockStateProvider extends BlockStateProvider {
    public EndOfMinecraftBlockStateProvider(PackOutput packOutput, String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, modId, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.horizontalBlock(EndOfMinecraftBlocks.ANOMALY_STONE.get(), this.models().withExistingParent("anomaly_stone", this.modLoc("anomaly_stone_template")));
        this.simpleBlock(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());
        this.getVariantBuilder(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get()).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.X).addModels(new ConfiguredModel(this.models().withExistingParent("planet_alpha_portal_ns", this.mcLoc("nether_portal_ns")).texture("portal", this.modLoc("block/planet_alpha_portal")).texture("particle", this.modLoc("block/planet_alpha_portal")))).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.Z).addModels(new ConfiguredModel(this.models().withExistingParent("planet_alpha_portal_ew", this.mcLoc("nether_portal_ew")).texture("portal", this.modLoc("block/planet_alpha_portal")).texture("particle", this.modLoc("block/planet_alpha_portal"))));
    }
}
