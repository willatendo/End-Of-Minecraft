package endofminecraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.portal.PortalShape;

@Mixin(PortalShape.class)
public class PortalSizeMixin {
	@Inject(at = @At("INVOKE"), method = "calculateWidth()I", cancellable = true)
	private void calculateWidth(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
		callbackInfoReturnable.setReturnValue(0);
	}
}
