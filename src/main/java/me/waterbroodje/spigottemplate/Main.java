package me.waterbroodje.spigottemplate;

import me.waterbroodje.spigottemplate.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static ListenerManager listenerManager;

    @Override
    public void onEnable() {
        instance = this;
        listenerManager = new ListenerManager();

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
}
