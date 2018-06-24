package net.reflxction.impuritybot.main;

import me.brokenearth.core.scheduler.Timer;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.core.others.Roles;

public class CleanChatFilter extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].startsWith("-") && event.getChannel().getId().equals("363721897743089671") && !event.getMember().getRoles().contains(Roles.STAFF)) {
            Message message = event.getChannel().sendMessage("**Please make sure to use commands in #bot-commands**").complete();
            new Timer() {
                @Override
                public void run() {
                    message.delete().queue();
                }
            }.schedule(3000);
        }
    }
}
