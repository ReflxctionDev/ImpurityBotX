package net.reflxction.impuritybot.logs.channel;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.events.channel.text.TextChannelDeleteEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class ChannelDeletedLogger extends Logger {

    @Override
    public void onTextChannelDelete(TextChannelDeleteEvent event) {
        final Channel c = event.getChannel();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.RED)
                .setAuthor(event.getGuild().getName(), null, event.getGuild().getIconUrl())
                .setDescription("**Channel Deleted: #**" + c.getName())
                .setFooter("ID: " + c.getId() + " • " + StringUtilsL.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
