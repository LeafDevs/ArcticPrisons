package net.arcticnetwork.prisons.Utils;

import com.cryptomorin.xseries.*;
import com.mojang.authlib.*;
import com.mojang.authlib.properties.*;
import de.tr7zw.nbtapi.*;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.lang.reflect.*;
import java.util.*;

public class ItemBuilder {

    ItemStack im;

    public ItemBuilder(ItemStack item, short data) {
        im = new ItemStack(item.getType(), 1, data);
    }

    public ItemBuilder(ItemStack item, short data, int amount) {
        im = new ItemStack(item.getType(), amount, data);
    }

    public ItemBuilder(XMaterial material) {
        im = new ItemStack(material.parseMaterial(), 1, material.getData());
    }

    public ItemBuilder(XMaterial material, int amount) {
        im = new ItemStack(material.parseMaterial(), amount, material.getData());
    }

    public ItemStack build() {
        return im;
    }

    public ItemBuilder setSkullTexture(String url) {
        SkullMeta headMeta = (SkullMeta) im.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        im.setItemMeta(headMeta);
        return this;
    }


    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = this.im.getItemMeta();
        ArrayList<String> loreList = new ArrayList();
        for(String s : lore) {
            loreList.add(s.replace("&", "§"));
        }
        meta.setLore(loreList);
        im.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addGlow() {
        im.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        im.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder addUUID() {
        setString("UUID", UUID.randomUUID().toString());
        return this;
    }

    public ItemBuilder dyeArmor(int r, int g, int b) {
        if(im.getType().toString().startsWith("LEATHER_")) {
            LeatherArmorMeta meta = (LeatherArmorMeta) this.im.getItemMeta();
            meta.setColor(Color.fromRGB(r,g,b));
            im.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta meta = im.getItemMeta();
        meta.setUnbreakable(unbreakable);
        im.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        ItemMeta meta = this.im.getItemMeta();
        ArrayList<String> loreList = new ArrayList();
        for(String s : lore) {
            loreList.add(s.replace("&", "§"));
        }
        meta.setLore(loreList);
        im.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = this.im.getItemMeta();
        meta.setDisplayName(name.replace("&", "§"));
        im.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setString(String key, String value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setString(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setInt(String key, int value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setInteger(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setBoolean(String key, boolean value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setBoolean(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setDouble(String key, double value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setDouble(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setLong(String key, long value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setLong(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setByte(String key, byte value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setByte(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setShort(String key, short value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setShort(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setFloat(String key, float value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setFloat(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setByteArray(String key, byte[] value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setByteArray(key, value);
        im = nbti.getItem();
        return this;
    }

    public ItemBuilder setIntArray(String key, int[] value) {
        NBTItem nbti = new NBTItem(im);
        nbti.setIntArray(key, value);
        im = nbti.getItem();
        return this;
    }

}
