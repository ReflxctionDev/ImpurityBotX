package net.reflxction.impuritybot.commands.miscs;

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
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.data.WarningsManager;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class WarnInfo extends AbstractCommand {

    private ImpurityBot bot;

    public WarnInfo(ImpurityBot bot) {
        this.bot = bot;
    }

    private final WarningsManager wu = new WarningsManager();



    @Override
    public String getCommand() {
        return "warninfo";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
        }
        if (args.length == 1) {
            try {
                int i = Integer.parseInt(args[0]);
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setDescription("Warning " + i + " for user " + u.getName())
                        .addField("Reason", wu.getWarningReason(u, i))
                        .addField("Warned by", wu.getWarnedBy(u, i).getName())
                        .addField("Total warnings", wu.getWarnings(u) + "")
                        .setRandomColor()
                        .build();
                c.sendMessage(builder.build()).queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**Invalid arguments! Expected a number, but found** `" + args[0] + "`").queue();
            } catch (IllegalArgumentException ex) {
                c.sendMessage("**Couldn't find a warning with this number!**").queue();
            }
        }
        if (args.length == 2) {
            try {
                User warned = j.getUserById(StringUtils.mentionToId(args[0]));
                int i = Integer.parseInt(args[1]);
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setDescription("Warning " + i + " for user " + warned.getName())
                        .addField("Reason", wu.getWarningReason(warned, i))
                        .addField("Warned by", wu.getWarnedBy(warned, i).getName())
                        .addField("Total warnings", wu.getWarnings(warned) + "")
                        .setRandomColor()
                        .build();
                c.sendMessage(builder.build()).queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**Invalid arguments! Expected a user mention, but found** `" + args[0] + "`").queue();
            } catch (IllegalArgumentException ex) {
                c.sendMessage("**Couldn't find a warning with this number!**").queue();
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
    public String getDescription() {
        return "Get information about a specific warning";
    }

    @Override
    public String getUsage() {
        return "-warninfo <@user> <warn number> / -warninfo <warn number>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
