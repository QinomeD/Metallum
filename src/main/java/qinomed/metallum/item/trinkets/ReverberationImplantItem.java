package qinomed.metallum.item.trinkets;

import com.sammy.malum.common.item.curiosities.curios.MalumCurioItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.systems.item.IEventResponderItem;

public class ReverberationImplantItem extends MalumCurioItem implements IEventResponderItem {
    public ReverberationImplantItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isGilded() {
        return true;
    }

    @Override
    public void killEvent(LivingEntity attacker, LivingEntity target, ItemStack stack) {
        attacker.heal(2);
        attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.PLAYERS, 2f, 0.1f);
    }
}
