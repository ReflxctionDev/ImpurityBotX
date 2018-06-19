package net.reflxction.impuritybot.logs.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class UserLeaveLogger extends Logger {

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        final User u = event.getUser();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_RED)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("User left")
                .addField("User info", "Name: " + u.getName() + "\nID: " + u.getId())
                .setThumbnail(u.getAvatarUrl())
                .setFooter("ID: " + u.getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
