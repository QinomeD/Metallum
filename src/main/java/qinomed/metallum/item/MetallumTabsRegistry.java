package qinomed.metallum.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import qinomed.metallum.Metallum;

public class MetallumTabsRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Metallum.MODID);

    public static final RegistryObject<CreativeModeTab> METALLUM_TAB = CREATIVE_TABS.register("metallum_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.metallum"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> MetallumItems.IRON_CLUSTER_PICKAXE.get().getDefaultInstance()).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
