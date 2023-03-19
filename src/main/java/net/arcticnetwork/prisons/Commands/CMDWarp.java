package net.arcticnetwork.prisons.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.PClass;
import net.arcticnetwork.prisons.Utils.Utils;

@SuppressWarnings("deprecation")
public class CMDWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if(arg0 instanceof Player) {
            Player plr = (Player) arg0;
            Iterator var01 = Main.mines.getConfigurationSection("mines").getKeys(false).iterator();
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder().getPath(), "mines.yml"));
            ArrayList<String> warpNames = new ArrayList<>();

            while(var01.hasNext()) {
                warpNames.add((String) var01.next());
            }
            for(String warp: warpNames) {
                if(warp == arg3[0]) {
                    int x = conf.getInt("mines." + warp + ".spawnpos.x");
                    int y = conf.getInt("mines." + warp + ".spawnpos.y");
                    int z = conf.getInt("mines." + warp + ".spawnpos.z");
                    World w = Bukkit.getWorld(conf.getString("mines." + warp + ".world"));
                    Location loc = new Location(w, x, y, z);
                    PClass pClass = Main.getPClass(plr);
                    if(!Utils.checkEmptyString(arg3[1])) {
                        if(!plr.hasPermission("arcticprisons.admin")) {
                            pClass.sendMessage("&f&lArctic&b&lPrison &8➣ &fYou do not have permission to teleport others to a mine.");
                            return false;
                        }
                        Player toTP = Bukkit.getPlayer(arg3[1]);
                        PClass tp = Main.getPClass(toTP);
                        tp.initTeleport(loc);
                        tp.setLocation(warp);
                        return true;
                    } else {
                        if(pClass.hasPermission("warps." + warp)) return false;
                        pClass.initTeleport(loc);
                        pClass.setLocation(warp);
                        return true;
                    }
                }
            }

        }
        


        return false;
    }
    
}
