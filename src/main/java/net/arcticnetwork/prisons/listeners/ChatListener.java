package net.arcticnetwork.prisons.listeners;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener extends Utils implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if(e.isCancelled()) {
            return;
        }
        e.setCancelled(true);
        PClass p = Main.getPClass(e.getPlayer());
        if(!Main.chatDisabled) {
            String msg;
            if(p.getPrestige() != 0) {
                msg = String.format("%s %s %s%s&7: %s", getPrestigePrefix(p.getPrestige()),p.getRank().getPrefix(), p.getServerrank().getPrefix(), p.toPlayer().getPlayer().getName(), e.getMessage());
            } else {
                msg = String.format("%s %s%s&7: %s", p.getRank().getPrefix(), p.getServerrank().getPrefix(), p.toPlayer().getPlayer().getName(), e.getMessage());
            }
            Bukkit.broadcastMessage(msg.replace("&", "\u00a7"));
        } else if(Main.chatDisabled && !p.hasPermission("chatbypass")) {
            p.sendMessage("&bChat is currently disabled!");
        }
    }

}
