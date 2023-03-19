package net.arcticnetwork.prisons.Commands;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Mines.Mine;
import net.arcticnetwork.prisons.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDReset extends Utils implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player plr = (Player) sender;
            if(!plr.hasPermission("arcticprisons.admin")) {
                Main.getPClass(plr).sendMessage("&f&lArctic&b&lPrison &8➣&f You do not have permission to run this command!");
                return false;
            }
            if(args.length == 1) {
                Mine.getMineFromName(args[0]).reset();
            } else {
                for(Mine m : Main.mine) {
                    m.resetSilent();
                }
            }
            return true;
        }
        return false;
    }
}
