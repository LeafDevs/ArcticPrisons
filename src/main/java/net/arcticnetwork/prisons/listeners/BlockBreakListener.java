package net.arcticnetwork.prisons.listeners;

import com.cryptomorin.xseries.XMaterial;
import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.PUpgrades;
import net.arcticnetwork.prisons.Utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockBreakListener extends Utils implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        PClass plr = Main.getPClass(e.getPlayer());
        if(plr.isInMine()) {
            plr.blockCount++;
            e.setDropItems(false);
            HashMap<PUpgrades.Upgrades, Integer> enchants = plr.getEnchants();
            int fortune = enchants.get(PUpgrades.Upgrades.FORTUNE);
            e.getBlock().getDrops(XMaterial.DIAMOND_PICKAXE.parseItem()).forEach(drop -> {
                if(plr.canAutoPickUp()) {
                    if(plr.canPickUpItem(drop)) {
                        for(int i = 0; i <= fortune; i++) {
                            plr.giveItem(drop);
                        }
                    }
                }
            });
            Location blockLoc = e.getBlock().getLocation();
            int ex = enchants.get(PUpgrades.Upgrades.EXPLOSION);
            ArrayList<Block> blocks = (ArrayList<Block>) getAllBlocksInArea(blockLoc, ex, ex, ex);
            for(Block b : blocks) {
                b.breakNaturally();
                for(int i = 0; i <= fortune; i++) {
                    plr.giveItem(XMaterial.matchXMaterial(b.getType()).parseItem());
                }
            }

        } else if(!plr.isJailed() && !plr.hasPermission("build") && !plr.buildMode) {
            e.setCancelled(true);
        }
    }

}
