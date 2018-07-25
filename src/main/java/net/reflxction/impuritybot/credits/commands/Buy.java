package net.reflxction.impuritybot.credits.commands;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.credits.enums.ErosItem;
import net.reflxction.impuritybot.data.credits.CreditsManager;
import net.reflxction.impuritybot.events.commands.CommandEvent;

/**
 * Created by Reflxction, on 02/08/18.
 */
public class Buy extends AbstractCommand {

    private CreditsManager cu = new CreditsManager();

    @Override
    public String getCommand() {
        return "buy";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        User u = event.getMember().getUser();
        TextChannel c = ((TextChannel) event.getChannel());
        if (args.length == 0) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
        }
        if (args.length == 1) {
            if (ErosItem.getById(args[0]) == null) {
                c.sendMessage("**Invalid item. Make sure to get the ID correctly from -menu!**").queue();
            } else {
                if (cu.canBuyItem(u, ErosItem.getById(args[0]))) {
                    ErosItem.give(u, ErosItem.getById(args[0]));
                    c.sendMessage("You've successfully purchased **" + ErosItem.getById(args[0]).getItem() + "**.").queue();
                } else {
                    c.sendMessage("**You don't have enough credits to buy this item!** Required: **" + ErosItem.getById(args[0]).getPrice() + "**, while you have: **" + cu.getUserCredits(u) + "**").queue();
                }
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CREDITS;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Buy something... i guess";
    }

    @Override
    public String getUsage() {
        return "-buy <item id>";
    }
}
