package endofminecraft.content.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import endofminecraft.content.client.model.AnomalyStoneModel;
import endofminecraft.library.tileentity.AnomalyStoneTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationBlockmationRenderer;

@OnlyIn(Dist.CLIENT)
public class AnomalyStoneRenderer extends TyrannomationBlockmationRenderer<AnomalyStoneTileEntity> {
	public AnomalyStoneRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn, new AnomalyStoneModel());
	}

	@Override
	public RenderType getRenderType(AnomalyStoneTileEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
