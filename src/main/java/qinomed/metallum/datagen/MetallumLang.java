package qinomed.metallum.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import qinomed.metallum.Metallum;
import team.lodestar.lodestone.helpers.DataHelper;

import java.util.HashSet;
import java.util.Set;

import static qinomed.metallum.item.MetallumItems.ITEMS;

public class MetallumLang extends LanguageProvider {
    public MetallumLang(DataGenerator gen) {
        super(gen, Metallum.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());

        items.forEach(item -> add(item.get(), DataHelper.toTitleCase(item.getId().getPath(), "_")));
    }
}
