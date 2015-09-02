package victinix.dirthammer.items;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import victinix.dirthammer.items.hammers.ItemDiamondHammer;
import victinix.dirthammer.items.hammers.ItemDirtHammer;

public class ModItems {

    public static Item.ToolMaterial DIRTHAMMER;
    public static Item.ToolMaterial DIAMONDHAMMER;

    public static Item dirtHammer;
    public static Item diamondHammer;

    public static void init() {

        DIRTHAMMER = EnumHelper.addToolMaterial("DIRTHAMMER", 0, 2000, 20f, 0, 0);
        DIAMONDHAMMER = EnumHelper.addToolMaterial("DIAMONDHAMMER", 0, 2000, 1f, 0, 0);

        dirtHammer = new ItemDirtHammer(ModItems.DIRTHAMMER);
        diamondHammer = new ItemDiamondHammer(ModItems.DIAMONDHAMMER);
    }
}
