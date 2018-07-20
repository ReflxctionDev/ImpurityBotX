package net.reflxction.impuritybot.commands.admin.botupdates;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.awt.*;

/**
 * Created by Reflxction, on 02/04/18.
 */
public class ReleasedCommand extends AbstractCommand {

    @Override
    public String getCommand() {
        return "release";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        m.delete().queue();
            if(c.getId().equals("433614676086095890")) {
            String whole = getMessageContent().substring(7);
            int fbc = whole.indexOf("(");
            int sbc = whole.indexOf(")");
            String command = whole.substring(fbc, sbc).replace("(", "")
                    .replace(")", "");
            int desc1 = whole.indexOf("{-");
            int desc2 = whole.indexOf("-}");
            String desc = whole.substring(desc1, desc2).replace("{-", "")
                    .replace("-}", "");
            int usage1 = whole.indexOf("{{");
            int usage2 = whole.indexOf("}}");
            String usage = whole.substring(usage1, usage2).replace("{{", "")
                    .replace("}}", "");
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setDescription("**New command released**\n")
                    .addField("Command:", "**Name**: -" + command + "\n**Description**: " + desc + "\n**Usage**: " + usage)
                    .setColor(Color.GREEN)
                    .build();
            c.sendMessage(builder.build()).queue();
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
        return "Post an embed regarding a new released command";
    }

    @Override
    public String getUsage() {
        return "-release (command) {-Description-} {{Usage}}";
    }
}
