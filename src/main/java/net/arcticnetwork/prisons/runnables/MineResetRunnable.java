package net.arcticnetwork.prisons.runnables;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Mines.Mine;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class MineResetRunnable extends BukkitRunnable {

    public MineResetRunnable() {
        runTaskTimer(Main.getPlugin(), 0L, 36000);
    }

    @Override
    public void run() {
        for(Mine m : Main.mine) {
            m.resetSilent();
        }
    }
}
