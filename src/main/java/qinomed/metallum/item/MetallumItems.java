package qinomed.metallum.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import qinomed.metallum.Metallum;
import qinomed.metallum.item.trinkets.ReagentCharmItem;
import qinomed.metallum.item.weapons.MetallumGlaiveItem;

@SuppressWarnings("unused")
public class MetallumItems {
    public static Item.Properties BASIC() {
        return new Item.Properties().tab(Metallum.METALLUM_TAB);
    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Metallum.MODID);

    // Materials
    public static final RegistryObject<Item>
            HALLOWED_FRAME = basicItem("hallowed_frame");
    // End

    // Iron Cluster Tools
    public static final RegistryObject<Item> IRON_CLUSTER_SWORD = ITEMS.register("iron_cluster_sword",
            () -> new SwordItem(MetallumTiers.IRON_CLUSTER, 5, -2.6f, BASIC()));

    public static final RegistryObject<Item> IRON_CLUSTER_PICKAXE = ITEMS.register("iron_cluster_pickaxe",
            () -> new PickaxeItem(MetallumTiers.IRON_CLUSTER, 1, -2.8f, BASIC()));

    public static final RegistryObject<Item> IRON_CLUSTER_AXE = ITEMS.register("iron_cluster_axe",
            () -> new AxeItem(MetallumTiers.IRON_CLUSTER, 7, -3.5f, BASIC()));

    public static final RegistryObject<Item> IRON_CLUSTER_SHOVEL = ITEMS.register("iron_cluster_shovel",
            () -> new ShovelItem(MetallumTiers.IRON_CLUSTER, 1.5f, -3.0f, BASIC()));

    public static final RegistryObject<Item> IRON_CLUSTER_HOE = ITEMS.register("iron_cluster_hoe",
            () -> new HoeItem(MetallumTiers.IRON_CLUSTER, -3, 0.0f, BASIC()));
    // End

    // Golden Cluster Tools
    public static final RegistryObject<Item> GOLDEN_CLUSTER_SWORD = ITEMS.register("golden_cluster_sword",
            () -> new SwordItem(MetallumTiers.GOLD_CLUSTER, 6, -2.4f, BASIC()));

    public static final RegistryObject<Item> GOLDEN_CLUSTER_PICKAXE = ITEMS.register("golden_cluster_pickaxe",
            () -> new PickaxeItem(MetallumTiers.GOLD_CLUSTER, 1, -2.8f, BASIC()));

    public static final RegistryObject<Item> GOLDEN_CLUSTER_AXE = ITEMS.register("golden_cluster_axe",
            () -> new AxeItem(MetallumTiers.GOLD_CLUSTER, 7, -3.0f, BASIC()));

    public static final RegistryObject<Item> GOLDEN_CLUSTER_SHOVEL = ITEMS.register("golden_cluster_shovel",
            () -> new ShovelItem(MetallumTiers.GOLD_CLUSTER, 1.5f, -3.0f, BASIC()));

    public static final RegistryObject<Item> GOLDEN_CLUSTER_HOE = ITEMS.register("golden_cluster_hoe",
            () -> new HoeItem(MetallumTiers.GOLD_CLUSTER, 0, -3.0f, BASIC()));
    // End

    // Weapons
    public static final RegistryObject<Item> CRUDE_GLAIVE = ITEMS.register("crude_glaive",
            () -> new MetallumGlaiveItem(Tiers.IRON, 1, 0.1f, BASIC().stacksTo(1).durability(350)));
    // End

    // Trinkets
    public static final RegistryObject<Item> REAGENT_PENDANT = ITEMS.register("reagent_pendant",
            () -> new ReagentCharmItem(BASIC().stacksTo(1)));
    // End

    private static RegistryObject<Item> basicItem(String name) {
        return ITEMS.register(name, () -> new Item(BASIC()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
