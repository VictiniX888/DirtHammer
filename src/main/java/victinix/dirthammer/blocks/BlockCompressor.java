package victinix.dirthammer.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import victinix.dirthammer.DirtHammer;
import victinix.dirthammer.others.Data;
import victinix.dirthammer.others.Tabs;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class BlockCompressor extends BlockContainer {

    private String name = "compressor";

    public IIcon[] icons = new IIcon[3];

    public BlockCompressor(Material material) {

        super(material);
        setBlockName(Data.MODID + ":" + name);
        setBlockTextureName(Data.MODID + ":" + name);
        setCreativeTab(Tabs.tabDirtHammer);
        GameRegistry.registerBlock(this, name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

        icons[0] = iconRegister.registerIcon(Data.MODID + ":" + name + "_top");
        icons[1] = iconRegister.registerIcon(Data.MODID + ":" + name + "_side");
        icons[2] = iconRegister.registerIcon(Data.MODID + ":" + name + "_front");
    }

    /**
     * Gets the block's texture. Args: side, meta
     *
     */
    @Override
    public IIcon getIcon(int side, int meta) {

        if(side == 0 || side == 1) {
            return icons[0];
        }
        else {
            if(side != meta) {
                return icons[1];
            }
            else {
                return icons[2];
            }
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     *
     */
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {

        super.onBlockAdded(world, x, y, z);
    }

    /**
     * Called when the block is placed in the world.
     *
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack itemStack) {

        int rotation = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4f / 360f) + 0.5d) & 3;

        if(rotation == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if(rotation == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if(rotation == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if(rotation == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     */
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityCompressor();
    }

    /**
     * Called upon block activation (right click on the block.)
     *
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {

        if(!world.isRemote) {
            FMLNetworkHandler.openGui(entityPlayer, DirtHammer.instance, 0, world, x, y, z);
        }

        return true;
    }
}
