package net.arcticnetwork.prisons;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.logging.Logger;

import net.arcticnetwork.prisons.Commands.*;
import net.arcticnetwork.prisons.Mines.*;
import net.arcticnetwork.prisons.listeners.BlockBreakListener;
import net.arcticnetwork.prisons.listeners.ChatListener;
import net.arcticnetwork.prisons.listeners.PlayerJoinListener;
import net.arcticnetwork.prisons.runnables.MineResetRunnable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.arcticnetwork.prisons.Utils.PClass;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static Plugin getPlugin() {
        return (Plugin) plugin;
    }

    public static YamlConfiguration mines;
    public static MineResetRunnable mrs;

    public static boolean chatDisabled = false;


    public static final Logger log = Logger.getLogger("Minecraft");

    private static Economy tokens = null;



    public static HashMap<Player, PClass> pClasses = new HashMap<>();
    public static ArrayList<Mine> mine = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        File playerDataFolder = new File(getDataFolder().getPath() + "/playerData");
        if(!playerDataFolder.exists()) playerDataFolder.mkdirs();

        mrs = new MineResetRunnable();
        mines = YamlConfiguration.loadConfiguration(new File(getDataFolder().getPath(), "mines.yml"));
        initCommandAndEvents();

    }


    private void initCommandAndEvents() {
        // commands
        getCommand("warp").setExecutor(new CMDWarp());
        getCommand("reset").setExecutor(new CMDReset());
        getCommand("mutechat").setExecutor(new CMDMutechat());
        getCommand("build").setExecutor(new CMDBuild());
        getCommand("permission").setExecutor(new CMDPermission());
        getCommand("edit").setExecutor(new CMDEdit());
        // events
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        // Mines
        new RankOne();
        new RankTwo();
        new RankThree();
        new RankFour();
        new RankFive();
        new RankSix();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        tokens = rsp.getProvider();
        return tokens != null;
    }



    public static Economy getTokens() {
        return tokens;
    }

    
    public static PClass getPClass(Player p) {
        return pClasses.get(p);
    }

    
}
