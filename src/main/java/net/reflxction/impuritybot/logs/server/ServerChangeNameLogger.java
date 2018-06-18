package net.reflxction.impuritybot.logs.server;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNameEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class ServerChangeNameLogger extends Logger {

    @Override
    public void onGuildUpdateName(GuildUpdateNameEvent event) {
        final String old = event.getOldName();
        final String newname = event.getGuild().getName();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.YELLOW)
                .setAuthor(event.getGuild().getName(), null, event.getGuild().getIconUrl())
                .addField("Before", old)
                .addField("New", newname)
                .setFooter("Guild ID: " + event.getGuild().getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
