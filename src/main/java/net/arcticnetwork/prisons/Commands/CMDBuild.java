package net.arcticnetwork.prisons.Commands;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDBuild extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            PClass plr = Main.getPClass((Player) sender);
            if(plr.hasPermission("build")) {
                if(plr.buildMode) {
                    plr.buildMode = false;
                    plr.sendMessage("&f&lArctic&b&lPrison &8➣ &9Disabled &fBuild mode!");
                } else {
                    plr.buildMode = true;
                    plr.sendMessage("&f&lArctic&b&lPrison &8➣ &9Enabled &fBuild mode!");
                }
                return true;
            }
        }
        return false;
    }
}
