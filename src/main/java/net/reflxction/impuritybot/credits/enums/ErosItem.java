package net.reflxction.impuritybot.credits.enums;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.data.credits.CreditsManager;
import net.reflxction.impuritybot.main.ImpurityBot;

/**
 * Created by Reflxction, on 02/08/18.
 */
public enum ErosItem {

    IMPURE_CHEST("Impure Chest", 1000, "impurity_chest", "The Impurity loot chest which contains various items"),
    RICH_BOI("Rich Boi (Role)", 2000, "role_rich_boi", "The 'Rich Boi' role"),
    GANGSTER_ROLE("Gangster (Role)", 2500, "role_gangster", "The 'Gangster' role"),
    MONEY_BALLER("Money Baller (Role)", 3500, "role_moneyballer", "The 'Money Baller' role"),
    IMPURE_CAN("Impure Can", 500, "impurity_can", "Impurity can, contains various sodas which may contain either good or bad things!");

    private String item;
    private String id;
    private String description;

    private double price;


    ErosItem(String name, double price, String id, String description) {
        item = name;
        this.price = price;
        this.id = id;
        this.description = description;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static ErosItem getById(String id) {
        for (ErosItem item : ErosItem.values()) {
            if (item.getId().toLowerCase().equalsIgnoreCase(id.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    private static CreditsManager cu = new CreditsManager();

    public static void give(User u, ErosItem item) {
        if (item == ErosItem.IMPURE_CHEST) {
            cu.giveChest(u, 1);
        }
        if (item == ErosItem.GANGSTER_ROLE) {
            giveRole(u, "Gangster");
        }
        if (item == ErosItem.MONEY_BALLER) {
            giveRole(u, "Money Baller");
        }
        if (item == ErosItem.RICH_BOI) {
            giveRole(u, "Rich Boi");
        }
        if(item == ErosItem.IMPURE_CAN) {
            cu.giveCan(u, 1);
        }
        cu.setUserCredits(u, (int) (cu.getUserCredits(u) - item.getPrice()));

    }


    private static void giveRole(User u, String name) {
        ImpurityBot.getImpurityGuild().getController().addSingleRoleToMember(ImpurityBot.getImpurityGuild().getMember(u), ImpurityBot.getImpurityGuild().getRolesByName(name, true).get(0)).queue();
    }



}
