package net.reflxction.impuritybot.eros;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.data.CreditsManager;

/**
 * Created by Reflxction, on 02/05/18.
 */
public class Balance extends AbstractCommand {

    private final CreditsManager cc = new CreditsManager();

    @Override
    public String getCommand() {
        return "balance";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length == 0) {
            int balance = cc.getUserCredits(u);
            c.sendMessage("Current balance for **" + u.getName() + "**: **" + balance + "**").queue();
        }
        if (args.length == 1) {
            try {
                User target = j.getUserById(StringUtils.mentionToId(args[0]));
                int balance = cc.getUserCredits(target);
                c.sendMessage("Current balance for **" + target.getName() + "**: **" + balance + "**").queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**Invalid arguments! Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get balance (of yourself or someone else)";
    }

    @Override
    public String getUsage() {
        return "-balance / -balance <@user/id>";
    }
}
