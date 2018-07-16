package net.reflxction.impuritybot.core.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public abstract class AbstractCommand extends ListenerAdapter {

    // Message content
    private String content;

    /**
     * Name of the command, prefixed with "-"
     *
     * @return ^
     */
    public abstract String getCommand();

    /**
     * Process of the command
     *
     * @param j    Instance of the latest JDA cache
     * @param g    Guild that the command is run in
     * @param m    Message of the command
     * @param c    Channel that the message was sent in
     * @param u    User who sent the command
     * @param args Extra arguments of the command
     */
    public abstract void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args);

    /**
     * Aliases
     *
     * @return A string array that consists of other strings which should do the same process
     */
    public abstract String[] getAliases();

    /**
     * Category
     *
     * @return Command category
     */
    public abstract CommandCategory getCategory();

    /**
     * Delay
     *
     * @return Delay of the command
     */
    public abstract long getDelay();

    /**
     * Message content
     *
     * @return Message's content with all args
     */
    protected String getMessageContent() {
        return content;
    }

    /**
     * Description
     *
     * @return A brief command description
     */
    public abstract String getDescription();

    /**
     * Usage
     *
     * @return Command usage
     */
    public abstract String getUsage();

    @SuppressWarnings("UnusedAssignment")
    @SubscribeEvent
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message m = event.getMessage();
        content = m.getContentRaw();
        User u = event.getAuthor();
        MessageChannel c = event.getChannel();
        if (u.isBot()) return;
        String[] args;
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
            /*CommandProcessedEvent cmdEvent = new CommandProcessedEvent(
                    event.getJDA(),
                    this,
                    event.getTextChannel(),
                    u,
                    event.getGuild(),
                    m,
                    args);
            ImpurityBot.EVENT_BUS.post(cmdEvent);
            if (!cmdEvent.isCanceled()) {
            */
            //}
        } else if (getAliases().length > 0) {
            for (int i = 0; i < getAliases().length; i++) {
                if (content.startsWith("-" + getAliases()[i])) {
                    args = content.replace("-" + getAliases()[i] + " ", "").split(" ");
                   /* CommandProcessedEvent cmdEvent = new CommandProcessedEvent(
                            event.getJDA(),
                            this,
                            event.getTextChannel(),
                            u,
                            event.getGuild(),
                            m,
                            args);
                    ImpurityBot.EVENT_BUS.post(cmdEvent);
                    if (!cmdEvent.isCanceled()) {
                    */
                    process(event.getJDA(), event.getGuild(), m, c, u, args);
                    //}
                }
            }
        }
    }
}