package net.arcticnetwork.prisons.Commands;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import net.arcticnetwork.prisons.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDGet extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                PClass pClass = Main.getPClass(p);
                if(!pClass.hasPermission("admin")) {
                    pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &cYou do not have permission to run this command!");
                }
                int id = Integer.parseInt(args[0]);
                pClass.giveItem(Items.getItem(id));
            } else if (args.length == 2) {
                PClass pClass = Main.getPClass(p);
                if(!pClass.hasPermission("admin")) {
                    pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &cYou do not have permission to run this command!");
                }
                PClass reciever = Main.getPClass(Bukkit.getPlayer(args[1]));
                int id = Integer.parseInt(args[0]);
                pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &9Successfully given the item with the id: " + id + "to " + reciever.getServerrank().getPrefix() + reciever.toPlayer().getName());
                reciever.sendMessage("&f&lArctic&b&lPrison &8➣ &9Recieved an item from an admin");
                reciever.giveItem(Items.getItem(id));
            }
        }
        return false;
    }
}
