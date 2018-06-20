package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.MuteManager;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import javax.naming.NamingEnumeration;

public class Unmute extends AbstractCommand {

    private ImpurityBot bot = ImpurityBot.getBot();

    @Override
    public String getCommand() {
        return null;
    }

    private boolean hasPermission(Member executor, Guild g) {
        Role admin = g.getRolesByName("Admin", true).get(0);
        Role owner = g.getRolesByName("Owner", true).get(0);
        Role coowner = g.getRolesByName("coowner", true).get(0);
        Role co_owner = g.getRolesByName("co-owner", true).get(0);
        Role co__owner = g.getRolesByName("co owner", true).get(0);
        return ((executor.getRoles().contains(co_owner) || executor.getRoles().contains(co__owner) || executor.getRoles().contains(coowner))
        ) && executor.getRoles().contains(owner) && executor.getRoles().contains(admin) && executor.isOwner();
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        Role admin = g.getRolesByName("Admin", true).get(0);
        Role owner = g.getRolesByName("Owner", true).get(0);
        Role coowner = g.getRolesByName("coowner", true).get(0);
        Role co_owner = g.getRolesByName("co-owner", true).get(0);
        Role co__owner = g.getRolesByName("co owner", true).get(0);
        Member executor = g.getMember(u);
        if (!hasPermission(executor, g)) {
            c.sendMessage("**You do not have permission to execute this command**").queue();
        }
        if (args.length != 2) {
            c.sendMessage("**Invalid usage. Usage: " + getUsage() + "**").queue();
            return;
        }
        Member target;
        String id;
        try {
            id = StringUtils.mentionToId(args[0]);
            target = g.getMember(j.getUserById(id));
            if (executor == target) {
                c.sendMessage("**You can't unmute yourself**").queue();
                return;
            }
            if (target.getUser().isBot()) {
                c.sendMessage("**You can't unmute bots**").queue();
                return;
            }
            if (executor.getRoles().contains(admin) && target.getRoles().contains(owner)) {
                c.sendMessage("**You are not allowed to unmute a member with higher or equal role**").queue();
                return;
            }
            if (executor.getRoles().contains(admin) && (target.getRoles().contains(coowner) || target.getRoles().contains(co__owner)
                    || target.getRoles().contains(co_owner))) {
                c.sendMessage("**You are not allowed to unmute a member with higher or equal role**").queue();
                return;
            }
            if (executor.getRoles().contains(admin) && target.isOwner()) {
                c.sendMessage("**You are not allowed to unmute a member with higher or equal role**").queue();
                return;
            }
            if ((executor.getRoles().contains(coowner) || executor.getRoles().contains(co__owner) || executor.getRoles().contains(co_owner)) && (target.getRoles().contains(owner) || target.isOwner())) {
                c.sendMessage("**You are not allowed to unmute a member with higher or equal role**").queue();
                return;
            }
            MuteManager manager = new MuteManager();
            manager.unmute(target.getUser());
            try {
                TextChannel channel = g.getTextChannelsByName("mute-log", false).get(0);
                sendLog(target, executor, channel);
            } catch (Exception e) {

            }
            sendNews(target, executor);
            c.sendMessage("**Successfully unmuted " + target.getUser().getAsMention() + "**").queue();
        } catch (NumberFormatException e) {
            c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
            return;
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Unmutes a specified member";
    }

    @Override
    public String getUsage() {
        return "-unmute <@user>";
    }


    private void sendLog(Member target, Member executor, TextChannel channel) throws Exception {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(target.getUser().getAsMention());
        builder.addField("You are unmuted", "by " + executor.getUser().getAsMention(), false);
        channel.sendMessage(builder.build()).queue();
    }

    private void sendNews(Member target, Member executor) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.addField("You are unmuted", "by " + executor.getUser().getName(), false);
        builder.setFooter("If you believe this is an error, contact an admin or " + executor.getUser().getAsMention(), null);
        target.getUser().openPrivateChannel().complete().sendMessage(builder.build()).queue();
    }
}
