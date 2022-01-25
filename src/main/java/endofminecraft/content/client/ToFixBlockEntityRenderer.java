package endofminecraft.content.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationBlockRenderer;
import tyrannotitanlib.library.tyrannomationcore.ITyrannomatable;

public abstract class ToFixBlockEntityRenderer<T extends BlockEntity & ITyrannomatable> extends TyrannomationBlockRenderer<T> {
	public ToFixBlockEntityRenderer(BlockEntityRendererProvider.Context context, TyrannomatedTyrannomationModel<T> modelProvider) {
		super(null, modelProvider);
	}
}
