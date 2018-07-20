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
public class ToDo extends AbstractCommand {

    @Override
    public String getCommand() {
        return "todo";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        m.delete().queue();
        if (c.getId().equals("409784636949135370")) {
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setColor(Color.GREEN)
                    .setAuthor("To-do list:", null, j.getSelfUser().getAvatarUrl())
                    .addField("", "-daily (!) - Gives 100-200 credits everyday." +
                            "\n-casino (#) - Three emotes will appear, and if they are the same you get 400 credits. Costs 100 credits per command." +
                            "\n-reward (#) - Exclusive to staffs. Rewards the player who has won the MvM and GvG. Every win gives you 500 credits. Posting a YouTube video also rewards you with 300 credits. Reporting a rule breaker first to the staff will give you 100 credits." +
                            "\n-buy (#) - Exchanges the coin with a discord loot chest. This gives you either discord xps, discord ranks, or certain ranks that allows you to use the commands in hobby channels. Hobby channels will include Aki and other games. This means we will be locking the Aki channel so it will be exclusive for those who has it." +
                            "\n-bet (#) - on the #polls we will be constantly posting bets like \"who will win the gvg? Vote now\" The player who gets it right gains 200 credits. Costs 100 credits per vote")
                    .addField("", "# - Commands that haven't been created yet\n! - Commands that have been created but work-in-progress\n* - Commands that have been created and released"  )
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
        return "Get the Todo list of the bot";
    }

    @Override
    public String getUsage() {
        return "-todo";
    }
}
