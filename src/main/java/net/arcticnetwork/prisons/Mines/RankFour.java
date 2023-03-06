package net.arcticnetwork.prisons.Mines;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.enums.Ranks;
import org.bukkit.Material;

import java.util.ArrayList;

public class RankFour extends Mine{
    @Override
    public String getName() {
        return "crystalcove";
    }

    @Override
    public Ranks getRank() {
        return Ranks.FOUR;
    }

    @Override
    public ArrayList<Material> getBlocks() {
        ArrayList<Material> mats = new ArrayList<>();
        mats.add(Material.STONE);
        mats.add(Material.COAL_ORE);
        mats.add(Material.IRON_ORE);
        return mats;
    }


}
