package net.reflxction.impuritybot.logs.roles;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleRemoveEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class RoleRemovedLogger extends Logger {

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        final User u = event.getUser();
        final Role r = event.getRoles().get(0);
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_RED)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("**<@" + u.getId() + "> was removed from the ** `" + r.getName() + "` role")
                .setFooter("User ID: " + u.getId() + " • " + StringUtils.getTimeEST(), null)
                .setThumbnail(u.getAvatarUrl())
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
