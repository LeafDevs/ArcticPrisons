package net.arcticnetwork.prisons.items.misc;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import net.arcticnetwork.prisons.Main;
import net.arcticnetwork.prisons.Utils.ItemBuilder;
import net.arcticnetwork.prisons.items.Item;
import org.bukkit.entity.Player;

public class Token extends Item {


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public ItemBuilder getItemBuilder() {
        ItemBuilder ib = new ItemBuilder(XMaterial.EMERALD);
        ib.setName("&aClaim Tokens");
        ib.addGlow();
        ib.addUUID();
        ib.setInt("tokens-amount", 5000);
        return ib;
    }

    @Override
    public void onRightClick(Player p) {
        if(p.getInventory().getItemInMainHand().getType()
                == getItemBuilder().build().getType() &&
                p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                        getItemBuilder().build().getItemMeta().getDisplayName()
                )
        ) {
            NBTContainer item = NBTItem.convertItemtoNBT(p.getInventory().getItemInMainHand());
            int tokens = item.getInteger("tokens-amount");
            Main.getTokens().depositPlayer(p, tokens);
            p.getInventory().remove(p.getInventory().getItemInMainHand());
        }
    }
}
