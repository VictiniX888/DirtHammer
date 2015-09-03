package victinix.dirthammer.items.hammers;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import victinix.dirthammer.others.Data;
import victinix.dirthammer.others.Tabs;

import java.util.Set;

public class ItemDiamondHammer extends ItemTool {

    private String name = "diamondHammer";

    private static final Set isDirt = Sets.newHashSet(new Block[] {
            Blocks.dirt
    });

    public ItemDiamondHammer(ToolMaterial toolMaterial) {

        super(1f, toolMaterial, isDirt);
        setUnlocalizedName(Data.MODID + ":" + name);
        setTextureName(Data.MODID + ":" + name);
        setCreativeTab(Tabs.tabDirtHammer);
        GameRegistry.registerItem(this, name);
    }
}
