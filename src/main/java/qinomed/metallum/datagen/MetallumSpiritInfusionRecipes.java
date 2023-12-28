package qinomed.metallum.datagen;

import com.sammy.malum.data.recipe.builder.SpiritInfusionRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class MetallumSpiritInfusionRecipes extends RecipeProvider implements IConditionBuilder {
    public MetallumSpiritInfusionRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Metallum Spirit Infusion Recipe Provider";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

    }
}
