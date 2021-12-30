package endofminecraft.library.structure;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.JunglePyramidPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AnomalyCave extends Structure<NoFeatureConfig>
{
	public AnomalyCave(Codec<NoFeatureConfig> codec) 
	{
		super(codec);
	}

	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() 
	{
		return AnomalyCave.Start::new;
	}
	
	@Override
	public Decoration step() 
	{
		return Decoration.SURFACE_STRUCTURES;
	}
	
	public static class Start extends StructureStart<NoFeatureConfig> 
	{
		public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) 
		{
			super(structure, chunkX, chunkZ, boundingBox, references, seed);
		}
		
		public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGenerator, TemplateManager template, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) 
		{
			JunglePyramidPiece junglepyramidpiece = new JunglePyramidPiece(this.random, chunkX * 16, chunkZ * 16);
			this.pieces.add(junglepyramidpiece);
			this.calculateBoundingBox();
		}
	}
}
