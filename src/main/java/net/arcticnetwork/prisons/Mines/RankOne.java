package net.arcticnetwork.prisons.Mines;

import java.util.ArrayList;

import net.arcticnetwork.prisons.Main;
import org.bukkit.Material;

import com.cryptomorin.xseries.XMaterial;

import net.arcticnetwork.prisons.enums.Ranks;

public class RankOne extends Mine {

    @Override
    public String getName() {
        return "dirtbay";
    } // must always be lower case

    @Override
    public Ranks getRank() {
        return Ranks.ONE;
    }

    @Override
    public ArrayList<Material> getBlocks() {
        ArrayList<Material> blocks = new ArrayList<>();
        blocks.add(XMaterial.OAK_LOG.parseMaterial());
        blocks.add(XMaterial.STONE.parseMaterial());
        blocks.add(XMaterial.OAK_WOOD.parseMaterial());
        return blocks;
    }



}
