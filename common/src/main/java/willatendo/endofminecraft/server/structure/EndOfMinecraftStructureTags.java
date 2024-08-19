package willatendo.endofminecraft.server.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import willatendo.endofminecraft.server.util.EndOfMinecraftUtils;
import willatendo.simplelibrary.server.util.TagRegister;

public class EndOfMinecraftStructureTags {
    public static final TagRegister<Structure> STRUCTURE_TAGS = TagRegister.create(Registries.STRUCTURE, EndOfMinecraftUtils.ID);

    public static final TagKey<Structure> ANOMALY_DETECTOR_DETECTABLE = STRUCTURE_TAGS.register("anomaly_detector_detectable");
}
