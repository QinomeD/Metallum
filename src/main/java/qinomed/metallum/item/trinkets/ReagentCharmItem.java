package qinomed.metallum.item.trinkets;

import com.sammy.malum.common.item.curiosities.curios.MalumCurioItem;
import com.sammy.malum.core.systems.item.IMalumEventResponderItem;
import net.minecraft.world.effect.MobEffectInstance;
import team.lodestar.lodestone.helpers.EntityHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ReagentCharmItem extends MalumCurioItem implements IMalumEventResponderItem {
    public ReagentCharmItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isGilded() {
        return true;
    }

    @Override
    public void pickupSpirit(LivingEntity attacker, ItemStack stack, double arcaneResonance) {
        MobEffectInstance speed = attacker.getEffect(MobEffects.MOVEMENT_SPEED);
        MobEffectInstance jumpboost = attacker.getEffect(MobEffects.JUMP);

        if(speed == null) {
            attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100 + (int)(arcaneResonance * 25.0D), 0, true, true, true));
        } else {
            EntityHelper.extendEffect(speed, attacker, 100 + (int)(arcaneResonance * 25.0D), 0);
        }

        if(jumpboost == null) {
            attacker.addEffect(new MobEffectInstance(MobEffects.JUMP, 100 + (int)(arcaneResonance * 25.0D), 0, true, true, true));
        } else {
            EntityHelper.extendEffect(jumpboost, attacker, 100 + (int)(arcaneResonance * 25.0D), 0);
        }
    }
}
