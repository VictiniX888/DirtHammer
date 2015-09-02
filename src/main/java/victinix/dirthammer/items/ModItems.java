package victinix.dirthammer.items;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import victinix.dirthammer.items.hammers.ItemDirtHammer;

public class ModItems {

    public static Item.ToolMaterial HAMMER;
    public static Item dirtHammer;

    public static void init() {

        HAMMER = EnumHelper.addToolMaterial("HAMMER", 0, 2000, 20f, 0, 0);
        dirtHammer = new ItemDirtHammer(ModItems.HAMMER);
    }
}
