package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;

/**
 * Created by Reflxction, on 02/01/18.
 */
public class Notify extends AbstractCommand {

    @Override
    public String getCommand() {
        return "notify";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length == 0) {
            g.getController().addSingleRoleToMember(g.getMember(u), Roles.BOT_UPDATES).queue();
            m.addReaction("\uD83D\uDC4C").queue();
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("remove")) {
                g.getController().removeSingleRoleFromMember(g.getMember(u), Roles.BOT_UPDATES).queue();
                m.addReaction("\uD83D\uDC4C").queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get the notify role";
    }

    @Override
    public String getUsage() {
        return "-notify / -notify remove";
    }
}
