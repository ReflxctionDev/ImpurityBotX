package net.reflxction.impuritybot.events.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;

public final class CommandEvent {

    private Member member;
    private Guild guild;
    private JDA jda;
    private Message message;
    private MessageChannel channel;

    public CommandEvent(JDA jda, Guild guild, MessageChannel channel, Member member, Message message) {
        this.jda = jda;
        this.guild = guild;
        this.channel = channel;
        this.member = member;
        this.message = message;
    }

    public Member getMember() {
        return member;
    }

    public Guild getGuild() {
        return guild;
    }

    public JDA getJda() {
        return jda;
    }

    public Message getMessage() {
        return message;
    }

    public MessageChannel getChannel() {
        return channel;
    }

}
