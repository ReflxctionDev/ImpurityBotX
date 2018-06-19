package net.reflxction.impuritybot.logs.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class UserBanLogger extends Logger {

    @Override
    public void onGuildBan(GuildBanEvent event) {
        final User u = event.getUser();
        final Guild g = event.getGuild();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.RED)
                .setDescription("User " + u.getName() + " has been banned")
                .setThumbnail(u.getAvatarUrl())
                .setFooter("User ID: " + u.getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
