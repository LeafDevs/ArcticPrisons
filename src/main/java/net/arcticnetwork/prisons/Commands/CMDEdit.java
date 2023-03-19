package net.arcticnetwork.prisons.Commands;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import net.arcticnetwork.prisons.enums.Ranks;
import net.arcticnetwork.prisons.enums.ServerRank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDEdit extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length >= 2) {
                PClass pClass = Main.getPClass(p);
                switch(args[0]) {
                    case "prestige":
                        pClass.prestige = Long.parseLong(args[1]);
                        pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &9Set your prestige to: " + args[1]);
                        break;
                    case "srank":
                        pClass.serverrank = ServerRank.getServerRankByID(args[1]);
                        pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &9Set your rank to: " + args[1]);
                        break;
                    case "rank":
                        pClass.rank =  Ranks.getFromName(args[1]);
                        pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &9Set your mining rank to: " + args[1]);
                        break;
                }


            }
        }
        return false;
    }
}
