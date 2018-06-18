package net.reflxction.impuritybot.logs.roles;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.role.RoleDeleteEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class RoleDeletedLogger extends Logger {

    @Override
    public void onRoleDelete(RoleDeleteEvent event) {
        final Role r = event.getRole();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_RED)
                .setAuthor("Role deleted", null, event.getGuild().getIconUrl())
                .setDescription("**Role has been deleted:** " + r.getName())
                .setFooter("Role ID: " + r.getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
