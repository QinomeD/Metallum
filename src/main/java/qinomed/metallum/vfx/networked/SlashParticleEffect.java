package qinomed.metallum.vfx.networked;

import com.sammy.malum.visual_effects.networked.ParticleEffectType;
import com.sammy.malum.visual_effects.networked.data.NBTEffectData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import qinomed.metallum.vfx.SlashParticleEffects;

import java.util.function.Supplier;

public class SlashParticleEffect extends ParticleEffectType {
    public SlashParticleEffect(String id) {
        super(id);
    }

    public static NBTEffectData createData(Vec3 start, Vec3 end) {
        NBTEffectData nbtData = new NBTEffectData(new CompoundTag());
        nbtData.compoundTag.putDouble("startX", start.x);
        nbtData.compoundTag.putDouble("startY", start.y);
        nbtData.compoundTag.putDouble("startZ", start.z);

        nbtData.compoundTag.putDouble("endX", end.x);
        nbtData.compoundTag.putDouble("endY", end.y);
        nbtData.compoundTag.putDouble("endZ", end.z);
        return nbtData;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            CompoundTag tag = nbtData.compoundTag;

            Vec3 start = new Vec3(tag.getDouble("startX"), tag.getDouble("startY"), tag.getDouble("startZ"));
            Vec3 end = new Vec3(tag.getDouble("endX"), tag.getDouble("endY"), tag.getDouble("endZ"));
            Vec3 lookOffset = new Vec3(positionData.posX, positionData.posY, positionData.posZ);

            SlashParticleEffects.slash(colorData, start, end, lookOffset);
        };
    }
}
