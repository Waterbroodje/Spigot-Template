package me.waterbroodje.spigottemplate.listeners;

import me.waterbroodje.spigottemplate.Main;
import me.waterbroodje.spigottemplate.utils.ClassUtils;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;

public class ListenerManager {

    public void register() {
        String packageName = getClass().getPackage().getName();

        ClassUtils classUtils = new ClassUtils();
        for (Class<?> classes : classUtils.getClasses(packageName + ".listeners")) {
            if (!classes.getName().equalsIgnoreCase("ListenerManager")) {
                try {
                    Listener listener = (Listener) classes
                            .getDeclaredConstructor()
                            .newInstance();
                    Main.getInstance().getServer().getPluginManager().registerEvents(listener, Main.getInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
