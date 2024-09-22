package dev.xoapp.kitty.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kitty.factory.ModuleFactory;
import dev.xoapp.kitty.module.WarpModule;

public class AddModuleCommand extends Command {

    public AddModuleCommand() {
        super("addmodule", "Add Warp Module");
        setAliases(new String[]{"addwarp", "addwarps"});
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!sender.isPlayer()) {
            return false;
        }

        Player player = sender.asPlayer();

        if (!player.hasPermission("warps.module.create")) {
            return false;
        }

        if (args[0].isEmpty()) {
            player.sendMessage(TextFormat.colorize("&cUse /addwarp (warpName)"));
            return false;
        }

        WarpModule warpModule = ModuleFactory.get(args[0]);

        if (warpModule != null) {
            player.sendMessage(TextFormat.colorize("&cThis warp apparently already exists"));
            return false;
        }

        ModuleFactory.add(new WarpModule(args[0], player.getLocation()));

        player.sendMessage(TextFormat.colorize("&aYou successfully added the warp &e" + args[0]));
        return true;
    }
}