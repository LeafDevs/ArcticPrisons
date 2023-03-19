package net.arcticnetwork.prisons.items.pickaxes;

import com.cryptomorin.xseries.XMaterial;
import net.arcticnetwork.prisons.Utils.ItemBuilder;
import net.arcticnetwork.prisons.items.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StarterPickaxe extends Item {


    @Override
    public int getId() {
        return 1;
    }

    @Override
    public ItemBuilder getItemBuilder() {
        ItemBuilder ib = new ItemBuilder(XMaterial.WOODEN_PICKAXE);
        ib.setName("&7Starter Pickaxe");
        ib.setLore("&7The starter pickaxe", " ", "&7Common Pickaxe");
        ib.setUnbreakable(true);
        return ib;
    }
}
