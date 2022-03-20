package endofminecraft.data.client;

import endofminecraft.EndOfMinecraftMod;
import endofminecraft.server.EndRegistry;
import endofminecraft.server.block.PlanetAlphaPortalBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EndBlockStateProvider extends BlockStateProvider {
	public static final String BLOCK_FOLDER = "block/";

	public EndBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, EndOfMinecraftMod.ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.portalBlock(EndRegistry.PLANET_ALPHA_PORTAL.get());
	}

	public void portalBlock(Block block) {
		String name = block.getRegistryName().getPath();
		ModelFile nsModel = this.models().getBuilder(name + "_ns").texture("particle", BLOCK_FOLDER + name).texture("portal", BLOCK_FOLDER + name).element().from(0, 0, 6).to(16, 16, 10).face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#portal").end().face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#portal").end().end();
		ModelFile ewModel = this.models().getBuilder(name + "_ew").texture("particle", BLOCK_FOLDER + name).texture("portal", BLOCK_FOLDER + name).element().from(6, 0, 0).to(10, 16, 16).face(Direction.EAST).uvs(0, 0, 16, 16).texture("#portal").end().face(Direction.WEST).uvs(0, 0, 16, 16).texture("#portal").end().end();
		this.getVariantBuilder(block).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.X).addModels(new ConfiguredModel(nsModel)).partialState().with(PlanetAlphaPortalBlock.AXIS, Axis.Z).addModels(new ConfiguredModel(ewModel));
	}
}
