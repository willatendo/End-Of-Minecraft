package endofminecraft.client;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

public class PlanetAlphaSkyRenderer implements ISkyRenderHandler {
	public static final ResourceLocation SUNS_LOCATION = UTILS.mod("textures/environment/planet_alpha_suns_phases.png");
	public VertexBuffer starBuffer;
	public VertexBuffer skyBuffer;
	public VertexBuffer darkBuffer;

	public PlanetAlphaSkyRenderer() {
		this.createStars();
		this.createLightSky();
		this.createDarkSky();
	}

	private void createDarkSky() {
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		if (this.darkBuffer != null) {
			this.darkBuffer.close();
		}

		this.darkBuffer = new VertexBuffer();
		buildSkyDisc(bufferbuilder, -16.0F);
		this.darkBuffer.upload(bufferbuilder);
	}

	private void createLightSky() {
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		if (this.skyBuffer != null) {
			this.skyBuffer.close();
		}

		this.skyBuffer = new VertexBuffer();
		buildSkyDisc(bufferbuilder, 16.0F);
		this.skyBuffer.upload(bufferbuilder);
	}

	private static void buildSkyDisc(BufferBuilder buffer, float colour) {
		float f = Math.signum(colour) * 512.0F;
		RenderSystem.setShader(GameRenderer::getPositionShader);
		buffer.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
		buffer.vertex(0.0D, (double) colour, 0.0D).endVertex();

		for (int i = -180; i <= 180; i += 45) {
			buffer.vertex((double) (f * Mth.cos((float) i * ((float) Math.PI / 180F))), (double) colour, (double) (512.0F * Mth.sin((float) i * ((float) Math.PI / 180F)))).endVertex();
		}

		buffer.end();
	}

	@Override
	public void render(int ticks, float partialTick, PoseStack pose, ClientLevel level, Minecraft minecraft) {
		Matrix4f matrix4f = pose.last().pose();
		Camera camera = minecraft.gameRenderer.getMainCamera();
		FogType fogtype = camera.getFluidInCamera();
		if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
			Entity $$9 = camera.getEntity();
			if ($$9 instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity) $$9;
				if (livingentity.hasEffect(MobEffects.BLINDNESS)) {
					return;
				}
			}

			RenderSystem.disableTexture();
			Vec3 vec3 = level.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), partialTick);
			float f10 = (float) vec3.x;
			float f = (float) vec3.y;
			float f1 = (float) vec3.z;
			FogRenderer.levelFogColor();
			BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
			RenderSystem.depthMask(false);
			RenderSystem.setShaderColor(f10, f, f1, 1.0F);
			ShaderInstance shaderinstance = RenderSystem.getShader();
			this.skyBuffer.drawWithShader(pose.last().pose(), matrix4f, shaderinstance);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);
			if (afloat != null) {
				RenderSystem.setShader(GameRenderer::getPositionColorShader);
				RenderSystem.disableTexture();
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				pose.pushPose();
				pose.mulPose(Vector3f.XP.rotationDegrees(90.0F));
				float f2 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
				pose.mulPose(Vector3f.ZP.rotationDegrees(f2));
				pose.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
				float f3 = afloat[0];
				float f4 = afloat[1];
				float f5 = afloat[2];
				bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
				bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f3, f4, f5, afloat[3]).endVertex();
				for (int j = 0; j <= 16; ++j) {
					float f6 = (float) j * ((float) Math.PI * 2F) / 16.0F;
					float f7 = Mth.sin(f6);
					float f8 = Mth.cos(f6);
					bufferbuilder.vertex(matrix4f, f7 * 120.0F, f8 * 120.0F, -f8 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
				}

				bufferbuilder.end();
				BufferUploader.end(bufferbuilder);
				pose.popPose();
			}

			RenderSystem.enableTexture();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			pose.pushPose();
			float f11 = 1.0F - level.getRainLevel(partialTick);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
			pose.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
			pose.mulPose(Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));
			Matrix4f matrix4f1 = pose.last().pose();
			float f12 = 30.0F;
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.setShaderTexture(0, SUNS_LOCATION);
			int sunPhase = level.getMoonPhase();
			int phaseWidth = sunPhase % 4;
			int phaseHeight = sunPhase / 4 % 2;
			float sunPhaseUV1 = (float) (phaseWidth + 0) / 4.0F;
			float sunPhaseUV2 = (float) (phaseHeight + 0) / 2.0F;
			float sunPhaseUV3 = (float) (phaseWidth + 1) / 4.0F;
			float sunPhaseUV4 = (float) (phaseHeight + 1) / 2.0F;
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(sunPhaseUV3, sunPhaseUV4).endVertex();
			bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(sunPhaseUV1, sunPhaseUV4).endVertex();
			bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(sunPhaseUV1, sunPhaseUV2).endVertex();
			bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(sunPhaseUV3, sunPhaseUV2).endVertex();
			bufferbuilder.end();
			BufferUploader.end(bufferbuilder);
			f12 = 20.0F;
//			RenderSystem.setShaderTexture(0, MOON_LOCATION);
//			int k = level.getMoonPhase();
//			int l = k % 4;
//			int i1 = k / 4 % 2;
//			float f13 = (float) (l + 0) / 4.0F;
//			float f14 = (float) (i1 + 0) / 2.0F;
//			float f15 = (float) (l + 1) / 4.0F;
//			float f16 = (float) (i1 + 1) / 2.0F;
//			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
//			bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
//			bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
//			bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
//			bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
//			bufferbuilder.end();
//			BufferUploader.end(bufferbuilder);
			RenderSystem.disableTexture();
			float f9 = level.getStarBrightness(partialTick) * f11;
			if (f9 > 0.0F) {
				RenderSystem.setShaderColor(f9, f9, f9, f9);
				FogRenderer.setupNoFog();
				this.starBuffer.drawWithShader(pose.last().pose(), matrix4f, GameRenderer.getPositionShader());
			}

			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableBlend();
			pose.popPose();
			RenderSystem.disableTexture();
			RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
			double d0 = minecraft.player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level);
			if (d0 < 0.0D) {
				pose.pushPose();
				pose.translate(0.0D, 12.0D, 0.0D);
				this.darkBuffer.drawWithShader(pose.last().pose(), matrix4f, shaderinstance);
				pose.popPose();
			}

			if (level.effects().hasGround()) {
				RenderSystem.setShaderColor(f10 * 0.2F + 0.04F, f * 0.2F + 0.04F, f1 * 0.6F + 0.1F, 1.0F);
			} else {
				RenderSystem.setShaderColor(f10, f, f1, 1.0F);
			}

			RenderSystem.enableTexture();
			RenderSystem.depthMask(true);

		}
	}

	private void createStars() {
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		RenderSystem.setShader(GameRenderer::getPositionShader);
		if (this.starBuffer != null) {
			this.starBuffer.close();
		}

		this.starBuffer = new VertexBuffer();
		this.drawStars(bufferbuilder);
		bufferbuilder.end();
		this.starBuffer.upload(bufferbuilder);
	}

	private void drawStars(BufferBuilder buffer) {
		Random random = new Random(10842L);
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

		for (int i = 0; i < 1500; ++i) {
			double d0 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d1 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d2 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d3 = (double) (0.15F + random.nextFloat() * 0.1F);
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;
			if (d4 < 1.0D && d4 > 0.01D) {
				d4 = 1.0D / Math.sqrt(d4);
				d0 *= d4;
				d1 *= d4;
				d2 *= d4;
				double d5 = d0 * 100.0D;
				double d6 = d1 * 100.0D;
				double d7 = d2 * 100.0D;
				double d8 = Math.atan2(d0, d2);
				double d9 = Math.sin(d8);
				double d10 = Math.cos(d8);
				double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
				double d12 = Math.sin(d11);
				double d13 = Math.cos(d11);
				double d14 = random.nextDouble() * Math.PI * 2.0D;
				double d15 = Math.sin(d14);
				double d16 = Math.cos(d14);

				for (int j = 0; j < 4; ++j) {
					double d18 = (double) ((j & 2) - 1) * d3;
					double d19 = (double) ((j + 1 & 2) - 1) * d3;
					double d21 = d18 * d16 - d19 * d15;
					double d22 = d19 * d16 + d18 * d15;
					double d23 = d21 * d12 + 0.0D * d13;
					double d24 = 0.0D * d12 - d21 * d13;
					double d25 = d24 * d9 - d22 * d10;
					double d26 = d22 * d9 + d24 * d10;
					buffer.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}
	}
}