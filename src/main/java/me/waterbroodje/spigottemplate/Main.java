package me.waterbroodje.spigottemplate;

import me.waterbroodje.spigottemplate.database.MySQL;
import me.waterbroodje.spigottemplate.database.SQLModules;
import me.waterbroodje.spigottemplate.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static ListenerManager listenerManager;
    private static MySQL mySQL;
    private static SQLModules sqlModules;

    @Override
    public void onEnable() {
        instance = this;
        listenerManager = new ListenerManager();
        mySQL = new MySQL();
        sqlModules = new SQLModules(this);

        listenerManager.register();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static ListenerManager getListenerManager() {
        return listenerManager;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    public static SQLModules getSqlModules() {
        return sqlModules;
    }
}
