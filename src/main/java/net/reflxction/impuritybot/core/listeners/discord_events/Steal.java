package net.reflxction.impuritybot.core.listeners.discord_events;

import me.brokenearth.core.scheduler.Timer;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.reflxction.impuritybot.utils.guild.GuildUtils;
import net.reflxction.impuritybot.utils.data.CreditsManager;
import java.util.Random;

/**
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 * @see net.reflxction.impuritybot.commands.user.StealCommand
 */
public class Steal {
    /**
     * The credits manager instance
     */
    private final CreditsManager manager = new CreditsManager();

    private int percentage;
    private boolean isSuccessful = false;

    public Steal() {
        this.generate();
    }

    /**
     * Generates a random number with a max of 100
     */
    private void generate() {
        percentage = new Random().nextInt(100) + 1;
    }
    /**
     * Attempts to steal 20% of the user's credits
     */
    public void steal(Member target, Member executor, int amount, MessageChannel channel) {
        if (target == executor) {
            if (channel != null) {
                channel.sendMessage("You can't steal credits from yourself").queue();
            }
        }
        if (channel != null) {
            channel.sendMessage("Percentage: **" + getPercentage() + "**").queue();
        }
        if (getPercentage() < 80) {
            if (channel != null) channel.sendMessage("**Oh no!** You were unlucky and ended up with less than 80%").queue();
            mute(executor, target);
            return;
        }
        if (amount > manager.getUserCredits(target.getUser())) {
            if (channel != null)
                channel.sendMessage("You are trying to steal more credits than **" + target.getUser().getName() + "** has").queue();
            return;
        }
        if (amount < 1) {
            if (channel != null)
                channel.sendMessage("You can't steal less than **1** credit").queue();
            return;
        }
        int percentage = (amount / manager.getUserCredits(target.getUser()) ) * 100;
        if (percentage > 20) {
            if (channel != null)
                channel.sendMessage("You are trying to steal more than 20% of **" + target.getUser().getName() + "'s** credits").queue();
            return;
        }
        isSuccessful = true;
        manager.setUserCredits(target.getUser(), manager.getUserCredits(target.getUser()) - amount);
        manager.setUserCredits(executor.getUser(), manager.getUserCredits(executor.getUser()) + amount);
        if (channel != null) channel.sendMessage("You have stolen **" + amount + "** credits from **" + target.getUser().getName() + "**").queue();
        mute(executor, target);
    }
    /**
     * Mutes the user who stole the credits for 5 minutes
     */
    private void mute(Member executor, Member target) {
        if (!isSuccessful) return;
        Role muted = GuildUtils.guild().getRolesByName("Muted", true).get(0);
        GuildUtils.controller().addRolesToMember(executor, muted).queue();
        new Timer() {
            @Override
            public void run() {
                GuildUtils.controller().removeRolesFromMember(target, muted).queue();
            }
        }.schedule(1000 * 60 * 5);
    }
    /**
     * Defines if the user did steal or not
     * @return it
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }
    /**
     * @return the percentage with a percent sign on front of it
     */
    public String getPercentageAsString() {
        return percentage + "%";
    }

    /**
     * @return the percentage
     */
    private int getPercentage() {
        return percentage;
    }
}
