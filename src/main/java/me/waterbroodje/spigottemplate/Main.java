package me.waterbroodje.spigottemplate;

import me.waterbroodje.spigottemplate.commands.CommandManager;
import me.waterbroodje.spigottemplate.database.MySQL;
import me.waterbroodje.spigottemplate.database.SQLModules;
import me.waterbroodje.spigottemplate.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static ListenerManager listenerManager;
    private static CommandManager commandManager;
    private static MySQL mySQL;
    private static SQLModules sqlModules;

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        instance = this;
        listenerManager = new ListenerManager();
        commandManager = new CommandManager();
        mySQL = new MySQL();
        sqlModules = new SQLModules();

        listenerManager.register();
        commandManager.register();
        mySQL.mysqlSetup();
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
