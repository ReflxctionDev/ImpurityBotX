package net.reflxction.impuritybot.credits.enums;

/**
 * Created by Reflxction, on 02/08/18.
 */
public enum ChestItems {

    EXP("Exp", 3000, 5000, Rarity.RARE),
    CREDITS("Credits", 2000, 7000, Rarity.LEGENDARY),
    COMMAND_AKI("Aki Channel Access", Rarity.EPIC),
    SUPER_DICE("Super Dice", Rarity.RARE),
    IMPURE_CANS("Impure Cans", Rarity.COMMON);

    private String name;

    private double min;
    private double max;

    private Rarity rarity;

    ChestItems(String name, double min, double max, Rarity rarity) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.rarity = rarity;
    }

    ChestItems(String name, Rarity rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
