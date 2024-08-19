package willatendo.endofminecraft.server.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willatendo.endofminecraft.client.particle.EndOfMinecraftParticleTypes;
import willatendo.endofminecraft.platform.EndOfMinecraftModloaderHelper;
import willatendo.endofminecraft.server.dimension.EndOfMinecraftDimensions;
import willatendo.endofminecraft.server.util.PlanetAlphaPortalShape;

public class PlanetAlphaPortalBlock extends Block {
    public static final MapCodec<PlanetAlphaPortalBlock> CODEC = PlanetAlphaPortalBlock.simpleCodec(PlanetAlphaPortalBlock::new);
    public static final EnumProperty<Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final int SHAPE_OFFSET = 2;
    protected static final VoxelShape X_AXIS_SHAPE = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AXIS_SHAPE = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public PlanetAlphaPortalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Axis.X));
    }

    @Override
    public MapCodec<PlanetAlphaPortalBlock> codec() {
        return CODEC;
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
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if (!entity.level().isClientSide() && !blockPos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = blockPos.immutable();
                }

                if (entity.level() instanceof ServerLevel serverLevel) {
                    MinecraftServer minecraftserver = serverLevel.getServer();
                    ResourceKey<Level> key = entity.level().dimension() == EndOfMinecraftDimensions.PLANET_ALPHA ? Level.OVERWORLD : EndOfMinecraftDimensions.PLANET_ALPHA;
                    ServerLevel endLevel = minecraftserver.getLevel(key);
                    if (endLevel != null && !entity.isPassenger()) {
                        entity.setPortalCooldown();
                        double x = entity.position().x();
                        double z = entity.position().z();
                        double y = serverLevel.getHeight(Heightmap.Types.WORLD_SURFACE, (int) x, (int) z);
                        double finalY = y > -64.0D ? y : 70;
                        EndOfMinecraftModloaderHelper.INSTANCE.changeDimensions(entity, endLevel, new PortalInfo(new Vec3(x, finalY, z), Vec3.ZERO, entity.getYRot(), entity.getXRot()));
                    }
                }
            }
        }
        super.entityInside(blockState, level, blockPos, entity);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState newBlockState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos newBlockPos) {
        Direction.Axis axis = direction.getAxis();
        Direction.Axis blockAxis = blockState.getValue(AXIS);
        boolean bl = blockAxis != axis && axis.isHorizontal();
        if (bl || newBlockState.is(this) || new PlanetAlphaPortalShape(levelAccessor, blockPos, blockAxis).isComplete()) {
            return super.updateShape(blockState, direction, newBlockState, levelAccessor, blockPos, newBlockPos);
        }
        return Blocks.AIR.defaultBlockState();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(100) == 0) {
            level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, randomSource.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double d0 = (double) blockPos.getX() + randomSource.nextDouble();
            double d1 = (double) blockPos.getY() + randomSource.nextDouble();
            double d2 = (double) blockPos.getZ() + randomSource.nextDouble();
            double d3 = ((double) randomSource.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double) randomSource.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double) randomSource.nextFloat() - 0.5D) * 0.5D;
            int j = randomSource.nextInt(2) * 2 - 1;
            if (!level.getBlockState(blockPos.west()).is(this) && !level.getBlockState(blockPos.east()).is(this)) {
                d0 = (double) blockPos.getX() + 0.5D + 0.25D * (double) j;
                d3 = (double) (randomSource.nextFloat() * 2.0F * (float) j);
            } else {
                d2 = (double) blockPos.getZ() + 0.5D + 0.25D * (double) j;
                d5 = (double) (randomSource.nextFloat() * 2.0F * (float) j);
            }

            level.addParticle(EndOfMinecraftParticleTypes.PLANET_ALPHA_PORTAL_PARTICLE.get(), d0, d1, d2, d3, d4, d5);
        }

        super.animateTick(blockState, level, blockPos, randomSource);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
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
