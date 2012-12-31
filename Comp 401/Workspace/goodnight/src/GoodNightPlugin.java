import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoodNightPlugin extends Plugin {
    static final GoodNightListener listener = new GoodNightListener();
    private Logger log = Logger.getLogger("Minecraft");
    private String name = "GoodNightPlugin";
    private String version = "0.1";

    public void enable() {
    	etc.getInstance().addCommand("/goodnight", "- say good night to all (requesting sleep)" );
    	log.info(name + " version " + version + " is enabled.");
    }
    
    public void disable() {
    	etc.getInstance().removeCommand("/goodnight");
    }

    public void initialize() {
        etc.getLoader().addListener(
            PluginLoader.Hook.COMMAND, listener, this,
            PluginListener.Priority.MEDIUM);
    }
}
