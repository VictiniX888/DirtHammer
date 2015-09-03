package victinix.dirthammer.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import victinix.dirthammer.others.Data;
import victinix.dirthammer.others.Tabs;
import victinix.dirthammer.tileentities.TileEntityCompressor;

public class BlockCompressor extends BlockContainer {

    private String name = "compressor";

    public BlockCompressor(Material material) {

        super(material);
        setBlockName(Data.MODID + ":" + name);
        setBlockTextureName(Data.MODID + ":" + name);
        setCreativeTab(Tabs.tabDirtHammer);
        GameRegistry.registerBlock(this, name);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityCompressor();
    }
}
