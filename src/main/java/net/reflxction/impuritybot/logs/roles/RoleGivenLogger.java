package net.reflxction.impuritybot.logs.roles;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class RoleGivenLogger extends Logger {

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        final User u = event.getUser();
        final Role r = event.getRoles().get(0);
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.YELLOW)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("**<@" + u.getId() + "> was given the ** `" + r.getName() + "` role")
                .setFooter("User ID: " + u.getId() + " • " + StringUtilsL.getTimeEST(), null)
                .setThumbnail(u.getAvatarUrl())
                .build();
        getLogs().sendMessage(builder.build()).queue();
        if(r.getId().equals("413362035334840324")) {
            u.openPrivateChannel().complete().sendMessage("<#452333985419493376>\n" +
                    "\n" +
                    "Hey!\n" +
                    "\n" +
                    "Once again, thank you for applying to Impurity. I am pleased to tell you that you have been **accepted**! Congratulations! You will receive an invite into the guild promptly. \n" +
                    "\n" +
                    "Before we send you an invite to the guild, however, here are some ground rules: \n" +
                    "\n" +
                    "**Strictly no drama and toxicity** - Any form of harassment, toxicity, insults directed towards other people, suicide encouragement, and excessive/inappropriate tagging are not allowed under any circumstances and are punishable.\n" +
                    "\n" +
                    "**Follow the Rules of the Hypixel network** - If you intend to become a part of Impurity and of the Hypixel community, keep in mind to follow the Hypixel rules. This ensures that everyone isn’t getting bothered by someone else and creates a friendly community. Respect all other players, including allies.\n" +
                    "\n" +
                    "You can find a full list of our rules in . Once you have read all of the rules, DM/tag an officer or above and you will receive a guild invite in game.\n" +
                    "\n" +
                    "Want to join one of our teams? Check out the teams section of <#452333985419493376>. You will find more information about the teams there. You are strongly recommended and encouraged to join either one of the teams. You will not only be able to help the guild, but also participate in a fun and non-toxic community. You will also get special permissions and perks from joining a team! For example, Helpers are able to -warn and -kick and Events Team members are able to make announcements and polls. GvG team members represent the guild in GvGs and you’ll be able to interact with a lot of your guildmates.\n" +
                    "\n" +
                    "If teams aren’t really your thing, consider joining one of our events! Our events team hosts a number of events weekly. Past examples include Murder Mystery, a duels tournament, and skribbl.io. Events are announced regularly and you can usually find more information there.\n" +
                    "\n" +
                    "If you have any more questions, contact **Loki#0532**. Once again, welcome to our guild. Hope you enjoy your stay!").queue();
        }
    }

}
