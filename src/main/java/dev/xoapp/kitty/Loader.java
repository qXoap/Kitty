package dev.xoapp.kitty;

import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kitty.commands.AddModuleCommand;
import dev.xoapp.kitty.commands.ModuleCommand;
import dev.xoapp.kitty.commands.RemoveModuleCommand;
import dev.xoapp.kitty.data.DataManager;
import dev.xoapp.kitty.factory.ModuleFactory;

import java.util.ArrayList;
import java.util.List;

public class Loader extends PluginBase {

    private static Loader instance;
    private static DataManager dataManager;

    @Override
    public void onEnable() {
        instance = new Loader();
        dataManager = new DataManager();

        ModuleFactory.initialize();

        for (Command command : this.commands()) {
            getServer().getCommandMap().register(command.getName(), command);
        }

        getLogger().info(TextFormat.colorize("&aLoaded &e" + this.commands().size() + "&a Commands"));
    }

    @Override
    public void onDisable() {
        ModuleFactory.save();
    }

    public static Loader getInstance() {
        return instance;
    }

    public static DataManager getDataManager() {
        return dataManager;
    }

    private List<Command> commands() {
        List<Command> commands = new ArrayList<>();

        commands.add(new AddModuleCommand());
        commands.add(new ModuleCommand());
        commands.add(new RemoveModuleCommand());

        return commands;
    }
}
