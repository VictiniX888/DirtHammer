package victinix.dirthammer.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import victinix.dirthammer.items.ModItems;

public class EventDropsAdd {

    @SubscribeEvent
    public void event(BlockEvent.BreakEvent event) {

        if(event.block == Blocks.diamond_block && event.getPlayer().getHeldItem() != null && event.getPlayer().getHeldItem().getItem() == ModItems.dirtHammer) {
            event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Blocks.dirt)));
        }

        if(event.block == Blocks.dirt && event.getPlayer().getHeldItem() != null && event.getPlayer().getHeldItem().getItem() == ModItems.diamondHammer) {
            event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.diamond)));
        }
    }
}
