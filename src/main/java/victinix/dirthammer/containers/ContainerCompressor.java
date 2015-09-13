package victinix.dirthammer.containers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class ContainerCompressor extends Container {

    private TileEntityCompressor compressor;
    private int timeCanCompress;
    private int ticksCompressItemSoFar;
    private int ticksPerItem;

    public ContainerCompressor(final InventoryPlayer inventoryPlayer, TileEntityCompressor tileEntityCompressor) {

        compressor = tileEntityCompressor;

        for (int i = 0; i < 8; i++) {
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

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {

        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, compressor.timeCanCompress);
        iCrafting.sendProgressBarUpdate(this, 1, compressor.ticksCompressItemSoFar);
        iCrafting.sendProgressBarUpdate(this, 2, compressor.ticksPerItem);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {

        super.detectAndSendChanges();

        for(int i = 0; i < crafters.size(); i++) {
            ICrafting iCrafting = (ICrafting)crafters.get(i);
            if(timeCanCompress != compressor.timeCanCompress) {
                iCrafting.sendProgressBarUpdate(this, 0, compressor.timeCanCompress);
            }
            if(ticksCompressItemSoFar != compressor.ticksCompressItemSoFar) {
                iCrafting.sendProgressBarUpdate(this, 0, compressor.ticksCompressItemSoFar);
            }
            if(ticksPerItem != compressor.ticksPerItem) {
                iCrafting.sendProgressBarUpdate(this, 0, compressor.ticksPerItem);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j) {

        if(i == 0) {
            compressor.timeCanCompress = j;
        }
        if(i == 1) {
            compressor.ticksCompressItemSoFar = j;
        }
        if(i == 2) {
            compressor.ticksPerItem = j;
        }
    }
}
