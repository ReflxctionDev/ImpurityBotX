package net.reflxction.impuritybot.logs.channel;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class ChannelCreatedLogger extends Logger {

    @Override
    public void onTextChannelCreate(TextChannelCreateEvent event) {
        final TextChannel channel = event.getChannel();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_BLUE)
                .setAuthor(event.getGuild().getName(), null, event.getGuild().getIconUrl())
                .setDescription("**Channel created: #" + channel.getName() + "**")
                    .setFooter("ID: " + channel.getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
