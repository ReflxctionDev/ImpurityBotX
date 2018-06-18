package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class TableFlip extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User u = event.getAuthor();
        TextChannel c = event.getTextChannel();
        Message m = event.getMessage();
        String content = m.getContentRaw();
        if (content.contains("(╯°□°）╯︵ ┻━┻")) {
            c.sendMessage("Stop flipping.").queue();
            c.sendMessage("┬─┬ ノ( ゜-゜ノ)").queue();
        }
        if (content.contains("¯\\_(ツ)_/¯")) {
            c.sendMessage("( ͡° ͜ʖ ͡°)").queue();
        }
    }

}
