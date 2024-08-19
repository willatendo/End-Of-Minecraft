package willatendo.endofminecraft.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import willatendo.endofminecraft.server.block.EndOfMinecraftBlocks;
import willatendo.endofminecraft.server.item.EndOfMinecraftItems;

import java.util.Locale;
import java.util.Objects;

public class EndOfMinecraftItemModelProvider extends ItemModelProvider {
    public EndOfMinecraftItemModelProvider(PackOutput packOutput, String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicBlock(EndOfMinecraftBlocks.ANOMALY_STONE.get());
        this.basicBlock(EndOfMinecraftBlocks.ANOMALY_BRICKS.get());

        for (int i = 0; i < 32; i++) {
            if (i == 16)
                continue;
            ModelFile model = this.getExistingFile(this.mcLoc("item/generated"));
            this.getBuilder(EndOfMinecraftItems.ANOMALY_DETECTOR.getId() + String.format(Locale.ROOT, "_%02d", i)).parent(model).texture("layer0", "item/" + EndOfMinecraftItems.ANOMALY_DETECTOR.getId().getPath() + String.format(Locale.ROOT, "_%02d", i));
        }
    }

    public ItemModelBuilder handheldItem(Item item) {
        return this.handheldItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), new ResourceLocation(BuiltInRegistries.ITEM.getKey(item).getNamespace(), "item/" + BuiltInRegistries.ITEM.getKey(item).getPath()));
    }

    public ItemModelBuilder handheldItem(Item item, ResourceLocation texture) {
        return this.handheldItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), texture);
    }

    public ItemModelBuilder basicItem(Item item, ResourceLocation texture) {
        return this.basicItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), texture);
    }

    public ItemModelBuilder basicItem(ResourceLocation item, ResourceLocation texture) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", texture);
    }

    public ItemModelBuilder handheldItem(ResourceLocation item, ResourceLocation texture) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", texture);
    }

    public ItemModelBuilder handheldItem(Item item, ResourceLocation[] textures) {
        return this.handheldItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), textures);
    }

    public ItemModelBuilder basicItem(Item item, ResourceLocation[] textures) {
        return this.basicItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), textures);
    }

    public ItemModelBuilder basicItem(ResourceLocation item, ResourceLocation[] textures) {
        ItemModelBuilder itemModelBuilder = this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/generated"));
        for (int i = 0; i < textures.length; i++) {
            itemModelBuilder.texture("layer" + i, textures[i]);
        }
        return itemModelBuilder;
    }

    public ItemModelBuilder handheldItem(ResourceLocation item, ResourceLocation[] textures) {
        ItemModelBuilder itemModelBuilder = this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld"));
        for (int i = 0; i < textures.length; i++) {
            itemModelBuilder.texture("layer" + i, textures[i]);
        }
        return itemModelBuilder;
    }

    public ItemModelBuilder spawnEggItem(Item item) {
        return this.spawnEggItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder spawnEggItem(ResourceLocation item) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
    }

    public void basicBlock(Block block) {
        this.getBuilder(BuiltInRegistries.BLOCK.getKey(block).getPath()).parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block).getPath())));
    }
}
