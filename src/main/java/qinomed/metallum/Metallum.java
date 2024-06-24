package qinomed.metallum;

import com.sammy.malum.common.item.cosmetic.skins.ArmorSkin;
import com.sammy.malum.registry.common.item.ArmorSkinRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import qinomed.metallum.client.model.cosmetic.abyssal.DelverBondrewdArmorModel;
import qinomed.metallum.datagen.MetallumItemModels;
import qinomed.metallum.datagen.MetallumLang;
import qinomed.metallum.datagen.MetallumRecipes;
import qinomed.metallum.item.MetallumItems;
import qinomed.metallum.item.MetallumTabsRegistry;
import qinomed.metallum.item.skin.DelverArmorSkin;
import team.lodestar.lodestone.systems.item.LodestoneArmorItem;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Metallum.MODID)
public class Metallum {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "metallum";

    // fuck registry, I ain't refactoring and adding gets everywhere
    public static CreativeModeTab METALLUM_TAB;

    public Metallum() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        MetallumItems.register(modEventBus);
        MetallumTabsRegistry.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ArmorSkinRegistry.registerItemSkin(
                new DelverArmorSkin("delver", LodestoneArmorItem.class, MetallumItems.DELVER_WEAVE.get()),
                new ArmorSkin.ArmorSkinDatagenData(
                        "metallum:item/cosmetic/armor_icons/delver_",
                        "metallum:models/item/delver_",
                        "visor", "cloak", "leggings", "boots"
                ));
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        public static DelverBondrewdArmorModel DELVER;

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(DelverBondrewdArmorModel.LAYER, DelverBondrewdArmorModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void addLayers(EntityRenderersEvent.AddLayers event) {
            DELVER = new DelverBondrewdArmorModel(event.getEntityModels().bakeLayer(DelverBondrewdArmorModel.LAYER));
        }
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        MetallumItemModels itemProvider = new MetallumItemModels(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), itemProvider);
        generator.addProvider(event.includeClient(), new MetallumLang(generator));
        generator.addProvider(event.includeServer(), new MetallumRecipes(generator));
        // generator.addProvider(event.includeServer(), new MetallumSpiritInfusionRecipes(generator));
    }
}
