package qinomed.metallum.item.skin;

import com.sammy.malum.client.cosmetic.ArmorSkinRenderingData;
import com.sammy.malum.client.cosmetic.SimpleArmorSkinRenderingData;
import com.sammy.malum.common.item.cosmetic.skins.ArmorSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import qinomed.metallum.Metallum;
import team.lodestar.lodestone.systems.item.LodestoneArmorItem;

public class DelverArmorSkin extends ArmorSkin {
    public DelverArmorSkin(String id, Class<? extends LodestoneArmorItem> validArmorClass, Item weaveItem) {
        super(id, validArmorClass, weaveItem);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ArmorSkinRenderingData getRenderingData() {
        return new SimpleArmorSkinRenderingData(new ResourceLocation(Metallum.MODID, "textures/armor/cosmetic/" + id + ".png"), Metallum.ClientModEvents.DELVER);
    }
}
