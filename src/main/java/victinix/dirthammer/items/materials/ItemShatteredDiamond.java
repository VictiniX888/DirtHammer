package victinix.dirthammer.items.materials;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import victinix.dirthammer.others.Data;
import victinix.dirthammer.others.Tabs;

public class ItemShatteredDiamond extends Item {

    private String name = "shatteredDiamond";

    public ItemShatteredDiamond() {

        super();
        setUnlocalizedName(Data.MODID + ":" + name);
        setTextureName(Data.MODID + ":" + name);
        setCreativeTab(Tabs.tabRandom);
        GameRegistry.registerItem(this, name);
    }
}
