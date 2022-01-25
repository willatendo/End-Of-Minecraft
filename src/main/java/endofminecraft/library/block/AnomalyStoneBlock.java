package endofminecraft.library.block;

import endofminecraft.content.server.init.BlockInit;
import endofminecraft.library.ModMaterials;
import endofminecraft.library.tileentity.AnomalyStoneTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class AnomalyStoneBlock extends Block implements EntityBlock {
	public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

	public AnomalyStoneBlock() {
		super(BlockBehaviour.Properties.of(ModMaterials.ANOMALY_STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion().randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, Boolean.valueOf(false)));
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (state.getValue(ACTIVATED).booleanValue() == false) {
			if (world.getBlockState(pos.south(5)) == Blocks.AIR.defaultBlockState()) {
				world.setBlock(pos, state.setValue(ACTIVATED, Boolean.valueOf(true)), 3);
				world.setBlock(pos.south(5), BlockInit.PLANET_ALPHA_PORTAL.defaultBlockState(), 3);
			}
		} else {
			if (world.getBlockState(pos.south(5)) == BlockInit.PLANET_ALPHA_PORTAL.defaultBlockState()) {
				world.setBlock(pos, state.setValue(ACTIVATED, Boolean.valueOf(false)), 3);
				world.setBlock(pos.south(5), Blocks.AIR.defaultBlockState(), 3);
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasBlockEntity() && state.getBlock() != newState.getBlock()) {
			world.removeBlockEntity(pos);
		}
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(ACTIVATED);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AnomalyStoneTileEntity(pos, state);
	}
}
