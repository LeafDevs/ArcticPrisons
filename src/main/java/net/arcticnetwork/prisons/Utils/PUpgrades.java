package net.arcticnetwork.prisons.Utils;

public class PUpgrades {
    

    Upgrades upgrade;
    int level;

    public PUpgrades(Upgrades u, int l) {
        this.upgrade = u;
        this.level = l;
    }


    public void levelup() {
        this.level++;
    }

    public void setlevel(int level) {
        this.level = level;
    }


    public static int getExplosionBuff(int level) {
        return level; // skull just a method that returns the level itsself
    }



    public enum Upgrades {
        LUCKY_MINER(100, 2500, 2, "Lucky Miner"), // 50m to upgrade to max CODING IN AT A LATER DATE
        EFFICIENCY(10000, 500, 0.25, "Efficiency"), // 1.25e10 (12.5b) vanilla EFFICIENCY
        FORTUNE(1000, 50, 0.2, "Fortune"), // about 10m to get to max level
        KEY_FINDER(10, 25000, 2.25, "Key Finder"), // smth like 5.625m to get to max level  WILL CODE IN AT A LATER DATE
        EXPLOSION(5, 40000, 3.55, "Explosion"), // 3.55m to get to make level

        // total cost to max all: 12,569,175,000
        // time to get all those tokens: about 60 hours

        ;


        int maxlevel;
        int cost;
        double costmulti;
        String display;

        Upgrades(int maxlvl, int cost, double costmulti, String display) {
            this.maxlevel = maxlvl;
            this.cost = cost;
            this.costmulti = costmulti;
            this.display = display;
        }

        public int getLevelCost(int level) {
            double multi = (level * costmulti); 
            return (int) ((multi * cost) + cost);
        }

        public int getMaxLevel() {
            return maxlevel;
        }
    }


}
