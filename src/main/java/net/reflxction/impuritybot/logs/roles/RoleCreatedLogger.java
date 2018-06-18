package net.reflxction.impuritybot.logs.roles;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.role.RoleCreateEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class RoleCreatedLogger extends Logger {

    @Override
    public void onRoleCreate(RoleCreateEvent event) {
        final Role r = event.getRole();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_GREEN)
                .setAuthor("Role created", null, event.getGuild().getIconUrl())
                .setDescription("**A new role has been created:** " + r.getName())
                .addField("Color", "#" + r.getColor().getRGB())
                .addField("ID", r.getId())
                .setFooter("Role ID: " + r.getId() + " • " + StringUtilsL.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
