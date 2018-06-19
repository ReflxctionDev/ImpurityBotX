package net.reflxction.impuritybot.commands.points;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.points.mechanics.AssignRanking;
import net.reflxction.impuritybot.utils.data.PointsManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class ManagePointsCmd extends AbstractCommand {

    private static PointsManager pointsManager = new PointsManager();

    @Override
    public String getCommand() {
        return "mpoints";
    }

    private PointsManager manager = new PointsManager();

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        Role staff = g.getRolesByName("Staff", true).get(0);
        if (!g.getMember(u).getRoles().contains(staff)) {
            c.sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length == 0) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            return;
        }
        if (args[0].equalsIgnoreCase("add")) {
            int amount;
            if (args.length == 3) {
                String name;
                User target;
                try {
                    name = StringUtils.mentionToId(args[1]);
                    target = j.getUserById(name);
                } catch (Exception e2) {
                    c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
                    return;
                }
                if (target.isBot()) {
                    c.sendMessage("**You can't manage a set, add, remove, or reset a bot's points**").queue();
                    return;
                }
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (Exception e) {
                    c.sendMessage("**Invalid usage at field amount**").queue();
                    return;
                }
                addPoints(amount, target, c);
                AssignRanking assignRanking = new AssignRanking(g.getMember(target), pointsManager.getLevel(target));
                assignRanking.assignRating();
            } else {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            }
            return;
        } else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length == 3) {
                int amount;
                User targetuser;
                String name;
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (Exception e3) {
                    c.sendMessage("**Invalid usage at field amount**").queue();
                    return;
                }
                try {
                    name = StringUtils.mentionToId(args[1]);
                    targetuser = j.getUserById(name);
                } catch (Exception e4) {
                    c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
                    return;
                }
                if (targetuser.isBot()) {
                    c.sendMessage("**You can't manage a set, add, remove, or reset a bot's points**").queue();
                    return;
                }
                removePoints(amount, targetuser, c);
                AssignRanking assignRanking = new AssignRanking(g.getMember(targetuser), pointsManager.getLevel(targetuser));
                assignRanking.assignRating();
            } else {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            }
            return;
        } else if (args[0].equalsIgnoreCase("set")) {
            if (args.length == 3) {
                int amount;
                User targetuser;
                String name;
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (Exception e5) {
                    c.sendMessage("**Invalid usage at field amount**").queue();
                    return;
                }
                try {
                    name = StringUtils.mentionToId(args[1]);
                    targetuser = j.getUserById(name);
                } catch (Exception e6) {
                    c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
                    return;
                }
                if (targetuser.isBot()) {
                    c.sendMessage("**You can't manage a set, add, remove, or reset a bot's points**").queue();
                    return;
                }
                manager.setUserPoints(targetuser, amount);
                c.sendMessage("**Set points for " + targetuser.getName() + " to " + amount +"**").queue();
                AssignRanking assignRanking = new AssignRanking(g.getMember(targetuser), pointsManager.getLevel(targetuser));
                assignRanking.assignRating();
            } else {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            }
            return;
        } else if (args[0].equalsIgnoreCase("reset")) {
            if (args.length == 2) {
                User target;
                String name;
                try {
                    name = StringUtils.mentionToId(args[1]);
                    target = j.getUserById(name);
                } catch (Exception e7) {
                    c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
                    return;
                }
                if (target.isBot()) {
                    c.sendMessage("**You can't manage a set, add, remove, or reset a bot's points**").queue();
                    return;
                }
                manager.setUserPoints(target, 0);
                c.sendMessage("**Reset points for " + target.getName() + "**").queue();
                AssignRanking assignRanking = new AssignRanking(g.getMember(target), pointsManager.getLevel(target));
                assignRanking.assignRating();
            } else {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            }
        } else {
            c.sendMessage("**The option " + args[0] + " seems to be invalid.\nUse add, remove, reset, or set**").queue();
        }
    }

    public static void addPoints(int toAdd, User target, MessageChannel c) {
        int total = pointsManager.getUserPoints(target) + toAdd;
        pointsManager.setUserPoints(target, total);
        c.sendMessage("**Added " + toAdd + " points to " + target.getName() + "**").queue();
    }

    public static void removePoints(int toRemove, User target, MessageChannel c) {
        int total = pointsManager.getUserPoints(target) - toRemove;
        if (total < 0) {
            total = 0;
        }
        pointsManager.setUserPoints(target, total);
        c.sendMessage("**Removed " + total + " points from " + target.getName() + "**").queue();
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.POINTS;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Manages the points for the desired user";
    }

    @Override
    public String getUsage() {
        return "-mpoints <add:remove:reset:set> <@user>";
    }
}