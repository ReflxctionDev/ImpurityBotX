package net.reflxction.impuritybot.logs.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class UserNickLogger extends Logger {

    @Override
    public void onGuildMemberNickChange(GuildMemberNickChangeEvent event) {
        try {
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setDescription("**Nick changed**")
                    .setColor(Color.pink)
                    .addField("Before", event.getPrevNick())
                    .addField("After", event.getNewNick())
                    .setFooter("User ID: " + event.getUser().getId() + " • " + StringUtilsL.getTimeEST(), null)
                    .build();
            getLogs().sendMessage(builder.build()).queue();
        } catch (IllegalArgumentException ex) {

        }
    }

}
