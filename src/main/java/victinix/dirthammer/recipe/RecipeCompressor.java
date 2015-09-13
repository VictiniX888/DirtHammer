package victinix.dirthammer.recipe;

import com.google.common.collect.Maps;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import victinix.dirthammer.items.ModItems;

import java.util.Iterator;
import java.util.Map;

public class RecipeCompressor {

    private static final RecipeCompressor compressorBase = new RecipeCompressor();
    private final Map<ItemStack, ItemStack> recipeList = Maps.newHashMap();

    public static RecipeCompressor instance() {

        return compressorBase;
    }

    public RecipeCompressor() {

        addRecipe(new ItemStack(ModItems.shatteredDiamond), new ItemStack(Items.diamond));
    }

    public void addRecipe(ItemStack input, ItemStack output) {

        recipeList.put(input, output);
    }

    public ItemStack getResult(ItemStack itemStack) {

        Iterator iterator = recipeList.entrySet().iterator();
        Map.Entry entry;

        do {
            if(!iterator.hasNext()) {
                return null;
            }
            entry = (Map.Entry)iterator.next();
        }
        while(!areItemStacksEqual(itemStack, (ItemStack)entry.getKey()));
        return (ItemStack)entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack itemStack1, ItemStack itemStack2) {

        if(itemStack2 == itemStack1 && (itemStack2.getItemDamage() == 32767 || itemStack2.getItemDamage() == itemStack1.getItemDamage())) {
            return true;
        }
        else {
            return false;
        }
    }

    public Map getRecipeList() {

        return recipeList;
    }
}
