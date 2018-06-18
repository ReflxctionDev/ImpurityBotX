package net.reflxction.impuritybot.logs.message;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

import java.awt.*;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class MessageEditedLogger extends Logger {

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        final User u = event.getAuthor();
        final Message m = event.getMessage();
        final Color orange = new Color(255, 119, 0);
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setColor(orange)
                .setDescription("**Message edited in #" + event.getChannel().getName() + "**")
                .setFooter("ID: " + m.getId() + " • " + StringUtilsL.getTimeEST(), null)
                .addField("New content", m.getContentRaw()).build();

        getLogs().sendMessage(builder.build()).queue();
    }

}
