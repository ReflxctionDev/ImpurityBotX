package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;

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
        Role updates = g.getRolesByName("Bot updates", true).get(0);
        if (args.length == 0) {
            addRole(u, g, c);
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("remove")) {
                g.getController().removeSingleRoleFromMember(g.getMember(u), updates).queue();
                c.sendMessage("**You will not be notified on bot updates**").queue();

            } else if (args[0].equalsIgnoreCase("add")) {
                addRole(u, g, c);
            } else {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            }
        } else {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
        }
    }

    private void addRole(User user, Guild g, MessageChannel c) {
        Role updates = g.getRolesByName("Bot updates", true).get(0);
        if (g.getMember(user).getRoles().contains(updates)) {
            c.sendMessage("**You already have bot notifications enabled**").queue();
        }
        g.getController().addSingleRoleToMember(g.getMember(user), updates).queue();
        c.sendMessage("**You will now be notified on bot updates**").queue();
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
