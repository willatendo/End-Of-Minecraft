package endofminecraft.library.block;

import javax.annotation.Nullable;

import endofminecraft.content.server.init.BlockInit;
import endofminecraft.content.server.init.TileEntityInit;
import endofminecraft.library.ModMaterials;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AnomalyStoneBlock extends Block {
	public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

	public AnomalyStoneBlock() {
		super(AbstractBlock.Properties.of(ModMaterials.ANOMALY_STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion().randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, Boolean.valueOf(false)));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
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
		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityInit.ANONALY_STONE_TILE_ENTITY.create();
	}

	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			world.removeBlockEntity(pos);
		}
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(ACTIVATED);
	}
}
