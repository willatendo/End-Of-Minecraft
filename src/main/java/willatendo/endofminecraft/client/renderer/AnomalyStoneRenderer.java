package willatendo.endofminecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import willatendo.endofminecraft.client.EndOfMinecraftModels;
import willatendo.endofminecraft.client.model.AnomalyStoneModel;
import willatendo.endofminecraft.server.block.AnomalyStoneBlock;
import willatendo.endofminecraft.server.block.entity.AnomalyStoneBlockEntity;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class AnomalyStoneRenderer implements BlockEntityRenderer<AnomalyStoneBlockEntity> {
	public static final ResourceLocation TEXTURE = EndOfMinecraftUtils.resource("textures/entity/anomaly_stone.png");
	private final AnomalyStoneModel anomalyStoneModel;

	public AnomalyStoneRenderer(Context context) {
		this.anomalyStoneModel = new AnomalyStoneModel(context.bakeLayer(EndOfMinecraftModels.ANOMALY_STONE));
	}

	@Override
	public void render(AnomalyStoneBlockEntity anomalyStoneBlockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
		BlockState blockState = anomalyStoneBlockEntity.getBlockState();
		boolean shouldRender = blockState.getValue(AnomalyStoneBlock.ACTIVE);
		if (shouldRender) {
			poseStack.pushPose();
			float rot = blockState.getValue(AnomalyStoneBlock.FACING).toYRot();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Axis.YP.rotationDegrees(-rot));
			poseStack.translate(-0.5F, -0.5F, -0.5F);
			poseStack.scale(-1.0F, -1.0F, 1.0F);
			poseStack.translate(-0.5F, -1.0F, 0.5F);
			VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(TEXTURE));
			this.anomalyStoneModel.setupAnim(partialTicks);
			this.anomalyStoneModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();
		}
	}
}
