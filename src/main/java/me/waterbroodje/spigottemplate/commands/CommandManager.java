package me.waterbroodje.spigottemplate.commands;

import me.waterbroodje.spigottemplate.Main;
import org.bukkit.command.CommandExecutor;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class CommandManager {

    public void register() {
        String packageName = getClass().getPackage().getName();

        for (Class<?> classes : new Reflections(packageName).getSubTypesOf(CommandExecutor.class)) {
            try {
                CommandExecutor command = (CommandExecutor) classes
                        .getDeclaredConstructor()
                        .newInstance();
                Main.getInstance().getCommand(classes.getName().replace("Command", "")).setExecutor(command); // example, you name the class SetNameCommand, the command will be /setname.
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
