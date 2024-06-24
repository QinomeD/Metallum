package qinomed.metallum.datagen;

import com.sammy.malum.data.recipe.builder.SpiritInfusionRecipeBuilder;
import com.sammy.malum.registry.common.SpiritTypeRegistry;
import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import qinomed.metallum.item.MetallumItems;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class MetallumRecipes extends RecipeProvider implements IConditionBuilder {
    public MetallumRecipes(DataGenerator generator) {
        super(generator.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        shaped(RecipeCategory.MISC, MetallumItems.HALLOWED_FRAME.get())
                .define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get()))
                .save(consumer);

        shaped(RecipeCategory.COMBAT, MetallumItems.CRUDE_GLAIVE.get())
                .define('/', Items.STICK).define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('o', ItemRegistry.PROCESSED_SOULSTONE.get())
                .pattern(" ##")
                .pattern("#/o")
                .pattern("/  ")
                .unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MetallumItems.DELVER_WEAVE.get())
                .requires(ItemRegistry.ESOTERIC_SPOOL.get())
                .requires(Items.DEEPSLATE)
                .unlockedBy("has_spool", has(ItemRegistry.ESOTERIC_SPOOL.get()))
                .save(consumer);

        clusterTool(Items.IRON_SWORD, MetallumItems.IRON_CLUSTER_SWORD.get(), ItemRegistry.IRON_NODE.get(), consumer);
        clusterTool(Items.IRON_PICKAXE, MetallumItems.IRON_CLUSTER_PICKAXE.get(), ItemRegistry.IRON_NODE.get(), consumer);
        clusterTool(Items.IRON_AXE, MetallumItems.IRON_CLUSTER_AXE.get(), ItemRegistry.IRON_NODE.get(), consumer);
        clusterTool(Items.IRON_SHOVEL, MetallumItems.IRON_CLUSTER_SHOVEL.get(), ItemRegistry.IRON_NODE.get(), consumer);
        clusterTool(Items.IRON_HOE, MetallumItems.IRON_CLUSTER_HOE.get(), ItemRegistry.IRON_NODE.get(), consumer);

        clusterTool(Items.GOLDEN_SWORD, MetallumItems.GOLDEN_CLUSTER_SWORD.get(), ItemRegistry.GOLD_NODE.get(), consumer);
        clusterTool(Items.GOLDEN_PICKAXE, MetallumItems.GOLDEN_CLUSTER_PICKAXE.get(), ItemRegistry.GOLD_NODE.get(), consumer);
        clusterTool(Items.GOLDEN_AXE, MetallumItems.GOLDEN_CLUSTER_AXE.get(), ItemRegistry.GOLD_NODE.get(), consumer);
        clusterTool(Items.GOLDEN_SHOVEL, MetallumItems.GOLDEN_CLUSTER_SHOVEL.get(), ItemRegistry.GOLD_NODE.get(), consumer);
        clusterTool(Items.GOLDEN_HOE, MetallumItems.GOLDEN_CLUSTER_HOE.get(), ItemRegistry.GOLD_NODE.get(), consumer);

        new SpiritInfusionRecipeBuilder(MetallumItems.HALLOWED_FRAME.get(), 1, MetallumItems.REAGENT_PENDANT.get(), 1)
                .addExtraItem(Items.STRING, 3)
                .addExtraItem(ItemRegistry.ALCHEMICAL_CALX.get(), 6)
                .addExtraItem(ItemRegistry.CTHONIC_GOLD.get(), 1)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 4)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 4)
                .build(consumer);

        new SpiritInfusionRecipeBuilder(MetallumItems.HALLOWED_FRAME.get(), 1, MetallumItems.REVERBERATION_IMPLANT.get(), 1)
                .addExtraItem(Ingredient.of(Tags.Items.GEMS_QUARTZ), 2)
                .addExtraItem(ItemRegistry.CLUSTER_OF_BRILLIANCE.get(), 1)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 4)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 2)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 2)
                .build(consumer);
    }

    private static void clusterTool(Item inTool, Item outTool, Item node, Consumer<FinishedRecipe> consumer) {
        new SpiritInfusionRecipeBuilder(inTool, 1, outTool, 1)
                .addExtraItem(node, 6)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 8)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 8)
                .build(consumer);
    }
}
