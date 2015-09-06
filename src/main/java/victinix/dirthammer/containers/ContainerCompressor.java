package victinix.dirthammer.containers;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class ContainerCompressor extends Container {

    private TileEntityCompressor compressor;

    public ContainerCompressor(final InventoryPlayer inventoryPlayer, TileEntityCompressor tileEntityCompressor) {

        compressor = tileEntityCompressor;

        for(int i = 0; i < 8; i++) {
            addSlotToContainer(new Slot(tileEntityCompressor, i, 17 + i * 18, 17));
        }

        addSlotToContainer(new Slot(tileEntityCompressor, 8, 80, 58) {

            /**
             * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
             *
             */
            @Override
            public boolean isItemValid(ItemStack itemStack) {

                return false;
            }
        });

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, 9 + j + i * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {

        return compressor.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     *
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw)
    {
        ItemStack stack = null;
        Slot slot = (Slot) inventorySlots.get(slotRaw);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            if (slotRaw < compressor.getSizeInventory()) {
                if (!mergeItemStack(stackInSlot, compressor.getSizeInventory(), inventorySlots.size(), false)) {
                    return null;
                }
            }
            else if (!mergeItemStack(stackInSlot, compressor.firstSlot, compressor.lastSlot, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return stack;
    }
}
