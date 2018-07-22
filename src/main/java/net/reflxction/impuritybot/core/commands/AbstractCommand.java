package net.reflxction.impuritybot.core.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.util.Arrays;

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
     * @param event the command event instance
     * @param args the arguments of the command
     */
    public abstract void process(CommandEvent event, String[] args);

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
    public final void onMessageReceived(MessageReceivedEvent event) {
        this.content = event.getMessage().getContentRaw();
        User user = event.getAuthor();
        if (user.isBot()) return;
        String[] args;
        if (!content.startsWith("-")) return;
        if (content.contains(" ")) args = StringUtils.toArgs(content, 1);
        else args = new String[0];
        if ((getAliases() == null || getAliases().length == 0) && !content.startsWith("-" + getCommand())) return;
        else if (getAliases().length > 0){
            boolean isFound = false;
            for (String alias : getAliases()) {
                if (content.startsWith("-" + alias)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) return;
        } else return;
        CommandEvent cmdEvent = new CommandEvent(event.getJDA(), event.getGuild(), event.getChannel(), event.getMember(), event.getMessage());
        process(cmdEvent, args);
    }
}