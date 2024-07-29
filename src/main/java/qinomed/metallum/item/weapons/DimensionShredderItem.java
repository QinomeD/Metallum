package qinomed.metallum.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import com.sammy.malum.visual_effects.networked.data.PositionEffectData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import qinomed.metallum.vfx.ParticleEffectsRegistry;
import qinomed.metallum.vfx.networked.SlashParticleEffect;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributeRegistry;

import java.awt.*;

public class DimensionShredderItem extends MetallumGlaiveItem {
    private static final Color RIFT_BLUE = Color.BLUE.brighter().brighter();
    private static final Color RIFT_PURPLE = new Color(0x7a00d1).brighter();
    public float magicDamage;

    public DimensionShredderItem(Tier tier, float attackDamage, float attackSpeed, float magicDamage, Item.Properties builderIn) {
        super(tier, attackDamage, attackSpeed, builderIn);
        this.magicDamage = magicDamage;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        Level level = entity.level();
        RandomSource random = level.random;

        if (player.getAttackStrengthScale(1) >= 1) {
            if (!level.isClientSide) {
                Vec3 look = player.getViewVector(1);
                Vec3 unit = normalize2D(look);
                Vec3 pos = new Vec3(entity.getX(), entity.getY() + entity.getBbHeight()/2, entity.getZ());

                // random angle
                int angle = RandomHelper.randomBetween(random, -20, 20);
                double side = (2 * Math.tan(angle * Mth.DEG_TO_RAD))/2;

                // random offset
                Vec3 randomOffset = new Vec3(
                        RandomHelper.randomBetween(random, -0.5, 0.5),
                        RandomHelper.randomBetween(random, -0.5, 0.5),
                        RandomHelper.randomBetween(random, -0.5, 0.5));

                Vec3 start = pos.add(unit.yRot(90 * Mth.DEG_TO_RAD)).subtract(0, side, 0).add(randomOffset);
                Vec3 end = pos.add(unit.yRot(-90 * Mth.DEG_TO_RAD)).add(0, side, 0).add(randomOffset);

                drawSlash(level, start, end, unit);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ImmutableMultimap.Builder<Attribute, AttributeModifier> createExtraAttributes() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiers = new ImmutableMultimap.Builder<>();
        modifiers.put(LodestoneAttributeRegistry.MAGIC_DAMAGE.get(), new AttributeModifier(LodestoneAttributeRegistry.UUIDS.get(LodestoneAttributeRegistry.MAGIC_DAMAGE), "Weapon magic damage", magicDamage, AttributeModifier.Operation.ADDITION));
        modifiers.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier("Attack range modifier", 2, AttributeModifier.Operation.ADDITION));
        return modifiers;
    }

    private void drawSlash(Level level, Vec3 start, Vec3 end, Vec3 look) {
        ParticleEffectsRegistry.SLASH.createPositionedEffect(level,
                new PositionEffectData(look),
                new ColorEffectData(RIFT_BLUE, RIFT_PURPLE),
                SlashParticleEffect.createData(start, end));

        /*Vec3 direction = start.subtract(end).normalize();
        double d = start.distanceTo(end) / -density;

        for (int i = 0; i < density; i += 1) {
            double factor = Easing.EXPO_IN_OUT.ease(i, 0, 0.5f, density);
            Vec3 pos = start
                    .add(direction.multiply(i*d, i*d, i*d)) // step
                    .add(look.multiply(factor, factor, factor)); // easing
            ParticleEffectsRegistry.SLASH.createPositionedEffect(level, new PositionEffectData(pos), new ColorEffectData(RIFT_BLUE, RIFT_PURPLE));
        }*/
    }

    private static Vec3 normalize2D(Vec3 vec) {
        double magnitude = Math.sqrt((vec.x * vec.x) + (vec.z * vec.z));
        return new Vec3(vec.x/magnitude, 0, vec.z/magnitude);
    }
}
