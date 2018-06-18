package net.reflxction.impuritybot.logs.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

import java.awt.*;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class UserJoinLogger extends Logger {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        final Color green = new Color(136, 255, 131);
        final User u = event.getUser();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(green)
                .setAuthor(event.getGuild().getName(), null, event.getGuild().getIconUrl())
                .setDescription("User joined")
                .addField("User info", "Name: " + u.getName() + "\nID: " + u.getId(), true)
                .setFooter("User ID: " + u.getId() + " • " + StringUtilsL.getTimeEST(), null)
                .setThumbnail(u.getAvatarUrl())
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
