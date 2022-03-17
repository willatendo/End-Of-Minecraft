package endofminecraft.data.client;

import endofminecraft.EndOfMinecraftMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EndBlockStateProvider extends BlockStateProvider {
	public static final String BLOCK_FOLDER = "block/";

	public EndBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, EndOfMinecraftMod.ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
	}
}
