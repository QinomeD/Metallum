package qinomed.metallum.vfx;

import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.behaviors.components.ExtrudingSparkBehaviorComponent;
import team.lodestar.lodestone.systems.particle.world.options.WorldParticleOptions;

import java.awt.*;

public class SlashParticleEffects {

    public static void slash(ColorEffectData colorData, Vec3 start, Vec3 end, Vec3 lookOffset) {
        ColorEffectData.ColorRecord record = colorData.getDefaultColorRecord();
        Color primary = record.primaryColor();
        Color secondary = record.secondaryColor();

        Level level = Minecraft.getInstance().level;
        RandomSource random = level.random;

        int density = 60;

        Vec3 direction = start.subtract(end).normalize();
        double d = start.distanceTo(end) / -density;

        for (int i = 0; i < density; i += 1) {
            double factor = Easing.EXPO_IN_OUT.ease(i, 0, 0.5f, density);
            Vec3 pos = start
                    .add(direction.multiply(i*d, i*d, i*d)) // step
                    .add(lookOffset.multiply(factor, factor, factor)); // easing
            slashParticle(level, random, pos, primary, secondary);
        }
    }

    private static void slashParticle(Level level, RandomSource random, Vec3 pos, Color primary, Color secondary) {
        GenericParticleData lengthData = GenericParticleData.create(0.1f, 0.5f, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).setCoefficient(1.25f).build();

        extrudingSpark(level, random, pos, RenderHandler.LATE_DELAYED_RENDER, LodestoneWorldParticleRenderType.ADDITIVE, primary, secondary,
                1.5f, 1.5f, 1f, lengthData);

        lengthData = lengthData.overrideCoefficientMultiplier(0.25f);

        extrudingSpark(level, random, pos, RenderHandler.DELAYED_RENDER, LodestoneWorldParticleRenderType.LUMITRANSPARENT, primary, secondary,
                3f, 2.6f, 1.75f, lengthData);
    }

    private static void extrudingSpark(Level level, RandomSource random, Vec3 pos, RenderHandler.LodestoneRenderLayer renderLayer, ParticleRenderType renderType, Color primary, Color secondary, float alphaMultiplier, float scaleMultiplier, float colorCoefficient, GenericParticleData lengthData) {
        WorldParticleBuilder.create(new WorldParticleOptions(LodestoneParticleRegistry.SPARK_PARTICLE).setBehaviorIfDefault(new ExtrudingSparkBehaviorComponent(lengthData)))
                .setRenderTarget(renderLayer)
                .setRenderType(renderType)
                .setMotion(0, 0.002f, 0)
                .setScaleData(GenericParticleData.create(0.04f, RandomHelper.randomBetween(random, 0.2f, 0.3f), 0).build().multiplyValue(scaleMultiplier))
                .setTransparencyData(GenericParticleData.create(0.8f * alphaMultiplier, 0f).build())
                .setColorData(ColorParticleData.create(primary, secondary).setCoefficient(colorCoefficient).build())
                .setLifetime((int) (RandomHelper.randomBetween(random, 30, 60) * 0.2f))
                .enableNoClip()
                .spawn(level, pos.x, pos.y, pos.z);
    }
}
