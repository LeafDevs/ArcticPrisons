package net.arcticnetwork.prisons.items;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Items {

    public static HashMap<Integer, Item> items = new HashMap<>();

    public static void registerItem(Item item) {
        items.put(item.getId(), item);
    }

    public static ItemStack getItem(int id) {
        return items.get(id).getItemBuilder().build();
    }

}
