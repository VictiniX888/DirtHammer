package victinix.dirthammer.others;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import victinix.dirthammer.items.ModItems;

public class Recipes {

    public static void Recipes() {

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dirtHammer, 1, 0), new Object[]{
                "AAA",
                "ABA",
                " B ",
                'A', new ItemStack(Blocks.dirt),
                'B', "stickWood"
        }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.diamondHammer, 1, 0), new Object[]{
                "AAA",
                "ABA",
                " B ",
                'A', "blockDiamond",
                'B', "stickWood"
        }));
    }
}
