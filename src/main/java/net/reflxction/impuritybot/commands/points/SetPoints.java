package net.reflxction.impuritybot.commands.points;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.points.mechanics.AssignRanking;
import net.reflxction.impuritybot.utils.data.PointsManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class SetPoints extends AbstractCommand {
    @Override
    public String getCommand() {
        return "setpoints";
    }

    private PointsManager manager = new PointsManager();

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length != 2) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            return;
        }
        int parsed;
        try {
            parsed = Integer.parseInt(args[1]);
        } catch (Exception e) {
            c.sendMessage("**Please enter a number at field 'amount'").queue();
            return;
        }
        try {
            String id = StringUtils.mentionToId(args[0]);
            User target = j.getUserById(id);
            manager.setUserPoints(target, parsed);
            AssignRanking assignRanking = new AssignRanking(g.getMember(target), manager.getLevel(u));
            assignRanking.assignRating();
            c.sendMessage("**Set " + target.getName() + "'s points to " + manager.getUserPoints(u) + "**.").queue();
        } catch (NumberFormatException ex) {
            c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
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
        return "Sets the points for the desired user";
    }

    @Override
    public String getUsage() {
        return "-setpoints <@user> <amount>";
    }
}
