package willatendo.endofminecraft.server.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import willatendo.endofminecraft.server.block.AnomalyStoneBlock;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;

public class AnomalyStoneBlockEntity extends BlockEntity {
	public AnomalyStoneBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(EndOfMinecraftBlockEntities.ANOMALY_STONE.get(), blockPos, blockState);
	}

	public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, AnomalyStoneBlockEntity anomalyStoneBlockEntity) {
		Direction facing = blockState.getValue(AnomalyStoneBlock.FACING);
		if (!anomalyStoneBlockEntity.portalExists(level, blockPos, facing)) {
			level.setBlock(blockPos, EndOfMinecraftBlocks.ANOMALY_STONE.get().defaultBlockState().setValue(AnomalyStoneBlock.FACING, facing).setValue(AnomalyStoneBlock.ACTIVE, false), 3);
		} else {
			level.setBlock(blockPos, EndOfMinecraftBlocks.ANOMALY_STONE.get().defaultBlockState().setValue(AnomalyStoneBlock.FACING, facing).setValue(AnomalyStoneBlock.ACTIVE, true), 3);
		}
	}

	public boolean portalExists(Level level, BlockPos blockPos, Direction facing) {
		BlockPos portalPos = blockPos.relative(facing, 5);
		Direction right = facing == Direction.SOUTH ? Direction.WEST : facing == Direction.NORTH ? Direction.EAST : facing == Direction.EAST ? Direction.SOUTH : Direction.NORTH;
		boolean portal1 = level.getBlockState(portalPos.above()).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal2 = level.getBlockState(portalPos).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal3 = level.getBlockState(portalPos.below()).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal4 = level.getBlockState(portalPos.relative(right).above()).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal5 = level.getBlockState(portalPos.relative(right)).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal6 = level.getBlockState(portalPos.relative(right).below()).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal7 = level.getBlockState(portalPos.relative(right.getOpposite()).above()).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal8 = level.getBlockState(portalPos.relative(right.getOpposite())).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		boolean portal9 = level.getBlockState(portalPos.relative(right.getOpposite()).below()).is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
		return portal1 && portal2 && portal3 && portal4 && portal5 && portal6 && portal7 && portal8 && portal9;
	}
}
