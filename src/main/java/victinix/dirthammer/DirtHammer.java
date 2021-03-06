package victinix.dirthammer;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import victinix.dirthammer.events.EventDropsAdd;
import victinix.dirthammer.events.EventDropsRemove;
import victinix.dirthammer.items.ModItems;
import victinix.dirthammer.others.Data;
import victinix.dirthammer.others.Recipes;
import victinix.dirthammer.others.Tabs;

@Mod(modid = Data.MODID, name = Data.MODNAME, version = Data.VERSION)

public class DirtHammer {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ModItems.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        Recipes.Recipes();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        Tabs.postInit();
        MinecraftForge.EVENT_BUS.register(new EventDropsAdd());
        MinecraftForge.EVENT_BUS.register(new EventDropsRemove());
    }
}
