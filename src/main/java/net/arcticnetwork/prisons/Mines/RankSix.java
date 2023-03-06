package net.arcticnetwork.prisons.Mines;

import com.cryptomorin.xseries.XMaterial;
import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.enums.Ranks;
import org.bukkit.Material;

import java.util.ArrayList;

public class RankSix extends Mine{
    @Override
    public String getName() {
        return "hollowgrave";
    }

    @Override
    public Ranks getRank() {
        return Ranks.SIX;
    }

    @Override
    public ArrayList<Material> getBlocks() {
        ArrayList<Material> blocks = new ArrayList<>();
        blocks.add(XMaterial.IRON_ORE.parseMaterial());
        blocks.add(XMaterial.COAL_BLOCK.parseMaterial());
        blocks.add(XMaterial.IRON_BLOCK.parseMaterial());
        blocks.add(XMaterial.GOLD_ORE.parseMaterial());
        return blocks;
    }

}
