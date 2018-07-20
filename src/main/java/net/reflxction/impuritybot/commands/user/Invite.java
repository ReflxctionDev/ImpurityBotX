package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class Invite extends AbstractCommand {
    @Override
    public String getCommand() {
        return "invite";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        event.getChannel().sendMessage("https://discord.gg/B7Xruae").queue();
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
        return "Get the official invite link for the Impurity discord server";
    }

    @Override
    public String getUsage() {
        return "-invite";
    }
}
