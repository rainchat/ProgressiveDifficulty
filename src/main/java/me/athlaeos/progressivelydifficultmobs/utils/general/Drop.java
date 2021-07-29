package me.athlaeos.progressivelydifficultmobs.utils.general;

import org.bukkit.inventory.ItemStack;

public class Drop {
    private ItemStack item; //item dropped
    private double dropChance; //chance to trigger this item to be dropped
    private double dropChanceLootingBonus; //chance increased per level of looting to drop item
    private int minAmountDrop; //minimum items dropped if item dropped
    private int maxAmountDrop; //maximum items dropped if item dropped
    private int minAmountDropLootingBonus; //minimum items dropped increased per level of looting
    private int maxAmountDropLootingBonus; //maximum items dropped increased per level of looting

    public Drop(ItemStack item, double dropChance, double dropChanceLootingBonus, int minAmountDrop, int minAmountDropLootingBonus, int maxAmountDrop, int maxAmountDropLootingBonus) {
        this.item = item;
        this.dropChance = dropChance;
        this.dropChanceLootingBonus = dropChanceLootingBonus;
        this.minAmountDrop = minAmountDrop;
        this.minAmountDropLootingBonus = minAmountDropLootingBonus;
        this.maxAmountDrop = maxAmountDrop;
        this.maxAmountDropLootingBonus = maxAmountDropLootingBonus;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public double getDropChance() {
        return dropChance;
    }

    public void setDropChance(double dropChance) {
        this.dropChance = dropChance;
    }

    public double getDropChanceLootingBonus() {
        return dropChanceLootingBonus;
    }

    public void setDropChanceLootingBonus(double dropChanceLootingBonus) {
        this.dropChanceLootingBonus = dropChanceLootingBonus;
    }

    public int getMaxAmountDrop() {
        return maxAmountDrop;
    }

    public void setMaxAmountDrop(int maxAmountDrop) {
        this.maxAmountDrop = maxAmountDrop;
    }

    public int getMaxAmountDropLootingBonus() {
        return maxAmountDropLootingBonus;
    }

    public void setMaxAmountDropLootingBonus(int maxAmountDropLootingBonus) {
        this.maxAmountDropLootingBonus = maxAmountDropLootingBonus;
    }

    public int getMinAmountDrop() {
        return minAmountDrop;
    }

    public void setMinAmountDrop(int minAmountDrop) {
        this.minAmountDrop = minAmountDrop;
    }

    public int getMinAmountDropLootingBonus() {
        return minAmountDropLootingBonus;
    }

    public void setMinAmountDropLootingBonus(int minAmountDropLootingBonus) {
        this.minAmountDropLootingBonus = minAmountDropLootingBonus;
    }
}
