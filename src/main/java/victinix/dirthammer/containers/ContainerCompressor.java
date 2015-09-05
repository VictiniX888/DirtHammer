package victinix.dirthammer.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class ContainerCompressor extends Container {

    private TileEntityCompressor compressor;

    public ContainerCompressor(InventoryPlayer inventoryPlayer, TileEntityCompressor tileEntityCompressor) {

        compressor = tileEntityCompressor;

        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }
        for(int j = 0; j < 8; j++) {
            addSlotToContainer(new Slot(tileEntityCompressor, j, 17 + j * 18, 17));
        }
        addSlotToContainer(new Slot(tileEntityCompressor, 8, 80, 58));
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {

        return compressor.isUseableByPlayer(entityPlayer);
    }
}
