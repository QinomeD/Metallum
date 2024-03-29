package qinomed.metallum.datagen;

import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import qinomed.metallum.item.MetallumItems;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class MetallumRecipes extends RecipeProvider implements IConditionBuilder {
    public MetallumRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Metallum Recipe Provider";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shaped(MetallumItems.HALLOWED_FRAME.get())
                .define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get()))
                .save(consumer);

        shaped(MetallumItems.CRUDE_GLAIVE.get())
                .define('/', Items.STICK).define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('o', ItemRegistry.PROCESSED_SOULSTONE.get())
                .pattern(" ##")
                .pattern("#/o")
                .pattern("/  ")
                .unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(MetallumItems.DELVER_WEAVE.get())
                .requires(ItemRegistry.ESOTERIC_SPOOL.get())
                .requires(Items.DEEPSLATE)
                .unlockedBy("has_spool", has(ItemRegistry.ESOTERIC_SPOOL.get()))
                .save(consumer);
    }
}
