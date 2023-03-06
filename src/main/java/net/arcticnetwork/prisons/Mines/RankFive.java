package net.arcticnetwork.prisons.Mines;

import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.enums.Ranks;
import org.bukkit.Material;

import java.util.ArrayList;

public class RankFive extends Mine{
    @Override
    public String getName() {
        return "duchil";
    }

    @Override
    public Ranks getRank() {
        return Ranks.FIVE;
    }

    @Override
    public ArrayList<Material> getBlocks() {
        ArrayList<Material> mats = new ArrayList<>();
        mats.add(Material.COAL_ORE);
        mats.add(Material.IRON_ORE);
        mats.add(Material.COAL_BLOCK);
        return mats;
    }



}
