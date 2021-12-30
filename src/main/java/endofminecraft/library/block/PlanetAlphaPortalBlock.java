package endofminecraft.library.block;

import java.util.Random;

import endofminecraft.content.server.init.ParticleInit;
import endofminecraft.library.util.ModRegistry;
import endofminecraft.library.util.ModTeleporter;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PlanetAlphaPortalBlock extends Block
{
	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	
	public PlanetAlphaPortalBlock() 
	{
		super(AbstractBlock.Properties.of(Material.PORTAL).noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel((light) -> 
		{
			return 15;
		}));
	}
	
	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) 
	{
		if(!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) 
		{
			if(entity.isOnPortalCooldown()) 
			{
				entity.setPortalCooldown();
			} 
			else 
			{
				if(!entity.level.isClientSide() && !pos.equals(entity.portalEntrancePos)) 
				{
					entity.portalEntrancePos = pos.immutable();
				}
				
				if(entity.level instanceof ServerWorld) 
				{
					ServerWorld serverworld = (ServerWorld)entity.level;
					MinecraftServer minecraftserver = serverworld.getServer();
					RegistryKey<World> registrykey = entity.level.dimension() == ModRegistry.PLANET_ALPHA_WORLD ? World.OVERWORLD : ModRegistry.PLANET_ALPHA_WORLD;
					ServerWorld serverworly = minecraftserver.getLevel(registrykey);
					if(serverworly != null && !entity.isPassenger()) 
					{
						entity.setPortalCooldown();
						entity.changeDimension(serverworly, new ModTeleporter(serverworly));
					}
				}
			}
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) 
	{
		if(rand.nextInt(100) == 0) 
		{
			world.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}
		
		for(int i = 0; i < 4; ++i) 
		{
			double x = (double)pos.getX() + rand.nextDouble();
			double y = (double)pos.getY() + rand.nextDouble();
			double z = (double)pos.getZ() + rand.nextDouble();
			double xf = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double yf = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double zf = ((double)rand.nextFloat() - 0.1D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if(!world.getBlockState(pos.west()).is(this) && !world.getBlockState(pos.east()).is(this)) 
			{
				x = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				xf = (double)(rand.nextFloat() * 2.0F * (float)j);
			}
			else 
			{
				z = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				zf = (double)(rand.nextFloat() * 2.0F * (float)j);
			}
			
			world.addParticle(ParticleInit.PLANET_ALPHA_PORTAL_PARTICLE, x, y, z, xf, yf, zf);
		}
	}
}
