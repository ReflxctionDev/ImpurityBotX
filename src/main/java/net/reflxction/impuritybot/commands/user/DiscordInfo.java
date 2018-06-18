package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;

import java.awt.*;
import java.time.OffsetDateTime;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class DiscordInfo extends AbstractCommand {

    @Override
    public String getCommand() {
        return "discordinfo";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        final String name = g.getName();
        final Member owner = g.getOwner();
        final OffsetDateTime date = g.getCreationTime();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor(name, null, g.getIconUrl())
                .setColor(Color.GREEN).setThumbnail(g.getIconUrl())
                .addField("Server Info", "**Name**: " + g.getName() + "\n**ID**: " + g.getId() + "\n**Members**: " + g.getMembers().size() + "\n**Owner**:" + owner.getUser().getName() + "\n**Date created**: " + date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear())
                .addField("Guild Info", "**Name**: Impurity\n**Guild Master**: Inariiiii\n**Guild Tag**: [IMPURE]\n**Date created**: 8th of Sep. 2017\n**Guild Texture Pack**: https://www.youtube.com/watch?v=XPVD5yqoENI (by <@275609695664734208>)")
                .addField("Useful links",  "**YouTube**: https://www.youtube.com/channel/UCCytGYl6gs58f-8FG8hoT1w\n**Twitter**: https://twitter.com/impurityguild\n**Forums thread**: https://hypixel.net/threads/impurity-impure-notthatpure-low-req-bedwars-guilds.1376085/")
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
        return "Get information about the Discord guild";
    }

    @Override
    public String getUsage() {
        return "-discordinfo";
    }
}
