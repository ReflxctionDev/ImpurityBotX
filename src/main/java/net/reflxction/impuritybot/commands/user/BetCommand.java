package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.Bet;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class BetCommand extends AbstractCommand {

    @Override
    public String getCommand() {
        return "bet";
    }

    @Override
    public void process(JDA jda, Guild guild, Message message, MessageChannel channel, User user, String[] args) {
        if (args.length != 2) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        Member executor = guild.getMember(user);
        Member target;
        int amount;
        try {
            String id = StringUtils.mentionToId(args[0]);
            target = guild.getMember(jda.getUserById(id));
        } catch (Exception e) {
            channel.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
            return;
        }
        if (target == executor) {
            channel.sendMessage("You can't bet **yourself**").queue();
            return;
        }
        try {
            amount = Integer.parseInt(args[1]);
        } catch (Exception e) {
            channel.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
            return;
        }
        if (amount < 5) {
            channel.sendMessage("You can't bet with less than **5** credits").queue();
            return;
        }
        Bet bet = new Bet();
        bet.bet(executor, target, channel, amount);
    }

    @Override
    public String getUsage() {
        return "-bet <@user> <amount>";
    }

    @Override
    public String getDescription() {
        return "bets a user";
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
}
