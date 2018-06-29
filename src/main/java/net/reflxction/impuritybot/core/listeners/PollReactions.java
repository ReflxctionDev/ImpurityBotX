package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.PollsManager;

/**
 * Created by Reflxction, on 02/02/18.
 */
public class PollReactions extends ListenerAdapter {

    private ImpurityBot bot;

    public PollReactions(ImpurityBot bot) {
        this.bot = bot;
    }

    private PollsManager polls = new PollsManager(bot);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel() instanceof TextChannel) {
            TextChannel c = (TextChannel) event.getChannel();
            final Guild g = event.getGuild();
            if (polls.isPoll(c.getId())) {
                final Message m = event.getMessage();
                Emote e = g.getEmoteById("412956641630093316");
                m.addReaction("\u2705").queue();
                m.addReaction(e).queue();
            }
			if(c.getId().equals("433614676086095890")) {
                final Message m = event.getMessage();
                m.addReaction("\u2B50").queue();
			}
        }
    }
}
