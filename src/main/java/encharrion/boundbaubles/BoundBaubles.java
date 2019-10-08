package encharrion.boundbaubles;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BoundBaubles.MODID, name = BoundBaubles.NAME, version = BoundBaubles.VERSION)
public class BoundBaubles
{
    public static final String MODID = "boundbaubles";
    public static final String NAME = "Bound Baubles";
    public static final String VERSION = "0.1";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}