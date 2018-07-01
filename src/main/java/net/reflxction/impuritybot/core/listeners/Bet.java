package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.utils.data.CreditsManager;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Create this class every time a member is bet
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 */
public class Bet {
    /**
     * The credits manager instance
     */
    private CreditsManager manager = new CreditsManager();
    /**
     * Bet a member
     * @param executor the member you want to bet
     * @param target the second member you want to bed
     * @param channel the chanel in where the event is occurring
     * @param amount the amount to bet
     */
    public void bet(Member executor, Member target, MessageChannel channel, int amount) {
        if (!hasEnough(executor, target, amount)) {
            channel.sendMessage("**There are no enough credits to start the bet**").queue();
            return;
        }
        double executorchance = (new Random().nextInt(1000) + 1) / 10.0;
        double targetchance = 100 - executorchance;
        makeEmbed(executor, target, channel, executorchance, targetchance);
        if (executorchance > targetchance) {
            channel.sendMessage("**" + executor.getUser().getName() + "** wins!").queue();
            setCredits(executor, target, (int)executorchance, (int)targetchance, amount);
        } else if (targetchance > executorchance) {
            channel.sendMessage("**" + target.getUser().getName() + "** wins!").queue();
            setCredits(target, executor, (int)executorchance, (int)targetchance, amount);
        } else channel.sendMessage("**" + executor.getUser().getName() + "** and **" + target.getUser().getName() + "** ties!").queue();
    }
    /**
     * Makes embed that displays the chance (etc.)
     * @param executor the first member that got bet
     * @param target the second member that got bet
     * @param channel the channel in where the event is occurring
     * @param executorchance the chance of the first member
     * @param targetchance the chance of the second member
     */
    private void makeEmbed(Member executor, Member target, MessageChannel channel, double executorchance, double targetchance) {
        EmbedFactory factory = new EmbedFactory(new EmbedBuilder());
        factory.setTitle(executor.getUser().getName() + " vs " + target.getUser().getName());
        factory.addField(executor.getUser().getAsMention(), String.valueOf(executorchance));
        factory.addField(target.getUser().getAsMention(), String.valueOf(targetchance));
        factory.setColor(Color.decode("#e84118"));
        channel.sendMessage(factory.build().build()).queue();
    }
    /**
     * Removes the credits the looser by the amount bet and adds the amount to the winner
     * @param winner the winner
     * @param looser the looser
     * @param amount the amount used
     */
    private void setCredits(Member winner, Member looser, int winnerchance, int looserchance, int amount) {
        if (winnerchance * 2 != looserchance)manager.setUserCredits(winner.getUser(), manager.getUserCredits(winner.getUser()) + amount);
        else manager.setUserCredits(winner.getUser(), manager.getUserCredits(winner.getUser()) + amount * 2);
        manager.setUserCredits(looser.getUser(), manager.getUserCredits(looser.getUser()) - amount);
    }

    /**
     * Checks of the target or executor has enough credits
     * @param executor the first member that got bet
     * @param target the second member that got bet
     * @param amount the amount of credits the bet will use
     * @return whether the target or executor has enough credits
     */
    private boolean hasEnough(Member executor, Member target, int amount) {
        return manager.getUserCredits(executor.getUser()) >= amount && manager.getUserCredits(target.getUser()) >= amount;
    }
}
