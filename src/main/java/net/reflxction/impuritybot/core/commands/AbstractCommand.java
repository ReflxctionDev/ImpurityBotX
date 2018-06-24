package net.reflxction.impuritybot.core.commands;

import me.brokenearth.core.scheduler.Timer;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import net.reflxction.impuritybot.core.others.Roles;

@SuppressWarnings("all")
public abstract class AbstractCommand extends ListenerAdapter {

    private String content;

    public abstract String getCommand();

    public abstract void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args);

    public abstract String[] getAliases();

    public abstract CommandCategory getCategory();

    public abstract long getDelay();

    protected String getMessageContent() {
        return content;
    }

    public abstract String getDescription();

    public abstract String getUsage();

    @SubscribeEvent
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message m = event.getMessage();
        content = m.getContentRaw();
        User u = event.getAuthor();
        MessageChannel c = event.getChannel();
        if (u.isBot()) return;
        String[] args;
        String[] arguments = event.getMessage().getContentRaw().split("\\s+");
        if (arguments[0].startsWith("-") && event.getChannel().getId().equals("363721897743089671") && !event.getMember().getRoles().contains(Roles.STAFF)) {
            Message message = event.getChannel().sendMessage("**Please make sure to use commands in #bot-commands**").complete();
            new Timer() {
                @Override
                public void run() {
                    message.delete().queue();
                }
            }.schedule(3000);
            return;
        }
        if (content.contains(" ")) {
            args = content.replace("-" + getCommand() + " ", "").split(" ");
        }
        if (!content.contains(" ")) {
            args = new String[0];
        }
        if (getAliases() == null || getAliases().length == 0) {
            if (content.startsWith("-" + getCommand() + " ")) {
                args = content.replace("-" + getCommand() + " ", "")
                        .split(" ");
                process(event.getJDA(), event.getGuild(), m, c, u, args);
            } else if (content.startsWith("-" + getCommand()) && !content.contains(" ")) {
                args = new String[0];
                process(event.getJDA(), event.getGuild(), m, c, u, args);
            }
        } else if (getAliases().length > 0) {
            for (int i = 0; i < getAliases().length; i++) {
                if (content.startsWith("-" + getAliases()[i])) {
                    args = content.replace("-" + getAliases()[i] + " ", "").split(" ");
                    process(event.getJDA(), event.getGuild(), m, c, u, args);
                }
            }
        }
    }
}