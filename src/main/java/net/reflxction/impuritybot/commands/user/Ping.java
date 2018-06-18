package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;

import java.awt.*;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class Ping extends AbstractCommand {
    @Override
    public String getCommand() {
        return "ping";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor("Pong!", null, j.getSelfUser().getAvatarUrl())
                .setDescription("\uD83D\uDC93 Took " + j.getPing() + "ms")
                .setColor(Color.GREEN)
                .build();
        c.sendMessage(builder.build()).queue();
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
        return "Get the bot's current ping";
    }

    @Override
    public String getUsage() {
        return "-ping";
    }
}
