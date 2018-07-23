/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.impuritybot.core.commands;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @param args  the arguments of the command
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

    @SubscribeEvent
    @Override
    public final void onMessageReceived(MessageReceivedEvent event) {
        this.content = event.getMessage().getContentRaw();
        User user = event.getAuthor();
        if (user.isBot()) return;
        String[] args;
        if (content.contains(" ")) args = StringUtils.toArgs(content, 1);
        else args = new String[0];
        if (regexCheck(getCommand(), content) && content.startsWith("-" + getCommand())) {
            CommandEvent cmdEvent = new CommandEvent(event.getJDA(), event.getGuild(), event.getChannel(), event.getMember(), event.getMessage());
            process(cmdEvent, args);
        }
        if (getAliases() != null && getAliases().length != 0) {
            AliasManager aliasManager = new AliasManager(this);
            if (!aliasManager.contentContainsAlias()) return;
            CommandEvent cmdEvent = new CommandEvent(event.getJDA(), event.getGuild(), event.getChannel(), event.getMember(), event.getMessage());
            process(cmdEvent, aliasManager.getArgs());
        }
    }

    /**
     * Performs a simple regex check
     *
     * @param sub    The content to check if it exists in the source
     * @param source The content to check if the sub is contained in
     * @return True if it was found (detected by regex)
     */
    private boolean regexCheck(String sub, String source) {
        Pattern pattern = Pattern.compile(sub + "\\b");
        Matcher matcher = pattern.matcher(source);
        return matcher.find();
    }

    /**
     * Manages command aliases
     */
    private class AliasManager {

        private AbstractCommand command;

        private AliasManager(AbstractCommand command) {
            this.command = command;
        }

        private boolean contentContainsAlias() {
            String content = command.content;
            String[] aliases = command.getAliases();
            for (String alias : aliases) {
                if (content.startsWith("-" + alias) && regexCheck(alias, content)) {
                    return true;
                }
            }
            return false;
        }

        private String[] getArgs() {
            return StringUtils.toArgs(command.content, 1);
        }
    }

}