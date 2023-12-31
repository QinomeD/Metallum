package qinomed.metallum.item;

import com.sammy.malum.core.systems.item.ItemSkin;
import com.sammy.malum.registry.common.ItemSkinRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import qinomed.metallum.Metallum;

@Mod.EventBusSubscriber(modid = Metallum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MetallumItemSkins {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientOnly.addRenderingData();
    }

    public static class ClientOnly {
        public static void addRenderingData() {
            ItemSkinRegistry.ClientOnly.registerItemSkinRenderingData("delver", new ItemSkin.ItemSkinRenderingData(
                    p -> new ResourceLocation("metallum", "textures/armor/bondrewd.png"),
                    p -> Metallum.ClientModEvents.DELVER
            ));
        }
    }

}
