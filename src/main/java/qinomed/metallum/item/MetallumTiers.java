package qinomed.metallum.item;

import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.registries.RegistryObject;

public class MetallumTiers {
    public static final ForgeTier
            IRON_CLUSTER = tier(3, 700, 8, 3, 18, BlockTags.NEEDS_IRON_TOOL, ItemRegistry.IRON_NODE),
            GOLD_CLUSTER = tier(2, 270, 14, 1, 24, BlockTags.NEEDS_IRON_TOOL, ItemRegistry.GOLD_NODE),
            HALLOWED_GOLD = tier(2, 350, 16, 2, 24, BlockTags.NEEDS_IRON_TOOL, ItemRegistry.HALLOWED_GOLD_INGOT),
            DIMENSION_SHREDDER = tier(2, 500, 16, 3, 24, BlockTags.NEEDS_IRON_TOOL, ItemRegistry.SPECTRAL_LENS);

    private static ForgeTier tier(int level, int uses, float speed, float dmgBonus, int enchValue, TagKey<Block> tag, RegistryObject<Item> repairItem) {
        return new ForgeTier(level, uses, speed, dmgBonus, enchValue, tag, () -> Ingredient.of(repairItem.get()));
    }
}
