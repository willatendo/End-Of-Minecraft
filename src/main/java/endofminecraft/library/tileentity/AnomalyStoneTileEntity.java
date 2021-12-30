package endofminecraft.library.tileentity;

import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.block.AnomalyStoneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class AnomalyStoneTileEntity extends TileEntity implements ITyrannomatable
{
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event)
    {
		BlockState state = this.getBlockState();
		if(state.getValue(AnomalyStoneBlock.ACTIVATED).booleanValue() == false)
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.anomaly_stone.normal", true));
		}
		else
		{
			event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.anomaly_stone.activated", true));
		}
		
		event.getController().transitionLengthTicks = 0;
        return PlayState.CONTINUE;
    }
	
	public AnomalyStoneTileEntity() 
	{
		super(TileEntityInit.ANONALY_STONE_TILE_ENTITY);
	}

	@Override
	public void registerControllers(TyrannomationData data) 
	{
		data.addAnimationController(new TyrannomationController<>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() 
	{
		return factory;
	}
}
