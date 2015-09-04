package victinix.dirthammer.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCompressor extends TileEntity implements ISidedInventory {

    private ItemStack[] inventorySlots = new ItemStack[9];
    private String name = "compressor";

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     *
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {

        if(side == 0) {
            return new int[] { 9 };
        }
        else {
            return new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     *
     */
    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {

        return isItemValidForSlot(slot, itemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     *
     */
    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {

        if(slot == 9 && side == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {

        return inventorySlots.length;
    }

    /**
     * Returns the stack in slot i
     *
     */
    @Override
    public ItemStack getStackInSlot(int i) {

        return inventorySlots[i];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     */
    @Override
    public ItemStack decrStackSize(int slot, int i) {

        if(inventorySlots[i] != null) {
            ItemStack itemStack;

            if(inventorySlots[i].stackSize <= i) {
                itemStack = inventorySlots[i];
                inventorySlots = null;
                return itemStack;
            }
            else {
                itemStack = inventorySlots[i].splitStack(i);
                if(inventorySlots[i].stackSize == 0) {
                    inventorySlots[i] = null;
                }
                return itemStack;
            }
        }
        else {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        if(inventorySlots[i] != null) {
            ItemStack itemStack = inventorySlots[i];
            inventorySlots[i] = null;
            return itemStack;
        }
        else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     */
    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        inventorySlots[i] = itemStack;

        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName() {

        return name;
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName() {

        return false;
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {

        return 1;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {

        if(worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        }
        else {
            if(entityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {

        if(slot == 9) {
            return false;
        }
        else {
            return true;
        }
    }
}
