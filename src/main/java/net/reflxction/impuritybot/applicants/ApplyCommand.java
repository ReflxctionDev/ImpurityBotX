package net.reflxction.impuritybot.applicants;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.applicants.Apply;
import net.reflxction.impuritybot.applicants.PlayerInSessionException;
import net.reflxction.impuritybot.applicants.PlayerNotExistsException;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;

public class ApplyCommand extends AbstractCommand {

    @Override
    public String getCommand() {
        return "apply";
    }

    @Override
    public void process(JDA jda, Guild guild, Message message, MessageChannel channel, User user, String[] args) {
        if (!channel.equals(user.openPrivateChannel().complete())) {
            channel.sendMessage("**Please head on to my pm and run the apply command there**").queue();
            return;
        }
        if (args.length != 1) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        try {
            Apply apply = new Apply(user, args[0]);
            apply.apply();
        } catch (PlayerNotExistsException e) {
            channel.sendMessage("**This is not a valid player name**").queue();
            return;
        } catch (PlayerInSessionException e) {
            channel.sendMessage("**You are in a session!** Please wait").queue();
            return;
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
        return "Applies and sends the application to the events team";
    }

    @Override
    public String getUsage() {
        return "-apply <ign>";
    }
}