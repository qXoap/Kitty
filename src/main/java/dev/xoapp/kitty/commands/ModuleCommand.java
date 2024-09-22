package dev.xoapp.kitty.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kitty.factory.ModuleFactory;
import dev.xoapp.kitty.module.WarpModule;
import dev.xoapp.kitty.utils.Kitty;

public class ModuleCommand extends Command {

    public ModuleCommand() {
        super("warps", "Warp Module Command");
        setAliases(new String[]{"warp", "modules"});
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!sender.isPlayer()) {
            return false;
        }

        Player player = sender.asPlayer();

        if (args[0].isEmpty()) {
            player.sendMessage(TextFormat.colorize("&cUsage /warp (warp)"));
            return false;
        }

        if (args[0].equals("list")) {
            String appendModules = Kitty.appendModules();

            player.sendMessage(TextFormat.colorize("&aAvailable Modules: &f" + appendModules));
            return false;
        }

        WarpModule warpModule = ModuleFactory.get(args[0]);

        if (warpModule == null) {
            player.sendMessage(TextFormat.colorize("&cThis Warp Module doesn't exists"));
            return false;
        }

        player.teleport(warpModule.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.sendMessage(TextFormat.colorize("&aYou got successfully teleported to &e" + warpModule.getName()));
        return true;
    }
}
