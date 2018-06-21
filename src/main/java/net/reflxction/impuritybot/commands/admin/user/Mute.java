package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.MuteManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class Mute extends AbstractCommand {

    private MuteManager manager = new MuteManager();

    @Override
    public String getCommand() {
        return "mute";
    }

    private boolean isAllowed(User u, Guild g) {
        Role admin = g.getRolesByName("Admin", true).get(0);
        Role owner = g.getRolesByName("Owner", true).get(0);
        Role co_owner = g.getRolesByName("co-owner", true).get(0);
        Member executor = g.getMember(u);
        return executor.getRoles().contains(admin) || executor.getRoles().contains(owner)
                || executor.getRoles().contains(co_owner) || executor.isOwner();
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        Role admin = g.getRolesByName("Admin", true).get(0);
        Role owner = g.getRolesByName("Owner", true).get(0);
        Role co_owner = g.getRolesByName("co-owner", true).get(0);
        if (!isAllowed(u, g)) {
            c.sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length < 3) {
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
            if (executor.getRoles().contains(admin) && target.getRoles().contains(owner)) {
                c.sendMessage("**You are not allowed to mute a member with higher or equal role**").queue();
                return;
            }
            if (executor.getRoles().contains(admin)
            || target.getRoles().contains(co_owner)) {
                c.sendMessage("**You are not allowed to mute a member with higher or equal role**").queue();
                return;
            }
            if (executor.getRoles().contains(admin) && target.isOwner()) {
                c.sendMessage("**You are not allowed to mute a member with higher or equal role**").queue();
                return;
            }
            if (executor.getRoles().contains(co_owner) && (target.getRoles().contains(owner) || target.isOwner())) {
                c.sendMessage("**You are not allowed to mute a member with higher or equal role**").queue();
                return;
            }
            int time_seconds = parseTime(args[1], c);
            if (time_seconds == Integer.MIN_VALUE) return;
            if (time_seconds < 1) {
                c.sendMessage("**You can't mute a member less than 1 second**").queue();
                return;
            }
            try {
                TextChannel toSend = g.getTextChannelsByName("mute-log", true).get(0);
                sendLog(executor.getUser(), target.getUser(), reason.toString(), time_seconds, toSend);
            } catch (Exception ignored) {

            }
            manager.muteUser(target.getUser(), time_seconds);
            c.sendMessage("**Successfully muted " + target.getUser().getAsMention() + "**").queue();
            sendNews(u, reason.toString(), time_seconds, executor.getUser());
        } catch (NumberFormatException e) {
            c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
            return;
        }
        for (int i = 3; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }
    }

    private int parseTime(String toParse, MessageChannel channel) {
        int breakpoint;
        String time = "";
        char[] array = toParse.toLowerCase().toCharArray();
        int parsed = Integer.MIN_VALUE;
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 's' || array[i] == 'm' || array[i] == 'h' || array[i] == 'd') {
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
                    if (array[i] == 'd') {
                        parsed = Integer.parseInt(time) * 3600 * 24;
                    }
                } else {
                    if (array[i] != '1' && array[i] != '2' && array[i] != '3' && array[i] != '4' && array[i] != '5' && array[i] != '6' &&
                            array[i] != '0' && array[i] != '9' && array[i] != '8' && array[i] != '7') {
                        channel.sendMessage("**Invalid usage** Time must be either s, m, h, or d followed by a number").queue();
                    }
                }
            }
        } catch (Exception e) {
            channel.sendMessage("**Invalid usage** Time must be either s, m, h, or d followed by a number").queue();
        }
        return parsed;
    }

    private void sendLog(User executor, User target, String reason, int time_seconds, TextChannel channel) throws Exception {
        EmbedBuilder builder = new EmbedBuilder();
        builder.addField("You are muted", "by " + executor.getAsMention(), false);
        builder.setTitle(target.getAsMention());
        if (time_seconds >= 60 && time_seconds < 3600) {
            int t1 = time_seconds / 60;
            builder.addField("Time", t1 + " minutes", false);
        } else if (time_seconds >= 3600 && time_seconds < 3600 * 24) {
            int t1 = time_seconds / 3600;
            builder.addField("Time", t1 + " hours", false);
        } else if (time_seconds >= 3600 * 24) {
            int t1 = time_seconds / (3600 * 24);
            builder.addField("Time", t1 + " days", false);
        } else {
            builder.addField("Time", time_seconds + " seconds", false);
        }
        if (reason != null && !reason.equalsIgnoreCase("")) builder.addField("Reason", reason, false);
        channel.sendMessage(builder.build()).queue();
    }

    private void sendNews(User u, String reason, int time_seconds, User executor) {
        PrivateChannel channel = u.openPrivateChannel().complete();
        //EMBED
        EmbedBuilder builder = new EmbedBuilder();
        builder.addField("You are muted", "by " + executor.getName(), false);
        if (time_seconds >= 60 && time_seconds < 3600) {
            int t1 = time_seconds / 60;
            builder.addField("Time", t1 + " minutes", false);
        } else if (time_seconds >= 3600 && time_seconds < 3600 * 24) {
            int t1 = time_seconds / 3600;
            builder.addField("Time", t1 + " hours", false);
        } else if (time_seconds >= 3600 * 24) {
            int t1 = time_seconds / (3600 * 24);
            builder.addField("Time", t1 + " days", false);
        } else {
            builder.addField("Time", time_seconds + " seconds", false);
        }
        if (reason != null && !reason.equalsIgnoreCase("")) builder.addField("Reason", reason, false);
        builder.setFooter("If you believe this is an error, contact an admin or " + executor.getAsMention(), null);
        //=====
        channel.sendMessage(builder.build()).queue();
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
