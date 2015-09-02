package victinix.dirthammer.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import victinix.dirthammer.items.ModItems;

public class EventDropsRemove {

    @SubscribeEvent
    public void event(BlockEvent.HarvestDropsEvent event) {

        if(event.block == Blocks.dirt && event.harvester.getHeldItem() != null && event.harvester.getHeldItem().getItem() == ModItems.diamondHammer) {
            for(int i = 0; i < event.drops.size(); i++) {
                ItemStack itemStack = event.drops.get(i);
                if(itemStack.getItem() == Item.getItemFromBlock(Blocks.dirt)) {
                    event.drops.remove(i);
                }
            }
        }
    }
}
