package victinix.dirthammer.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class ContainerCompressor extends Container {

    private TileEntityCompressor compressor;

    public ContainerCompressor(InventoryPlayer inventoryPlayer, TileEntityCompressor tileEntityCompressor) {

        compressor = tileEntityCompressor;

        for(int j = 0; j < 8; j++) {
            addSlotToContainer(new Slot(tileEntityCompressor, j, 17 + j * 18, 17));
        }
        addSlotToContainer(new Slot(tileEntityCompressor, 8, 80, 58));
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
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
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i) {

        ItemStack result = null;
        Slot slot = (Slot)inventorySlots.get(i);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            result = itemStack.copy();

            if (i <9) {
                if(!mergeItemStack(itemStack, 9, 44, false)) {
                    return null;
                }
                slot.onSlotChange(itemStack, result);
            }
            else if(!mergeItemStack(itemStack, 0, 7, false)) {
                return null;
            }
            else if(i >= 9 && i < 36) {
                if(!mergeItemStack(itemStack, 36, 44, false)) {
                    return null;
                }
            }
            else if(i >= 36 && i < 45 && !mergeItemStack(itemStack, 9, 35, false)) {
                return null;
            }
            else if(!mergeItemStack(itemStack, 9, 44, false)) {
                return null;
            }

            if(itemStack.stackSize == 0) {
                slot.putStack((ItemStack)null);
            }
            else {
                slot.onSlotChanged();
            }

            if(itemStack.stackSize == result.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemStack);
        }

        return result;
    }
}
