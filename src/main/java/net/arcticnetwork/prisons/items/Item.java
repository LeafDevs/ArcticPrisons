package net.arcticnetwork.prisons.items;

import net.arcticnetwork.prisons.Utils.ItemBuilder;
import org.bukkit.entity.Player;

public abstract class Item {

    public Item() {
        Items.registerItem(this);
    }

    public abstract int getId();

    public abstract ItemBuilder getItemBuilder();

    public void onRightClick(Player p) {
        return;
    }

}
