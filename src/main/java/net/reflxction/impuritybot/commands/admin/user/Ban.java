package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.BanInfo;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.util.*;

public class Ban extends AbstractCommand {

    private HashMap<User, Integer> inSession = new HashMap<>();
    private HashMap<User, BanInfo> banInfos = new HashMap<>();

    private void ban(Member member, int delDays, String user) {
        member.getGuild().getController().ban(member.getUser(), delDays, user).queue();
    }

    private void ban(Member member, int delDays) {
        member.getGuild().getController().ban(member.getUser(), delDays).queue();
    }

    public Ban() {
        tickSessions();
    }

    @Override
    public String getCommand() {
        return "ban";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR) || event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            if (args.length == 1 && args[0].equalsIgnoreCase("confirm")) {
                if (inSession.containsKey(event.getMember().getUser())) {
                    boolean found = false;
                    BanInfo currentInfo = null;
                    for (User user : banInfos.keySet()) {
                        BanInfo info = banInfos.get(user);
                        if (info.getExecutor().equals(event.getMember())) {
                            found = true;
                            currentInfo = info;
                            break;
                        }
                    }
                    if (found) {
                        String target = currentInfo.getTarget().getUser().getName();
                        if (!currentInfo.reasonProvided())
                            event.getGuild().getController().ban(currentInfo.getTarget().getUser(), currentInfo.getDelDays()).queue();
                        else
                            event.getGuild().getController().ban(currentInfo.getTarget().getUser(), currentInfo.getDelDays(), currentInfo.getReason()).queue();
                        event.getChannel().sendMessage("Successfully banned **" + target + "**" + ((currentInfo.reasonProvided()) ? " for **" + currentInfo.getReason() + "**" : "")).queue();
                    } else {
                        event.getChannel().sendMessage("**An error had occurred!** Please report this error to the Developers").queue();
                        return;
                    }
                } else {
                    event.getChannel().sendMessage("**You are not in a session**").queue();
                    return;
                }
            }
            if (args.length < 2) {
                event.getChannel().sendMessage("**Invalid usage!** Try " + getUsage()).queue();
                return;
            }
            if (inSession.containsKey(event.getMember().getUser())) {
                event.getChannel().sendMessage("**You are already in session (**your session is now reset**)").queue();
                inSession.remove(event.getMember().getUser());
                banInfos.remove(event.getMember().getUser());
                return;
            }
            Member target;
            try {
                target = event.getGuild().getMemberById(StringUtils.mentionToId(args[0]));
                boolean reasonProvided = false;
                int delMsg;
                String reason = "";
                if (args.length >= 3) {
                    reasonProvided = true;
                    StringBuilder builder = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        builder.append(args[i]);
                    }
                    reason = builder.toString().trim();
                }
                try {
                    delMsg = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    event.getChannel().sendMessage("**Expected a number but found " + args[1] + "**").queue();
                    return;
                }
                if ((target.getRoles().get(0).getPositionRaw() >= event.getMember().getRoles().get(0).getPositionRaw()) && !event.getMember().isOwner()) {
                    event.getChannel().sendMessage("**You can't ban members with higher or equal role**").queue();
                    return;
                }
                BanInfo info;
                if (!reasonProvided) {
                    info = new BanInfo(event.getMember(), target, delMsg);
                } else info = new BanInfo(event.getMember(), target, delMsg, reason);
                addSession(event.getMember().getUser(), info);
            } catch (Exception e) {
                event.getChannel().sendMessage("**Expected a mention (or id) but found " + args[0] + "**").queue();
                return;
            }
            event.getChannel().sendMessage("**Are you sure you want to ban " + target.getUser().getName() + "? Please do -ban confirm**").queue();
        } else {
            event.getChannel().sendMessage("**You do not have permission to execute this command**").queue();
        }
    }

    public void tickSessions() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (User user : inSession.keySet()) {
                    int left =  inSession.get(user) - 10;
                    if (left == 0) {
                        inSession.remove(user);
                        banInfos.remove(user);
                        continue;
                    }
                    inSession.remove(user);
                    inSession.put(user, left);
                    tickSessions();
                }
            }
        }, 10000);
    }

    public void addSession(User user, BanInfo info) {
        inSession.put(user, 30);
        banInfos.put(user, info);
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
        return "Bans a specified user";
    }

    @Override
    public String getUsage() {
        return "-ban <@user> <delDays> [reason] where [] = optional and <> = required";
    }
}
