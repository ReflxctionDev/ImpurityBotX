package net.reflxction.impuritybot.credits.enums;

/**
 * Created by Reflxction, on 02/08/18.
 */
public enum Rarity {

    COMMON("Common", 1, 40),

    RARE("Rare", 2, 30),

    EPIC("Epic", 3, 20),
    LEGENDARY("Legendary", 4, 10);

    private String rarity;

    private int slot;

    private int percentage;

    Rarity(String rarity, int slot, int percentage) {
        this.rarity = rarity;
        this.slot = slot;
        this.percentage = percentage;
    }

    public String getRarity() {
        return rarity;
    }

    public int getSlot() {
        return slot;
    }

    public int getPercentage() {
        return percentage;
    }
}
