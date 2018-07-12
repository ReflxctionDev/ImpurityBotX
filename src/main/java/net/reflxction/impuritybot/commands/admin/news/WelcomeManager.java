package net.reflxction.impuritybot.commands.admin.news;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.info.*;

@SuppressWarnings("SuspiciousMethodCalls")
public class WelcomeManager extends AbstractCommand {

    @Override
    public String getCommand() {
        return "readio";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        m.delete().queue();
        if (g.getMember(u).hasPermission(Permission.MESSAGE_MANAGE)) {
            c.sendFile(EnumRules.getImage()).queue();
            // Rules
            for (EnumRules rule : EnumRules.values()) {
                c.sendMessage(rule.getAsEmbed().build()).queue();
            }

            // Punishments
            c.sendFile(EnumPunishments.getImage()).queue();
            for (EnumPunishments punishment : EnumPunishments.values()) {
                c.sendMessage(punishment.getAsEmbed().build()).queue();
            }

            // FAQ
            c.sendFile(EnumFAQ.getImage()).queue();
            for (EnumFAQ faq : EnumFAQ.values()) {
                c.sendMessage(faq.getAsEmbed().build()).queue();
            }

            // Teams
            c.sendFile(EnumTeams.getImage()).queue();
            for (EnumTeams team : EnumTeams.values()) {
                c.sendMessage(team.getAsEmbed().build()).queue();
            }

            // Roles
            c.sendFile(EnumRoles.getImage()).queue();
            for (EnumRoles role : EnumRoles.values()) {
                c.sendMessage(role.getAsEmbed().build()).queue();
            }

            // Registration
            c.sendFile(EnumImages.REGISTRATION.getFile()).queue();
            c.sendFile(EnumImages.REGISTRATION_EXAMPLE.getFile()).queue();

            // Staff Notes
            c.sendFile(EnumImages.STAFF_NOTES.getFile()).queue();
            c.sendMessage("**Extra notes staff may leave.**").queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get the rules";
    }

    @Override
    public String getUsage() {
        return "-readio";
    }


}
