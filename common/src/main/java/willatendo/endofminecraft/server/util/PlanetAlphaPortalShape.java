package willatendo.endofminecraft.server.util;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;

public class PlanetAlphaPortalShape {
	private static final BlockBehaviour.StatePredicate FRAME = (blockState, blockGetter, blockPos) -> blockState.is(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());
	private final LevelAccessor levelAccessor;
	private final Direction rightDir;
	private int numPortalBlocks;
	private BlockPos bottomLeft;
	private final int height;
	private final int width;

	public PlanetAlphaPortalShape(LevelAccessor levelAccessor, BlockPos blockPos, Direction.Axis axis) {
		this.levelAccessor = levelAccessor;
		this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
		this.bottomLeft = this.calculateBottomLeft(blockPos);
		if (this.bottomLeft == null) {
			this.bottomLeft = blockPos;
			this.width = 1;
			this.height = 1;
		} else {
			this.width = 5;
			this.height = 5;
		}
	}

	@Nullable
	private BlockPos calculateBottomLeft(BlockPos blockPos) {
		int i = Math.max(this.levelAccessor.getMinBuildHeight(), blockPos.getY() - 21);
		while (blockPos.getY() > i && PlanetAlphaPortalShape.isEmpty(this.levelAccessor.getBlockState(blockPos.below()))) {
			blockPos = blockPos.below();
		}
		Direction direction = this.rightDir.getOpposite();
		int j = this.getDistanceUntilEdgeAboveFrame(blockPos, direction) - 1;
		if (j < 0) {
			return null;
		}
		return blockPos.relative(direction, j);
	}

	private int getDistanceUntilEdgeAboveFrame(BlockPos blockPos, Direction direction) {
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
		for (int i = 0; i <= 21; ++i) {
			mutableBlockPos.set(blockPos).move(direction, i);
			BlockState blockState = this.levelAccessor.getBlockState(mutableBlockPos);
			if (!PlanetAlphaPortalShape.isEmpty(blockState)) {
				if (!FRAME.test(blockState, this.levelAccessor, mutableBlockPos))
					break;
				return i;
			}
			BlockState blockState2 = this.levelAccessor.getBlockState(mutableBlockPos.move(Direction.DOWN));
			if (!FRAME.test(blockState2, this.levelAccessor, mutableBlockPos))
				break;
		}
		return 0;
	}

	private static boolean isEmpty(BlockState blockState) {
		return blockState.isAir() || blockState.is(EndOfMinecraftBlocks.PLANET_ALPHA_PORTAL.get());
	}

	public boolean isValid() {
		return this.bottomLeft != null;
	}

	public boolean isComplete() {
		return this.isValid() && this.numPortalBlocks == this.width * this.height;
	}
}
