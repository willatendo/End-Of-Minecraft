package endofminecraft.content.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import endofminecraft.content.client.ToFixBlockEntityRenderer;
import endofminecraft.content.client.model.AnomalyStoneModel;
import endofminecraft.library.tileentity.AnomalyStoneTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnomalyStoneRenderer extends ToFixBlockEntityRenderer<AnomalyStoneTileEntity> {
	public AnomalyStoneRenderer(BlockEntityRendererProvider.Context context) {
		super(context, new AnomalyStoneModel());
	}

	@Override
	public RenderType getRenderType(AnomalyStoneTileEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
