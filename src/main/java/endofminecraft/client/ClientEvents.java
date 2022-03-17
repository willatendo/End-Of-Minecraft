package endofminecraft.client;

//@EventBusSubscriber(modid = EndOfMinecraftMod.ID, value = Dist.CLIENT)
public class ClientEvents {
//	@SubscribeEvent
//	public static void renderOverlay(RenderGameOverlayEvent.PreLayer event) {
//		var screenWidth = event.getWindow().getGuiScaledWidth();
//		var screenHeight = event.getWindow().getGuiScaledHeight();
//		var player = Minecraft.getInstance().player;
//		if (Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
//			if (player.getItemBySlot(EquipmentSlot.HEAD).is(EndRegistry.RADIATION_SUIT_HELMET.get())) {
//				RenderSystem.disableDepthTest();
//				RenderSystem.depthMask(false);
//				RenderSystem.defaultBlendFunc();
//				RenderSystem.setShader(GameRenderer::getPositionTexShader);
//				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//				RenderSystem.setShaderTexture(0, EndOfMinecraftMod.UTILS.resource("textures/gui/radiation_helmet.png"));
//				Tesselator tesselator = Tesselator.getInstance();
//				BufferBuilder bufferbuilder = tesselator.getBuilder();
//				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
//				bufferbuilder.vertex(0.0D, (double) screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
//				bufferbuilder.vertex((double) screenWidth, (double) screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
//				bufferbuilder.vertex((double) screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
//				bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
//				tesselator.end();
//				RenderSystem.depthMask(true);
//				RenderSystem.enableDepthTest();
//				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//			}
//		}
//	}
}
