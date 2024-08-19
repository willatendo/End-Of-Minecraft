package willatendo.endofminecraft.client.dimension;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.client.event.SkyRenderer;

public class PlanetAlphaSkyRenderer {
    private static final ResourceLocation PLANET_ALPHA_MOON_LOCATION = EndOfMinecraftUtils.resource("textures/environment/planet_alpha_moon_1_phases.png");
    private static final ResourceLocation PLANET_ALPHA_SUN_LOCATION = EndOfMinecraftUtils.resource("textures/environment/planet_alpha_suns.png");

    public static void renderSky(SkyRenderer skyRenderer) {
        renderSky(skyRenderer.levelRenderer(), skyRenderer.poseStack(), skyRenderer.getProjectionMatrix(), skyRenderer.tickDelta(), skyRenderer.getCamera());
    }

    private static void renderSky(LevelRenderer levelRenderer, PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera) {
        float r;
        float q;
        float p;
        int n;
        float l;
        float j;
        FogType fogType = camera.getFluidInCamera();
        if (fogType == FogType.POWDER_SNOW || fogType == FogType.LAVA || levelRenderer.doesMobEffectBlockSky(camera)) {
            return;
        }
        Vec3 vec3 = levelRenderer.level.getSkyColor(levelRenderer.minecraft.gameRenderer.getMainCamera().getPosition(), f);
        float g = (float) vec3.x;
        float h = (float) vec3.y;
        float i = (float) vec3.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(g, h, i, 1.0f);
        ShaderInstance shaderInstance = RenderSystem.getShader();
        levelRenderer.skyBuffer.bind();
        levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), matrix4f, shaderInstance);
        VertexBuffer.unbind();
        RenderSystem.enableBlend();
        float[] fs = levelRenderer.level.effects().getSunriseColor(levelRenderer.level.getTimeOfDay(f), f);
        if (fs != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
            j = Mth.sin(levelRenderer.level.getSunAngle(f)) < 0.0f ? 180.0f : 0.0f;
            poseStack.mulPose(Axis.ZP.rotationDegrees(j));
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0f));
            float k = fs[0];
            l = fs[1];
            float m = fs[2];
            Matrix4f matrix4f2 = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferBuilder.vertex(matrix4f2, 0.0f, 100.0f, 0.0f).color(k, l, m, fs[3]).endVertex();
            n = 16;
            for (int o = 0; o <= 16; ++o) {
                p = (float) o * ((float) Math.PI * 2) / 16.0f;
                q = Mth.sin(p);
                r = Mth.cos(p);
                bufferBuilder.vertex(matrix4f2, q * 120.0f, r * 120.0f, -r * 40.0f * fs[3]).color(fs[0], fs[1], fs[2], 0.0f).endVertex();
            }
            BufferUploader.drawWithShader(bufferBuilder.end());
            poseStack.popPose();
        }
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        poseStack.pushPose();
        j = 1.0f - levelRenderer.level.getRainLevel(f);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, j);
        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(levelRenderer.level.getTimeOfDay(f) * 360.0f));
        Matrix4f matrix4f3 = poseStack.last().pose();
        l = 30.0f;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, PLANET_ALPHA_SUN_LOCATION);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f3, -l, 100.0f, -l).uv(0.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, l, 100.0f, -l).uv(1.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, l, 100.0f, l).uv(1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f3, -l, 100.0f, l).uv(0.0f, 1.0f).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        l = 20.0f;
        RenderSystem.setShaderTexture(0, PLANET_ALPHA_MOON_LOCATION);
        int s = levelRenderer.level.getMoonPhase();
        int t = s % 4;
        n = s / 4 % 2;
        float u = (float) (t + 0) / 4.0f;
        p = (float) (n + 0) / 2.0f;
        q = (float) (t + 1) / 4.0f;
        r = (float) (n + 1) / 2.0f;
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f3, -l, -100.0f, l).uv(q, r).endVertex();
        bufferBuilder.vertex(matrix4f3, l, -100.0f, l).uv(u, r).endVertex();
        bufferBuilder.vertex(matrix4f3, l, -100.0f, -l).uv(u, p).endVertex();
        bufferBuilder.vertex(matrix4f3, -l, -100.0f, -l).uv(q, p).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        float v = levelRenderer.level.getStarBrightness(f) * j;
        if (v > 0.0f) {
            RenderSystem.setShaderColor(v, v, v, v);
            FogRenderer.setupNoFog();
            levelRenderer.starBuffer.bind();
            levelRenderer.starBuffer.drawWithShader(poseStack.last().pose(), matrix4f, GameRenderer.getPositionShader());
            VertexBuffer.unbind();
        }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        poseStack.popPose();
        RenderSystem.setShaderColor(0.0f, 0.0f, 0.0f, 1.0f);
        double d = levelRenderer.minecraft.player.getEyePosition((float) f).y - levelRenderer.level.getLevelData().getHorizonHeight(levelRenderer.level);
        if (d < 0.0) {
            poseStack.pushPose();
            poseStack.translate(0.0f, 12.0f, 0.0f);
            levelRenderer.darkBuffer.bind();
            levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), matrix4f, shaderInstance);
            VertexBuffer.unbind();
            poseStack.popPose();
        }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.depthMask(true);
    }
}
