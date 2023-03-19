package net.arcticnetwork.prisons.listeners;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import net.arcticnetwork.prisons.enums.Ranks;
import net.arcticnetwork.prisons.enums.ServerRank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

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
        // scoreboard

        final Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            public void run() {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                final Scoreboard board = manager.getNewScoreboard();
                final Objective objective = board.registerNewObjective("test", "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(colorText("&f&lArctic&b&lPrisons"));
                Score score = objective.getScore(colorText("  &eBalance: &6" + Utils.serializeNumber((int) Main.getMoney().getBalance(p))));
                score.setScore(6);
                Score score1 = objective.getScore(colorText("  &aTokens: &2" + Utils.serializeNumber((int) Main.getTokens().getBalance(p))));
                score1.setScore(5);
                Score score2 = objective.getScore(colorText("  &7Rank: " + Main.getPClass(p).getServerrank().getPrefix()));
                score2.setScore(9);
                Score score3 = objective.getScore(colorText("  &7Name: &e" + p.getName()));
                score3.setScore(8);
                Score score4 = objective.getScore(colorText("  &7Mine: " + Main.getPClass(p).getRank().getId()));
                score4.setScore(4);
                Score score34 = objective.getScore(colorText("  &7Prestige: " + Main.getPClass(p).getPrestige()));
                score34.setScore(4);
                Score score5 = objective.getScore(colorText("&eplay.arcticprisons.net"));
                score5.setScore(1);
                Score dummyScore1 = objective.getScore("  ");
                Score dummyScore2 = objective.getScore("   ");
                Score dummyScore3 = objective.getScore(" ");
                dummyScore1.setScore(10);
                dummyScore2.setScore(7);
                dummyScore3.setScore(2);

                p.setScoreboard(board);
            }
        },0, 20 * 10);

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
