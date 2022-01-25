package endofminecraft.library.tileentity;

import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.block.AnomalyStoneBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tyrannotitanlib.library.tyrannomationcore.ITyrannomatable;
import tyrannotitanlib.library.tyrannomationcore.PlayState;
import tyrannotitanlib.library.tyrannomationcore.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomationcore.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomationcore.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomationcore.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomationcore.manager.TyrannomationFactory;

public class AnomalyStoneTileEntity extends BlockEntity implements ITyrannomatable {
	private TyrannomationFactory factory = new TyrannomationFactory(this);

	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) {
		BlockState state = this.getBlockState();
		if (state.getValue(AnomalyStoneBlock.ACTIVATED).booleanValue() == false) {
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.anomaly_stone.normal", true));
		} else {
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.anomaly_stone.activated", true));
		}

		event.getController().transitionLengthTicks = 0;
		return PlayState.CONTINUE;
	}

	public AnomalyStoneTileEntity(BlockPos pos, BlockState state) {
		super(TileEntityInit.ANONALY_STONE_TILE_ENTITY, pos, state);
	}

	@Override
	public void registerControllers(TyrannomationData data) {
		data.addAnimationController(new TyrannomationController<>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() {
		return factory;
	}
}
