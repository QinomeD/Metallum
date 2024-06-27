package qinomed.metallum.vfx.networked;

import com.sammy.malum.visual_effects.networked.ParticleEffectType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import qinomed.metallum.vfx.SlashParticleEffects;

import java.util.function.Supplier;

public class SlashParticleEffect extends ParticleEffectType {
    public SlashParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            SlashParticleEffects.slash(positionData, colorData);
        };
    }
}
