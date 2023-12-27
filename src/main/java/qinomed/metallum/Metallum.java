package qinomed.metallum;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import qinomed.metallum.datagen.MetallumItemModels;
import qinomed.metallum.datagen.MetallumLang;
import qinomed.metallum.item.MetallumItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Metallum.MODID)
public class Metallum {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "metallum";

    public static final CreativeModeTab METALLUM_TAB = new CreativeModeTab("metallum") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MetallumItems.IRON_CLUSTER_PICKAXE.get());
        }
    };

    public Metallum() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        MetallumItems.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code

        }
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        MetallumItemModels itemProvider = new MetallumItemModels(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), itemProvider);
        generator.addProvider(event.includeClient(), new MetallumLang(generator));
    }
}
