package victinix.dirthammer.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCompressor extends TileEntity implements ISidedInventory {

    ItemStack[] inventory = new ItemStack[getSizeInventory()];

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     *
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int j) {

        int accessibleSlot = -1;
        for(int i = 0; i < getSizeInventory(); i++)
            if(getStackInSlot(i) != null)
                accessibleSlot = i;

        if(accessibleSlot == -1) {
            return new int[0];
        }
        else {
            return new int[] {
                    accessibleSlot
            };
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     *
     */
    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int j) {

        return true;
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     *
     */
    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {

        if(i == 3) {
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

        return 3;
    }

    /**
     * Returns the stack in slot i
     *
     */

    @Override
    public ItemStack getStackInSlot(int i) {

        return inventory[i];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     */
    @Override
    public ItemStack decrStackSize(int i, int j) {

        if(inventory != null) {
            ItemStack stackAt;

            if (inventory[i].stackSize <= j) {
                stackAt = inventory[i];
                inventory[i] = null;
                return stackAt;
            }
            else {
                stackAt = inventory[i].splitStack(j);

                if (inventory[i].stackSize == 0)
                    inventory[i] = null;

                return stackAt;
            }
        }

        return null;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     * @param p_70304_1_
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param p_70299_1_
     * @param p_70299_2_
     */
    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName() {
        return null;
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
        return 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     * @param p_70300_1_
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
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
     * @param p_94041_1_
     * @param p_94041_2_
     */
    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}
