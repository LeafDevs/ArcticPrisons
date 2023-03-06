package net.arcticnetwork.prisons.Mines;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.enums.Ranks;

public abstract class Mine extends Utils{

    public Mine() {
        register();
    }
    
    public abstract String getName(); // must always be lower case

    public abstract Ranks getRank();

    public abstract ArrayList<Material> getBlocks();

    public void register() {
        Main.mine.add(this);
    }

    public void resetSilent() {
        try {
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder().getPath(), "mines.yml"));
            Location pos1 = new Location(
                    Bukkit.getWorld(conf.getString("mines." + getName() + ".world")),
                    conf.getInt("mines." + getName() + ".pos1.x"),
                    conf.getInt("mines." + getName() + ".pos1.y"),
                    conf.getInt("mines." + getName() + ".pos1.z")
            );
            Location pos2 = new Location(
                    Bukkit.getWorld(conf.getString("mines." + getName() + ".world")),
                    conf.getInt("mines." + getName() + ".pos2.x"),
                    conf.getInt("mines." + getName() + ".pos2.y"),
                    conf.getInt("mines." + getName() + ".pos2.z")
            );
            Location spawn = new Location(
                    Bukkit.getWorld(conf.getString("mines." + getName() + ".world")),
                    conf.getInt("mines." + getName() + ".spawnpos.x"),
                    conf.getInt("mines." + getName() + ".spawnpos.y"),
                    conf.getInt("mines." + getName() + ".spawnpos.z")
            );
            Utils.fillBox(pos1, pos2, getBlocks());
            for(Player p : Bukkit.getOnlinePlayers()) {
                PClass pClass = Main.getPClass(p);
                if(pClass.isInMine() && pClass.getCurrentLocation() == getName()) {
                    p.teleport(spawn);
                }
            }
        } catch(Exception e) {
            log("Failed to reset mine. One of the positions are invalid!");
        }
    }

    public void reset() {
        try {
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder().getPath(), "mines.yml"));
            Location pos1 = new Location(
                    Bukkit.getWorld(conf.getString("mines." + getName() + ".world")),
                    conf.getInt("mines." + getName() + ".pos1.x"),
                    conf.getInt("mines." + getName() + ".pos1.y"),
                    conf.getInt("mines." + getName() + ".pos1.z")
            );
            Location pos2 = new Location(
                    Bukkit.getWorld(conf.getString("mines." + getName() + ".world")),
                    conf.getInt("mines." + getName() + ".pos2.x"),
                    conf.getInt("mines." + getName() + ".pos2.y"),
                    conf.getInt("mines." + getName() + ".pos2.z")
            );
            Utils.fillBox(pos1, pos2, getBlocks());
            for(Player p : Bukkit.getOnlinePlayers()) {
                PClass pClass = Main.getPClass(p);
                pClass.sendMessage("&b[&fArctic Prisons&b] &fMine " + getName() + " was reset!");
            }
        } catch(Exception e) {
            log("Failed to reset mine. One of the positions are invalid!");
        }

    }



    public static Mine getMineFromName(String s) {
        for(Mine m : Main.mine) {
            System.out.println(m.getName());
            if (m.getName() == s) {
                return m;
            }
        }
        return null;
    }

}
