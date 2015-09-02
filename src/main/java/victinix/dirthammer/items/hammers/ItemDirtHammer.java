package victinix.dirthammer.items.hammers;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import victinix.dirthammer.others.Data;
import victinix.dirthammer.others.Tabs;

import java.util.Set;

public class ItemDirtHammer extends ItemTool {

    private String name = "dirtHammer";

    private static final Set isDiamond = Sets.newHashSet(new Block[] {
            Blocks.diamond_block
    });

    public ItemDirtHammer(ToolMaterial toolMaterial) {

        super(1f, toolMaterial, isDiamond);
        setUnlocalizedName(Data.MODID + ":" + name);
        setTextureName(Data.MODID + ":" + name);
        setCreativeTab(Tabs.tabRandom);
        GameRegistry.registerItem(this, name);
    }
}
