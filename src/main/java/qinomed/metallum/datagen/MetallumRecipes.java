package qinomed.metallum.datagen;

import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
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
    }
}
