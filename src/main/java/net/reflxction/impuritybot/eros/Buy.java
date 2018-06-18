package net.reflxction.impuritybot.eros;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.eros.ErosItem;
import net.reflxction.impuritybot.utils.data.CreditsManager;

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
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
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
        return CommandCategory.EROS;
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
