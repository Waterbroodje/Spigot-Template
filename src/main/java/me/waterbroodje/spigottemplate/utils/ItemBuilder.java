package me.waterbroodje.spigottemplate.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private final ItemStack stack;

    public ItemBuilder(ItemStack stack) {
        this.stack = stack;
    }

    public ItemBuilder(Material material) {
        this.stack = new ItemStack(material);
    }

    public ItemBuilder(Material material, int amount) {
        this.stack = new ItemStack(material, amount);
    }

    public ItemBuilder clone() {
        return new ItemBuilder(stack);
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        stack.setAmount(amount);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        stack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        stack.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setGlowing() {
        stack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(new ArrayList<>(lore));
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStack toItemStack() {
        return stack;
    }
}