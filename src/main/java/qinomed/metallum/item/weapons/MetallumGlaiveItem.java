package qinomed.metallum.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.sammy.malum.common.item.curiosities.weapons.MalumScytheItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.ForgeMod;

public class MetallumGlaiveItem extends MalumScytheItem {
    public MetallumGlaiveItem(Tier tier, float attackDamage, float attackSpeed, Properties builderIn) {
        super(tier, attackDamage, attackSpeed, builderIn);
    }

    @Override
    public ImmutableMultimap.Builder<Attribute, AttributeModifier> createExtraAttributes() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiers = new ImmutableMultimap.Builder<>();
        modifiers.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier("Attack range modifier", 2, AttributeModifier.Operation.ADDITION));
        return modifiers;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.category == EnchantmentCategory.WEAPON;
    }
}
