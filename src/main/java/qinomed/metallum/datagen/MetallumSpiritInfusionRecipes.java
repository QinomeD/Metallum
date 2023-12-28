package qinomed.metallum.datagen;

import com.sammy.malum.data.recipe.builder.SpiritInfusionRecipeBuilder;
import com.sammy.malum.registry.common.SpiritTypeRegistry;
import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import qinomed.metallum.item.MetallumItems;

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

    }

    private static void clusterTool(Item inTool, Item outTool, Item node, Consumer<FinishedRecipe> consumer) {
        new SpiritInfusionRecipeBuilder(inTool, 1, outTool, 1)
                .addExtraItem(node, 6)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 8)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 8)
                .build(consumer);
    }
}
