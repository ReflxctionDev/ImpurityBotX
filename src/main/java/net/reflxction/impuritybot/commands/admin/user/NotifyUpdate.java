package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.managers.RoleManager;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;

/**
 * Created by Reflxction, on 02/01/18.
 */
public class NotifyUpdate extends AbstractCommand {

    @Override
    public String getCommand() {
        return "news";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        Role updates = g.getRoleById("408537798392414218");
        RoleManager rm = updates.getManager();
        if (c.getId().equals("374104070945767424")) {
            rm.setMentionable(true).queue();
            m.delete().queue();
            String news = "";
            for (int i = 0; i < args.length; i++) {
                String arg = " " + args[i];
                news = news + arg;
            }
            c.sendMessage("<@&408537798392414218>" + news).queue();
            rm.setMentionable(false).queue();
            String nick = (g.getMember(u).getNickname() == null) ? u.getName() : g.getMember(u).getNickname();
            c.sendMessage("- **" + nick).queue();
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
        return "Make an announcement to the ";
    }

    @Override
    public String getUsage() {
        return "-news <announcement>";
    }
}
