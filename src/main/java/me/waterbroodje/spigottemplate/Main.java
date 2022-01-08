package me.waterbroodje.spigottemplate;

import me.waterbroodje.spigottemplate.commands.CommandManager;
import me.waterbroodje.spigottemplate.database.MySQL;
import me.waterbroodje.spigottemplate.database.SQLModules;
import me.waterbroodje.spigottemplate.listeners.ListenerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static ListenerManager listenerManager;
    private static CommandManager commandManager;
    private static MySQL mySQL;
    private static SQLModules sqlModules;

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

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
