package qinomed.metallum.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import qinomed.metallum.Metallum;
import qinomed.metallum.item.weapons.MetallumGlaiveItem;
import team.lodestar.lodestone.systems.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.systems.datagen.itemsmith.AbstractItemModelSmith;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneItemModelProvider;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static qinomed.metallum.item.MetallumItems.ITEMS;

public class MetallumItemModels extends LodestoneItemModelProvider {
    public MetallumItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Metallum.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Supplier<Item>> items = new HashSet<>(ITEMS.getEntries());

        // items.removeIf(i -> i.get() instanceof BlockItem);
        items.removeIf(i -> i.get() instanceof MetallumGlaiveItem);

        AbstractItemModelSmith.ItemModelSmithData data = new AbstractItemModelSmith.ItemModelSmithData(this, items::remove);

        setTexturePath("");
        ItemModelSmithTypes.HANDHELD_ITEM.act(data, items.stream().filter(i -> i.get() instanceof DiggerItem).toList());
        ItemModelSmithTypes.HANDHELD_ITEM.act(data, items.stream().filter(i -> i.get() instanceof SwordItem).toList());

        ItemModelSmithTypes.GENERATED_ITEM.act(data, items);
    }
}
