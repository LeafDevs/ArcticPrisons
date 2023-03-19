package net.arcticnetwork.prisons.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.enums.Ranks;
import net.arcticnetwork.prisons.enums.ServerRank;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;


@SuppressWarnings("deprecation")
public class PClass extends Utils {



    public Player plr;

    public Ranks rank;

    public long prestige = 0;

    public ServerRank serverrank;

    public long blockCount;

    public String currentLocation = "spawn";
    public String plrUUID;

    public ArrayList<String> permissions = new ArrayList<>();

    public boolean currentlyTeleporting = false;
    public boolean autoPickUp = false;
    public boolean buildMode = false;
    public boolean muted = false;

    public PClass(Player plr, Ranks rank, long prestige, ServerRank serverrank, long blockCount, ArrayList<String> perms) {
        this.plr = plr;
        this.plrUUID = plr.getUniqueId().toString();
        this.rank  = rank;
        this.prestige = prestige;
        this.serverrank = serverrank;
        this.blockCount = blockCount;
        if(perms != null) {
            for(String s : perms) {
                permissions.add(s);
            }
        }
        Main.pClasses.put(plr, this);
    }

    public Player toPlayer() {
        return plr;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains("arcticprisons." + permission);
    }

    public boolean canAutoPickUp() {
        return autoPickUp;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isInventoryFull() {
        Inventory inv = toPlayer().getInventory();
        for (org.bukkit.inventory.ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }

    public boolean canPickUpItem(org.bukkit.inventory.ItemStack i) {
        Inventory inv = toPlayer().getInventory();
        for (org.bukkit.inventory.ItemStack item: inv.getContents()) {
            if(item == null || item == i) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setLocation(String s) {
        currentLocation = s;
    }

    public void giveItem(org.bukkit.inventory.ItemStack item) {
        plr.getInventory().addItem(item);
    }

    public HashMap<PUpgrades.Upgrades, Integer> getEnchants() {
        ItemStack stack = CraftItemStack.asNMSCopy(plr.getItemInHand());
        if (plr.getItemInHand() == null) return null;
        NBTTagCompound compound = stack.getTag();
        if (compound == null)
            return null;
        HashMap<PUpgrades.Upgrades, Integer> enchants = new HashMap<>();
        for(String key : compound.c()) {
            if(key.equals("LUCKY_MINER_LEVEL")) {
                enchants.put(PUpgrades.Upgrades.LUCKY_MINER, compound.getInt(key));
            } else if(key.equals("EXPLOSION_LEVEL")) {
                enchants.put(PUpgrades.Upgrades.EXPLOSION, compound.getInt(key));
            }else if(key.equals("FORTUNE_LEVEL")) {
                enchants.put(PUpgrades.Upgrades.FORTUNE, compound.getInt(key));
            }else if(key.equals("KEY_FINDER_LEVEL")) {
                enchants.put(PUpgrades.Upgrades.KEY_FINDER, compound.getInt(key));
            }else if(key.equals("EFFICIENCY")) {
                enchants.put(PUpgrades.Upgrades.EFFICIENCY, compound.getInt(key));
            }
        }
        return enchants;
    }

    public boolean isInMine() {
        if(plr.getWorld().getName().contains("mine")) {
            return true;
        }
        return false;
    }

    public boolean isJailed() {
        if(plr.getWorld().getName().contains("cells")) {
            return true;
        } else{
            return false;
        }
    }


    public boolean addPrestige() {
        try {
            this.prestige++;
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean setPrestige(int p) {
        try {
            this.prestige = p;
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean setRank(Ranks rank) {
        this.rank = rank;
        return true;
    }

    public boolean setRank(ServerRank rank) {
        this.serverrank = rank;
        return true;
    }

    public boolean initTeleport(Location loc) {
        if(currentlyTeleporting) {
            return false;
        }
        currentlyTeleporting = true;
        new BukkitRunnable() {
            int tptimer = 5;

            @Override
            public void run() {
                sendMessage("&f&lArctic&b&lPrison &8➣ &fYou will be teleported in &9" + tptimer);

                if(tptimer <= 0) {
                    plr.teleport(loc);
                    sendMessage("&f&lArctic&b&lPrison &8➣ &fYou have been successfully teleported!");
                    currentlyTeleporting = false;
                }
                if(tptimer >= 0) {
                    tptimer--;
                }
            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);
        return true;
    }


    public boolean sendMessage(String s) {
        if(plr.isOnline()) {
            plr.sendMessage(s.replace("&", "\u00a7"));
            return true;
        }
        return false;
    }

    // getters setters

    public void setPlr(Player plr) {
        this.plr = plr;
    }

    public String getPlrUUID() {
        return plrUUID;
    }

    public Ranks getRank() {
        return rank;
    }

    public long getPrestige() {
        return prestige;
    }

    public ServerRank getServerrank() {
        return serverrank;
    }
}
