package victinix.dirthammer.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.Sys;
import victinix.dirthammer.items.ModItems;
import victinix.dirthammer.recipe.RecipeCompressor;

public class TileEntityCompressor extends TileEntity implements ISidedInventory {

    private ItemStack[] inventorySlots = new ItemStack[9];
    public int firstSlot = 0;
    public int lastSlot = 8;
    private String name = "compressor";
    public int timeCanCompress;
    public int currentItemCompressTime;
    public int ticksCompressItemSoFar;
    public int ticksPerItem;

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     *
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {

        if(side == 0) {
            return new int[] { 8 };
        }
        else {
            return new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
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

        if(slot == 8 && side == 0) {
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

        return 9;
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
    public ItemStack decrStackSize(int slot, int i)
    {
        if (this.inventorySlots[slot] != null)
        {
            ItemStack itemStack;

            if (this.inventorySlots[slot].stackSize <= i)
            {
                itemStack = this.inventorySlots[slot];
                this.inventorySlots[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.inventorySlots[slot].splitStack(i);

                if (this.inventorySlots[slot].stackSize == 0)
                {
                    this.inventorySlots[slot] = null;
                }

                return itemStack;
            }
        }
        else
        {
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

        boolean isSameItemStackAlreadyInSlot = itemStack != null && ItemStack.areItemStackTagsEqual(itemStack, inventorySlots[i]);
        inventorySlots[i] = itemStack;

        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        if(i == 0 && !isSameItemStackAlreadyInSlot) {
            ticksPerItem = compressingTime(itemStack);
            ticksCompressItemSoFar = 0;
            markDirty();
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

        return 64;
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

        if(slot == 8) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
        NBTTagList nbtTagList = new NBTTagList();

        for(int i = 0; i < inventorySlots.length; i++) {
            if(inventorySlots[i] != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("SlotCompressor", (byte)i);
                inventorySlots[i].writeToNBT(item);
                nbtTagList.appendTag(item);
            }
        }

        nbtTagCompound.setTag("ItemsCompressor", nbtTagList);
        nbtTagCompound.setInteger("timeCanCompress", timeCanCompress);
        nbtTagCompound.setInteger("ticksCompressItemSoFar", ticksCompressItemSoFar);
        nbtTagCompound.setInteger("ticksPerItem", ticksPerItem);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
        NBTTagList nbtTagList = nbtTagCompound.getTagList("ItemsCompressor", 10);

        for(int i = 0; i < nbtTagList.tagCount(); i++) {
            NBTTagCompound item = nbtTagList.getCompoundTagAt(i);
            byte slot = item.getByte("SlotCompressor");

            if(slot >= 0 && slot < inventorySlots.length) {
                inventorySlots[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        timeCanCompress = nbtTagCompound.getInteger("timeCanCompress");
        ticksCompressItemSoFar = nbtTagCompound.getInteger("ticksCompressItemSoFar");
        ticksPerItem = nbtTagCompound.getInteger("ticksPerItem");
    }

    public boolean compressingSomething() {

        return true;
    }

    public int compressingTime(ItemStack itemStack) {

        return 50;
    }

    public boolean canCompress() {

        if ((inventorySlots[0] == null || inventorySlots[1] == null || inventorySlots[2] == null || inventorySlots[3] == null || inventorySlots[4] == null || inventorySlots[5] == null || inventorySlots[6] == null || inventorySlots[7] == null)) {
            return false;
        }
        else {
            ItemStack output = new ItemStack(Items.diamond);

            if (inventorySlots[8] == null) {
                return true;
            }

            if (!inventorySlots[8].isItemEqual(output)) {
                return false;
            }

            int result = inventorySlots[8].stackSize + output.stackSize;
            return result <= getInventoryStackLimit() && result <= inventorySlots[1].getMaxStackSize();
        }
    }

    public void compressItem() {
        if(canCompress()) {

            ItemStack itemStack = new ItemStack(Items.diamond);

            if(inventorySlots[8] == null) {
                inventorySlots[8] = itemStack.copy();
            }
            else if(inventorySlots[8].getItem() == itemStack.getItem()) {
                inventorySlots[8].stackSize += itemStack.stackSize;
            }

            for (int i = 0; i < 8; i++) {
                inventorySlots[i].stackSize--;

                if(inventorySlots[i].stackSize <= 0) {
                    inventorySlots[i] = null;
                }
            }
        }
    }

    @Override
    public void updateEntity() {

        boolean hasBeenCompressing = compressingSomething();
        boolean changedCompressingState = false;

        if(compressingSomething()) {
            timeCanCompress--;
        }

        if(!worldObj.isRemote) {

            if(inventorySlots[0] != null && inventorySlots[1] != null && inventorySlots[2] != null && inventorySlots[3] != null && inventorySlots[4] != null && inventorySlots[5] != null && inventorySlots[6] != null && inventorySlots[7] != null) {
                if(inventorySlots[0].getItem() == ModItems.shatteredDiamond && inventorySlots[1].getItem() == ModItems.shatteredDiamond && inventorySlots[2].getItem() == ModItems.shatteredDiamond && inventorySlots[3].getItem() == ModItems.shatteredDiamond && inventorySlots[4].getItem() == ModItems.shatteredDiamond && inventorySlots[5].getItem() == ModItems.shatteredDiamond && inventorySlots[6].getItem() == ModItems.shatteredDiamond && inventorySlots[7].getItem() == ModItems.shatteredDiamond) {
                    System.out.println("SD in input slot!");
                    if(worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == Blocks.obsidian) {
                        if(!compressingSomething() && canCompress()) {
                            timeCanCompress = 40;
                            if(compressingSomething()) {
                                changedCompressingState = true;
                            }
                        }

                        if(compressingSomething() && canCompress()) {
                            ticksCompressItemSoFar++;
                            if(ticksCompressItemSoFar == ticksPerItem) {
                                ticksCompressItemSoFar = 0;
                                ticksPerItem = compressingTime(inventorySlots[1]);
                                compressItem();
                                changedCompressingState = true;
                            }
                        }
                        else {
                            ticksCompressItemSoFar = 0;
                        }
                    }

                }
            }

            if(hasBeenCompressing != compressingSomething()) {
                changedCompressingState = true;
            }
        }

        if(changedCompressingState) {
            markDirty();
        }
    }
}
