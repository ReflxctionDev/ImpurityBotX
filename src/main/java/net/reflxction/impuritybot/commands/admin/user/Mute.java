package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.discord_events.MuteManager;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

public class Mute extends AbstractCommand {

    private MuteManager manager = new MuteManager();

    @Override
    public String getCommand() {
        return "mute";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (!(g.getMember(u).getRoles().get(0).getPositionRaw() >= Roles.ADMIN.getPosition())) {
            c.sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length < 2) {
            c.sendMessage("**Invalid usage. Usage: " + getUsage() + "**").queue();
            return;
        }
        Member target;
        Member executor = g.getMember(u);
        StringBuilder reason;
        String id;
        StringBuilder reasonBuilder = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            reasonBuilder.append(args[i]);
        }
        reason = new StringBuilder(reasonBuilder.toString());
        try {
            id = StringUtils.mentionToId(args[0]);
            target = g.getMember(j.getUserById(id));
            if (manager.isMuted(target.getUser())) {
                c.sendMessage("** " + target.getUser().getName() + " is already muted").queue();
                return;
            }
            if (executor == target) {
                c.sendMessage("**You can't mute yourself**").queue();
                return;
            }
            if (target.getUser().isBot()) {
                c.sendMessage("**You can't mute bots**").queue();
                return;
            }
            if (executor.getRoles().get(0).getPositionRaw() <= target.getRoles().get(0).getPositionRaw()) {
                c.sendMessage("You cannot mute **" + target.getUser().getName() + "** because he has a higher or equal role!").queue();
                return;
            }
            int time_seconds = parseTime(args[1], c);
            if (time_seconds == Integer.MIN_VALUE) return;
            if (time_seconds < 1) {
                c.sendMessage("**You can't mute a member less than 1 second**").queue();
                return;
            }
            for (int i = 3; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }
            c.sendMessage("Successfully muted **" + target.getUser().getName() + "**").queue();
            manager.muteUser(target.getUser(), time_seconds);
            sendNews(target.getUser().openPrivateChannel().complete(), reason.toString(), time_seconds, executor.getUser(), target.getUser());
        } catch (NumberFormatException e) {
            c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
        }
    }

    private int parseTime(String toParse, MessageChannel channel) {
        int breakpoint;
        String time = "";
        char[] array = toParse.toLowerCase().toCharArray();
        int parsed = Integer.MIN_VALUE;
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 's' || array[i] == 'm' || array[i] == 'h' ) {
                    breakpoint = i;
                    if (array[i] == 's') {
                        for (int c = 0; c < breakpoint; c++) {
                            time += array[c];
                        }
                        parsed = Integer.parseInt(time);
                    }
                    if (array[i] == 'm') {
                        for (int c = 0; c < breakpoint; c++) {
                            time += array[c];
                        }
                        parsed = Integer.parseInt(time) * 60;
                    }
                    if (array[i] == 'h') {
                        for (int c = 0; c < breakpoint; c++) {
                            time += array[c];
                        }
                        parsed = Integer.parseInt(time) * 3600;
                    }
                } else {
                    if (array[i] != '1' && array[i] != '2' && array[i] != '3' && array[i] != '4' && array[i] != '5' && array[i] != '6' &&
                            array[i] != '0' && array[i] != '9' && array[i] != '8' && array[i] != '7') {
                        channel.sendMessage("**Invalid usage** Time must be either s, m, or h followed by a number").queue();
                    }
                }
            }
        } catch (Exception e) {
            channel.sendMessage("**Invalid usage** Time must be either s, m, or h followed by a number").queue();
        }
        return parsed;
    }

    private void sendNews(MessageChannel channel, String reason, int time_seconds, User executor, User target) {
        if (reason.equals("")) reason = "unspecified";
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setTitle(target.getName())
                .setColor(Color.decode("#e84118"))
                .addField("You are muted", "by " + executor.getName(), false)
                .addField("Reason",  reason , false)
                .addField("Time", timeToString(time_seconds), false)
                .setFooter("If you believe this is an error, please contact the development team", null)
                .build();
        channel.sendMessage(builder.build()).queue();
    }

    private String timeToString(int time_seconds) {
        if (time_seconds < 60) {
            return time_seconds + " seconds";
        }
        else if (time_seconds < 3600) {
            return time_seconds / 60 + " minutes";
        } else if (time_seconds < 86400) {
            return time_seconds / 3600 + " hours";
        } else {
            return time_seconds / 86400 + " days";
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
        return "Mutes a specified member on a particular time";
    }

    @Override
    public String getUsage() {
        return "-mute <@user> <time:-s-:-m-:-h-:-d-> [reason]";
    }
}
