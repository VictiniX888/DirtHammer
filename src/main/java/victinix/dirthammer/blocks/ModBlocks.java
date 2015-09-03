package victinix.dirthammer.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

    public static Block compressor;

    public static void init() {

        compressor = new BlockCompressor(Material.rock);
    }
}
