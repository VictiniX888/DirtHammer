package victinix.dirthammer.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import victinix.dirthammer.DirtHammer;
import victinix.dirthammer.containers.ContainerCompressor;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class GuiHandler implements IGuiHandler {

    public GuiHandler() {

        NetworkRegistry.INSTANCE.registerGuiHandler(DirtHammer.instance, this);
    }

    /**
     * Returns a Server side Container to be displayed to the user.
     *
     * @param ID     The Gui ID Number
     * @param player The player viewing the Gui
     * @param world  The current world
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Position
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch(ID) {
            case 0:
                if(tileEntity != null && tileEntity instanceof TileEntityCompressor) {
                    return new ContainerCompressor(player.inventory, (TileEntityCompressor) tileEntity);
                }
                else {
                    return null;
                }
            default :
                return null;
        }
    }

    /**
     * Returns a Container to be displayed to the user. On the client side, this
     * needs to return a instance of GuiScreen On the server side, this needs to
     * return a instance of Container
     *
     * @param ID     The Gui ID Number
     * @param player The player viewing the Gui
     * @param world  The current world
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Position
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        switch(ID) {
            case 0:
                if(tileEntity != null && tileEntity instanceof TileEntityCompressor) {
                    return new GuiCompressor(player.inventory, (TileEntityCompressor) tileEntity);
                }
                else {
                    return null;
                }
            default :
                return null;
        }
    }
}
