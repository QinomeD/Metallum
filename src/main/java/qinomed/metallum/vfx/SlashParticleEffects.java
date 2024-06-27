package qinomed.metallum.vfx;

import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import com.sammy.malum.visual_effects.networked.data.PositionEffectData;
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

    public static void slash(PositionEffectData positionData, ColorEffectData colorData) {
        ColorEffectData.ColorRecord record = colorData.getDefaultColorRecord();
        Color primary = record.primaryColor();
        Color secondary = record.secondaryColor();

        Level level = Minecraft.getInstance().level;
        RandomSource random = level.random;

        Vec3 pos = new Vec3(positionData.posX, positionData.posY, positionData.posZ);

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
