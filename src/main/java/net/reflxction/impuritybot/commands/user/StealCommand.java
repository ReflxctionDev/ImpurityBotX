package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.discord.Steal;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class StealCommand extends AbstractCommand {

    @Override
    public void process(JDA jda, Guild guild, Message message, MessageChannel channel, User user, String[] args) {
        if (args.length < 2) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        User target;
        int count;
        try {
            String id = StringUtils.mentionToId(args[0]);
            target = jda.getUserById(id);
        } catch (Exception e) {
            channel.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
            return;
        }
        try {
            count = Integer.parseInt(args[1]);
        } catch (Exception e) {
            channel.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
            return;
        }
        Steal steal = new Steal();
        steal.steal(guild.getMember(target), guild.getMember(user), count, channel);
    }

    @Override
    public String getCommand() {
        return "steal";
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getUsage() {
        return "-steal <@user> <credits>";
    }

    @Override
    public String getDescription() {
        return "20% chance to steal at most of 20% of the target user's" +
                " credits";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CREDITS;
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }
}
