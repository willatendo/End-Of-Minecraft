package willatendo.endofminecraft.server.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;

public class EndOfMinecraftDamageTypes {
    public static final ResourceKey<DamageType> IRRADIATION = register("irradiation");

    public static ResourceKey<DamageType> register(String string) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, EndOfMinecraftUtils.resource(string));
    }


    public static void bootstrap(BootstapContext<DamageType> bootstapContext) {
        bootstapContext.register(IRRADIATION, new DamageType("irradiation", 0.0F));
    }
}
