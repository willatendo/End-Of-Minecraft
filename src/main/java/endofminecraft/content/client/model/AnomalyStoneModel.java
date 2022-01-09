package endofminecraft.content.client.model;

import endofminecraft.library.tileentity.AnomalyStoneTileEntity;
import endofminecraft.library.util.ModUtils;
import net.minecraft.util.ResourceLocation;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

public class AnomalyStoneModel extends TyrannomatedTyrannomationModel<AnomalyStoneTileEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(AnomalyStoneTileEntity animatable) {
		return ModUtils.rL("animations/anomaly_stone.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(AnomalyStoneTileEntity animatable) {
		return ModUtils.rL("geo/anomaly_stone.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(AnomalyStoneTileEntity entity) {
		return ModUtils.rL("textures/blocks/anomaly_stone.png");
	}
}
