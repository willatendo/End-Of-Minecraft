package endofminecraft.server.block;

import java.util.Random;

import endofminecraft.server.EndRegistry;
import endofminecraft.server.world.EndTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlanetAlphaPortalBlock extends Block {
	public static final EnumProperty<Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final int SHAPE_OFFSET = 2;
	protected static final VoxelShape X_AXIS_SHAPE = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AXIS_SHAPE = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public PlanetAlphaPortalBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Axis.X));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		switch ((Axis) state.getValue(AXIS)) {
		case Z:
			return Z_AXIS_SHAPE;
		case X:
		default:
			return X_AXIS_SHAPE;
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
			if (entity.isOnPortalCooldown()) {
				entity.setPortalCooldown();
			} else {
				if (!entity.level.isClientSide() && !pos.equals(entity.portalEntrancePos)) {
					entity.portalEntrancePos = pos.immutable();
				}

				if (entity.level instanceof ServerLevel serverLevel) {
					MinecraftServer minecraftserver = serverLevel.getServer();
					ResourceKey<Level> key = entity.level.dimension() == EndRegistry.PLANET_ALPHA ? Level.OVERWORLD : EndRegistry.PLANET_ALPHA;
					ServerLevel serverlevel = minecraftserver.getLevel(key);
					if (serverlevel != null && !entity.isPassenger()) {
						entity.setPortalCooldown();
						entity.changeDimension(serverlevel, new EndTeleporter(serverlevel));
					}
				}
			}
		}

		super.entityInside(state, level, pos, entity);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
		if (rand.nextInt(100) == 0) {
			level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for (int i = 0; i < 4; ++i) {
			double d0 = (double) pos.getX() + rand.nextDouble();
			double d1 = (double) pos.getY() + rand.nextDouble();
			double d2 = (double) pos.getZ() + rand.nextDouble();
			double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
				d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
			} else {
				d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
				d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
			}

			level.addParticle((SimpleParticleType) EndRegistry.PLANET_ALPHA_PORTAL_PARTICLE.get(), d0, d1, d2, d3, d4, d5);
		}

		super.animateTick(state, level, pos, rand);
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
		return ItemStack.EMPTY;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		switch (rotation) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch ((Axis) state.getValue(AXIS)) {
			case Z:
				return state.setValue(AXIS, Axis.X);
			case X:
				return state.setValue(AXIS, Axis.Z);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
		super.createBlockStateDefinition(builder);
	}
}
