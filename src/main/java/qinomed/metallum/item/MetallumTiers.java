package qinomed.metallum.item;

import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class MetallumTiers {
    public static final ForgeTier IRON_CLUSTER = new ForgeTier(3, 700, 8.0f,
            3.0f, 18, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemRegistry.IRON_NODE.get()));

    public static final ForgeTier GOLD_CLUSTER = new ForgeTier(2, 270, 14.0f,
            1.0f, 24, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.GOLD_NODE.get()));
}
