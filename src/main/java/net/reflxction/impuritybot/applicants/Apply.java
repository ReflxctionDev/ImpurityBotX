package net.reflxction.impuritybot.applicants;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Apply {

    private String playerName;
    private User sender;

    private boolean isSent = false;

    public Apply(String playerName, User sender) {
        this.playerName = playerName;
        this.sender = sender;
    }

    public void apply(MessageChannel channel) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .addField("IGN", playerName)
                .addField("Plancke", "https://plancke.io/hypixel/player/stats/" + playerName)
                .addField("Discord user", sender.getName() + "#" + sender.getId())
                .addField("Note", "Don't forget to reply to that user!")
                .setTitle(playerName)
                .setColor(Color.decode("#e84118"))
                .build();
        channel.sendMessage(builder.build()).queue();
        isSent = true;
    }

    public void apply() {
        apply(ImpurityBot.getImpurityGuild().getTextChannelById("469126105689882625"));
    }

    public boolean isSent() {
        return isSent;
    }

}
