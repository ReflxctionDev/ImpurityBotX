package me.brokenearth.core.commands;


import me.brokenearth.core.events.CommandEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.HashMap;

/**
 * Copyright 2018 github.com/BrokenEarthDev
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 * @see CommandEvent
 * @see CommandExecutor
 */
public final class Command extends ListenerAdapter {
    /**
     * Stores the classes that implement CommandExecutor
     */
    private static HashMap<CommandExecutor, String> map = new HashMap<CommandExecutor, String>();
    /**
     * Gets the prefix of the command
     */
    private static String prefix = "";

    /**
     * Registers the classes that implement CommandExecutor
     *
     * @param executor the class that implements CommandExecutor
     */
    public static void register(CommandExecutor executor) {
        map.put(executor, "");
    }

    /**
     * Sets the prefix of the command
     *
     * @param prefix the prefix that will be set
     */
    public static void setPrefix(String prefix) {
        Command.prefix = prefix;
    }

    /**
     * Triggered when someone sends a message to the bot or in guild
     *
     * @param event the event that occurs when sends a message to the bot or in guild
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (!args[0].startsWith(prefix)) return;
        for (CommandExecutor executor : map.keySet()) {
            executor.onCommand(new CommandEvent(event.getMember(), event.getGuild(), args[0], prefix, event.getJDA(), event.getChannel()), args);
        }
    }
}
