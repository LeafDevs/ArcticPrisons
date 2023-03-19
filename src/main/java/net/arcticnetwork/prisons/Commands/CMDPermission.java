package net.arcticnetwork.prisons.Commands;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDPermission extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.isOp()) {
                if(args.length == 1) {
                    if(Main.getPClass(p).permissions.contains(args[0])) {
                        Main.getPClass(p).permissions.remove(args[0]);
                        Main.getPClass(p).sendMessage("&f&lArctic&b&lPrison &8➣ &9Successfully removed the permission: " + args[0]);
                    } else {
                        Main.getPClass(p).permissions.add(args[0]);
                        Main.getPClass(p).sendMessage("&f&lArctic&b&lPrison &8➣ &9Successfully gave yourself the permission: " + args[0]);

                    }
                } else if(args.length == 2) {
                    PClass g = Main.getPClass(Bukkit.getPlayer(args[1]));
                    if(g.permissions.contains(args[0])) {
                        g.permissions.remove(args[0]);
                        Main.getPClass(p).sendMessage("&f&lArctic&b&lPrison &8➣ &9Successfully removed the permission: " + args[0]);
                    } else {
                        g.permissions.add(args[0]);
                        Main.getPClass(p).sendMessage("&f&lArctic&b&lPrison &8➣ &9Successfully gave yourself the permission: " + args[0]);

                    }
                }
            }
        }
        return false;
    }
}
