package net.arcticnetwork.prisons.Commands;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDMutechat
        extends Utils
        implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            PClass p = Main.getPClass((Player) sender);
            if(p.hasPermission("helper")) {
                if(!Main.chatDisabled) {
                    Main.chatDisabled = true;
                    for(Player plr : Bukkit.getOnlinePlayers()) {
                        PClass a = Main.getPClass(plr);
                        a.sendMessage("&bChat has been muted by " + p.getServerrank().getPrefix() + " " + p.toPlayer().getName());
                    }
                } else {
                    Main.chatDisabled = false;
                    for(Player plr : Bukkit.getOnlinePlayers()) {
                        PClass a = Main.getPClass(plr);
                        a.sendMessage("&bChat has been un-muted!");
                    }
                }
            }
        }
        return false;
    }
}
