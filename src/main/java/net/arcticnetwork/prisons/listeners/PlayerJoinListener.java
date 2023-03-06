package net.arcticnetwork.prisons.listeners;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import net.arcticnetwork.prisons.enums.Ranks;
import net.arcticnetwork.prisons.enums.ServerRank;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class PlayerJoinListener extends Utils implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(hasPlayerData(e.getPlayer())) {
            Player p = e.getPlayer();
            Ranks rank = Ranks.getFromName((String) getDataFromPlayerData("stats.rank", p));
            ServerRank sRank = ServerRank.getServerRankByID((String) getDataFromPlayerData("rank", p));
            int prestige = (int) getDataFromPlayerData("stats.prestige", p);
            int blocksBroken = (int) getDataFromPlayerData("stats.blockCount", p);
            ArrayList<String> perms = (ArrayList<String>) getDataFromPlayerData("permissions", p);
            new PClass(p, rank, prestige, sRank, blocksBroken, perms);
        } else {
            createPlayerData(e.getPlayer());
            new PClass(e.getPlayer(), Ranks.ONE,0, ServerRank.DEFAULT, 0, null);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if(hasPlayerData(e.getPlayer())) {
            Player p = e.getPlayer();
            PClass pClass = Main.getPClass(p);
            long prestige = pClass.prestige;
            long blocksBroken = pClass.blockCount;
            ServerRank srank = pClass.serverrank;
            Ranks rank = pClass.rank;
            ArrayList<String> permissions = pClass.permissions;
            YamlConfiguration conf = getPlayerData(p);
            conf.set("stats.prestige", prestige);
            conf.set("stats.blockCount", blocksBroken);
            conf.set("stats.rank", rank.getId());
            conf.set("rank", srank.getId());
            conf.set("username", p.getName());
            conf.set("permissions", permissions);
            try {
                conf.save(new File(Main.getPlugin().getDataFolder().getPath() + "/playerData", p.getUniqueId() + ".yml"));
            } catch (Exception ignored) {}
        } else {
            log("Error saving player data! player data file does not exist for " + e.getPlayer().getName());
        }
    }

}
