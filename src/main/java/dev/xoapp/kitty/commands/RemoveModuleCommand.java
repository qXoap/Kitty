package dev.xoapp.kitty.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kitty.factory.ModuleFactory;
import dev.xoapp.kitty.module.WarpModule;

public class RemoveModuleCommand extends Command {

    public RemoveModuleCommand() {
        super("removemodule", "Remove Warp Module");
        setAliases(new String[]{"removewarp", "removewarps"});
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!sender.isPlayer()) {
            return false;
        }

        Player player = sender.asPlayer();

        if (!player.hasPermission("warps.module.remove")) {
            return false;
        }

        if (args[0].isEmpty()) {
            player.sendMessage(TextFormat.colorize("&cUse /removewarp (warp)"));
            return false;
        }

        WarpModule warpModule = ModuleFactory.get(args[0]);

        if (warpModule == null) {
            player.sendMessage(TextFormat.colorize("&cThis warp apparently doesn't exists"));
            return false;
        }

        ModuleFactory.delete(warpModule.getName());
        player.sendMessage(TextFormat.colorize("&aYou successfully removed the warp &e" + warpModule.getName()));
        return true;
    }
}
