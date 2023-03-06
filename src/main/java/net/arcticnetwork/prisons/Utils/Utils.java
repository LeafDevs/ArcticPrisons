package net.arcticnetwork.prisons.Utils;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.enums.Ranks;
import net.arcticnetwork.prisons.enums.ServerRank;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.arcticnetwork.prisons.Main.plugin;

public class Utils {

    /**
     * Copied from KojiV's Developer Kit under the Apache License 2.0.
     * https://github.com/KojiV/DeveloperKit
     */
    public static Object getPrivateField(Object object, String field) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        return objectField.get(object);
    }

    public static boolean hasPlayerData(Player p) {
        File f = new File(Main.getPlugin().getDataFolder().getPath() + "/playerData", p.getUniqueId() + ".yml");
        return f.exists();
    }

    public static String getPrestigePrefix(long prestige) {
        if(prestige >= 0 && prestige <= 5) {
            return "&8[&7" + prestige + "&8]";
        } else
        if(prestige >= 6 && prestige <= 15) {
            return "&8[&3" + prestige + "&8]";
        }else
        if(prestige >= 16 && prestige <= 50) {
            return "&8[&2" + prestige + "&8]";
        }
        else
        if(prestige >= 51 && prestige <= 75) {
            return "&8[&a" + prestige + "&8]";
        }else
        if(prestige >= 76 && prestige <= 125) {
            return "&8[&9" + prestige + "&8]";
        }
        else
        if(prestige >= 126 && prestige <= 200) {
            return "&8[&b" + prestige + "&8]";
        }
        else
        if(prestige >= 201 && prestige <= 250) {
            return "&8[&d" + prestige + "&8]";
        }else
        if(prestige >= 251 && prestige <= 325) {
            return "&8[&6" + prestige + "&8]";
        } else
        if(prestige >= 326 && prestige <= 1000) {
            return "&8[&1" + prestige + "&8]";
        }
        return null;
    }

    public static Object getDataFromPlayerData(String path, Player p) {
        File f = new File(Main.getPlugin().getDataFolder().getPath() + "/playerData", p.getUniqueId() + ".yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(f);
        return conf.get(path);
    }

    public static YamlConfiguration getPlayerData(Player p) {
        File f = new File(Main.getPlugin().getDataFolder().getPath() + "/playerData", p.getUniqueId() + ".yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(f);
        return conf;
    }

    public static void createPlayerData(Player p) {
        File f = new File(Main.getPlugin().getDataFolder().getPath() + "/playerData", p.getUniqueId() + ".yml");
        try {
            f.createNewFile();
        } catch(Exception ignored) {}
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(f);
        conf.createSection("username");
        conf.createSection("rank");
        conf.createSection("stats.rank");
        conf.createSection("stats.prestige");
        conf.createSection("stats.blockCount");
        conf.createSection("permissions");
        conf.set("username", p.getName());
        conf.set("rank", ServerRank.DEFAULT.getId());
        conf.set("stats.rank", Ranks.ONE.getId());
        conf.set("stats.prestige", 0);
        conf.set("stats.blockCount", 0);
        conf.set("permissions", null);
        try {
            conf.save(f);
        } catch(Exception ignored) {}


    }

    public static boolean checkEmptyString(String s) {
        try {
            if(s == null || s.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
    }

    public static String calculateTime(long seconds) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) -
                TimeUnit.DAYS.toHours(day);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) -
                TimeUnit.DAYS.toMinutes(day) -
                TimeUnit.HOURS.toMinutes(hours);
        long second = TimeUnit.SECONDS.toSeconds(seconds) -
                TimeUnit.DAYS.toSeconds(day) -
                TimeUnit.HOURS.toSeconds(hours) -
                TimeUnit.MINUTES.toSeconds(minute);
        StringBuilder sb = new StringBuilder();
        if(day != 0) {
            sb.append(day + "d ");
        }
        if(hours != 0) {
            sb.append(hours + "h ");
        }
        if(minute != 0) {
            sb.append(minute + "m ");
        }
        if(second != 0) {
            sb.append(second + "s ");
        }
        return sb.toString();
    }

    public static List<Block> getAllBlocksInArea(Location loc, int x, int y, int z) {
        List<Block> blocks = new ArrayList<Block>();

        Location loc1 = new Location(loc.getWorld(), loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);
        Location loc2 = new Location(loc.getWorld(), loc.getBlockX() - x, loc.getBlockY() - y, loc.getBlockZ() - z);


        int topBlockX = (Math.max(loc1.getBlockX(), loc2.getBlockX()));
        int bottomBlockX = (Math.min(loc1.getBlockX(), loc2.getBlockX()));

        int topBlockY = (Math.max(loc1.getBlockY(), loc2.getBlockY()));
        int bottomBlockY = (Math.min(loc1.getBlockY(), loc2.getBlockY()));

        int topBlockZ = (Math.max(loc1.getBlockZ(), loc2.getBlockZ()));
        int bottomBlockZ = (Math.min(loc1.getBlockZ(), loc2.getBlockZ()));

        for(int x2 = bottomBlockX; x2 <= topBlockX; x2++)
        {
            for(int z2 = bottomBlockZ; z2 <= topBlockZ; z2++)
            {
                for(int y2 = bottomBlockY; y2 <= topBlockY; y2++)
                {
                    Block block = loc1.getWorld().getBlockAt(x2, y2, z2);

                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    public static List<Block> getAllBlocksInArea(Location loc1, Location loc2) {
        List<Block> blocks = new ArrayList<Block>();

        int topBlockX = (Math.max(loc1.getBlockX(), loc2.getBlockX()));
        int bottomBlockX = (Math.min(loc1.getBlockX(), loc2.getBlockX()));

        int topBlockY = (Math.max(loc1.getBlockY(), loc2.getBlockY()));
        int bottomBlockY = (Math.min(loc1.getBlockY(), loc2.getBlockY()));

        int topBlockZ = (Math.max(loc1.getBlockZ(), loc2.getBlockZ()));
        int bottomBlockZ = (Math.min(loc1.getBlockZ(), loc2.getBlockZ()));

        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);

                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    public static Material pickRandomBlock(ArrayList<Material> mats) {
        return mats.get((int) (Math.random() * mats.size()));
    }

    public static void runTaskAfter(Runnable runnable, int delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskLater(plugin, delay);
    }

    public static void log(Object s) {
        Main.log.info("[Arctic Prisons] " +  s);
    }

    public static void fillBox(Location pos1, Location pos2, ArrayList<Material> blocks) {
        for(Block b : getAllBlocksInArea(pos1, pos2)) {
            runTaskAfter(() -> b.setType(pickRandomBlock(blocks)), 2);
        }
    }
}
