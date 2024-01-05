package willatendo.endofminecraft.server.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import willatendo.endofminecraft.server.block.entity.AnomalyStoneBlockEntity;
import willatendo.endofminecraft.server.block.entity.EndOfMinecraftBlockEntities;
import willatendo.simplelibrary.server.block.BlockHelper;

public class AnomalyStoneBlock extends HorizontalDirectionalBlock implements EntityBlock {
	public static final MapCodec<AnomalyStoneBlock> CODEC = AnomalyStoneBlock.simpleCodec(AnomalyStoneBlock::new);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

	public AnomalyStoneBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVE, false));
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null : BlockHelper.createTickerHelper(blockEntityType, EndOfMinecraftBlockEntities.ANOMALY_STONE.get(), AnomalyStoneBlockEntity::serverTick);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		Direction facing = blockState.getValue(FACING);
		BlockPos portalPos = blockPos.relative(facing, 5);

		if (level.getBlockState(portalPos).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get())) {
			this.fillPortal(level, facing, portalPos, Blocks.AIR.defaultBlockState());
			level.setBlock(blockPos, blockState.setValue(ACTIVE, false), 3);
			return InteractionResult.SUCCESS;
		} else {
			if (this.hasFrame(level, facing, portalPos)) {
				Axis portalAxis = (blockState.getValue(FACING) == Direction.EAST || blockState.getValue(FACING) == Direction.WEST) ? Axis.Z : Axis.X;
				this.fillPortal(level, facing, portalPos, EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get().defaultBlockState().setValue(PlanetAlphaPortalBlock.AXIS, portalAxis));
				level.setBlock(blockPos, blockState.setValue(ACTIVE, true), 3);
				return InteractionResult.SUCCESS;
			} else {
				return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
			}
		}
	}

	private boolean hasFrame(Level level, Direction facing, BlockPos portalPos) {
		Direction right = facing == Direction.SOUTH ? Direction.WEST : facing == Direction.NORTH ? Direction.EAST : facing == Direction.EAST ? Direction.SOUTH : Direction.NORTH;
		return (level.getBlockState(portalPos.above(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right).above(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right, 2).above(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right, 2).above()).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right, 2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right, 2).below()).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right, 2).below(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite()).above(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite(), 2).above(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite(), 2).above()).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite(), 2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite(), 2).below()).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite(), 2).below(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.below(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right).below(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()) && level.getBlockState(portalPos.relative(right.getOpposite()).below(2)).is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get()));
	}

	private void fillPortal(Level level, Direction facing, BlockPos portalPos, BlockState toFillWith) {
		level.setBlock(portalPos.above(), toFillWith, 3);
		level.setBlock(portalPos, toFillWith, 3);
		level.setBlock(portalPos.below(), toFillWith, 3);

		Direction right = facing == Direction.SOUTH ? Direction.WEST : facing == Direction.NORTH ? Direction.EAST : facing == Direction.EAST ? Direction.SOUTH : Direction.NORTH;
		level.setBlock(portalPos.relative(right).above(), toFillWith, 3);
		level.setBlock(portalPos.relative(right), toFillWith, 3);
		level.setBlock(portalPos.relative(right).below(), toFillWith, 3);
		level.setBlock(portalPos.relative(right.getOpposite()).above(), toFillWith, 3);
		level.setBlock(portalPos.relative(right.getOpposite()), toFillWith, 3);
		level.setBlock(portalPos.relative(right.getOpposite()).below(), toFillWith, 3);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, ACTIVE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	protected MapCodec<AnomalyStoneBlock> codec() {
		return CODEC;
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return blockState.getValue(ACTIVE) ? RenderShape.ENTITYBLOCK_ANIMATED : RenderShape.MODEL;
	}

	@Override
	public boolean triggerEvent(BlockState blockState, Level level, BlockPos blockPos, int i, int j) {
		super.triggerEvent(blockState, level, blockPos, i, j);
		BlockEntity blockEntity = level.getBlockEntity(blockPos);
		return blockEntity != null && blockEntity.triggerEvent(i, j);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new AnomalyStoneBlockEntity(blockPos, blockState);
	}
}
