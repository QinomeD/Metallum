package qinomed.metallum.item.weapons;

import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import com.sammy.malum.visual_effects.networked.data.PositionEffectData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import qinomed.metallum.vfx.ParticleEffectsRegistry;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.systems.easing.Easing;

import java.awt.*;

public class DimensionShredderItem extends MetallumGlaiveItem {
    private static final Color RIFT_BLUE = Color.BLUE.brighter().brighter();
    private static final Color RIFT_PURPLE = new Color(0x7a00d1).brighter();

    public DimensionShredderItem(Tier tier, float attackDamage, float attackSpeed, Item.Properties builderIn) {
        super(tier, attackDamage, attackSpeed, builderIn);
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

                drawSlash(level, start, end, unit, 60);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    private void drawSlash(Level level, Vec3 start, Vec3 end, Vec3 look, int density) {
        Vec3 direction = start.subtract(end).normalize();
        double d = start.distanceTo(end) / -density;

        for (int i = 0; i < density; i += 1) {
            double factor = Easing.EXPO_IN_OUT.ease(i, 0, 0.5f, density);
            Vec3 pos = start
                    .add(direction.multiply(i*d, i*d, i*d)) // step
                    .add(look.multiply(factor, factor, factor)); // easing
            ParticleEffectsRegistry.SLASH.createPositionedEffect(level, new PositionEffectData(pos), new ColorEffectData(RIFT_BLUE, RIFT_PURPLE));
        }
    }

    private static Vec3 normalize2D(Vec3 vec) {
        double magnitude = Math.sqrt((vec.x * vec.x) + (vec.z * vec.z));
        return new Vec3(vec.x/magnitude, 0, vec.z/magnitude);
    }
}
