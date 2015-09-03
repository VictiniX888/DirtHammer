package victinix.dirthammer.others;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import victinix.dirthammer.items.ModItems;

public class Tabs {

    private static class CustomCreativeTab extends CreativeTabs {

        private ItemStack stack;

        public CustomCreativeTab(String label) {

            super(label);
        }

        @Override
        public Item getTabIconItem() {

            return stack.getItem();
        }

        public void setTabIconItemStack(ItemStack stack) {

            this.stack = stack;
        }

        @Override
        public ItemStack getIconItemStack() {

            return stack;
        }
    }

    public static final CustomCreativeTab tabDirtHammer = new CustomCreativeTab("tabDirtHammer");

    public static void postInit() {

        tabDirtHammer.setTabIconItemStack(new ItemStack(ModItems.dirtHammer));
    }
}
