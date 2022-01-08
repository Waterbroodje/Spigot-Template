package me.waterbroodje.spigottemplate.listeners;

import me.waterbroodje.spigottemplate.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class ListenerManager {

    public void register() {
        String packageName = getClass().getPackage().getName();

        for (Class<?> classes : new Reflections(packageName).getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) classes
                        .getDeclaredConstructor()
                        .newInstance();
                Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
