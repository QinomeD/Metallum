package qinomed.metallum.item.weapons;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.world.LodestoneWorldParticleRenderType;

import java.awt.*;

public class DimensionShredderItem extends MetallumGlaiveItem {
    private static final Color RIFT_BLUE = Color.BLUE.brighter().brighter();
    private static final Color RIFT_PURPLE = new Color(0x7a00d1).brighter();

    public DimensionShredderItem(Tier tier, float attackDamage, float attackSpeed, Properties builderIn) {
        super(tier, attackDamage, attackSpeed, builderIn);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        Level level = entity.level;
        Vec3 look = entity.getViewVector(1);
        Vec3 pos = new Vec3(entity.getX(), entity.getY() + entity.getEyeHeight(), entity.getZ());

        Vec3 middle = look.multiply(1.5, 1, 1.5).add(pos);
        Vec3 unit = normalize2D(look);

        Vec3 start = middle.add(unit.yRot(90 * Mth.DEG_TO_RAD));
        Vec3 end = middle.add(unit.yRot(-90 * Mth.DEG_TO_RAD));

        drawRift(level, start, end, 40);

        return super.onEntitySwing(stack, entity);
    }

    private static void drawRift(Level level, Vec3 start, Vec3 end, int particleCount) {
        Vec3 direction = start.subtract(end).normalize();
        double d = start.distanceTo(end) / -particleCount;

        for (int i = 0; i < particleCount; i += 1) {
            Vec3 pos = start.add(direction.multiply(i*d, i*d, i*d));
            spawnRiftParticle(level, pos);
        }
    }

    private static void spawnRiftParticle(Level level, Vec3 pos) {
        spawnRiftParticle(level, pos.x, pos.y, pos.z);
    }

    private static void spawnRiftParticle(Level level, double x, double y, double z) {
        ColorParticleData.ColorParticleDataBuilder colorBuilder = ColorParticleData.create(RIFT_BLUE, RIFT_PURPLE).setCoefficient(2);

        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                .setTransparencyData(GenericParticleData.create(1, 0).setEasing(Easing.SINE_IN, Easing.SINE_OUT).build())
                .setLifetime(40)
                .setScaleData(GenericParticleData.create(0.5f).build())
                .setColorData(colorBuilder.build())
                .setRandomOffset(0.1d)
                .enableNoClip()
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .spawn(level, x, y, z);
    }

    private static Vec3 normalize2D(Vec3 vec) {
        double magnitude = Math.sqrt((vec.x * vec.x) + (vec.z * vec.z));
        return new Vec3(vec.x/magnitude, 0, vec.z/magnitude);
    }
}
