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
package net.reflxction.impuritybot.commands.dev;

import net.dv8tion.jda.core.entities.TextChannel;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

public class DevDiscordInvite extends AbstractCommand {

    /**
     * Name of the command, prefixed with "-"
     *
     * @return ^
     */
    @Override
    public String getCommand() {
        return "-dev";
    }

    /**
     * Process of the command
     *
     * @param event the command event instance
     * @param args  the arguments of the command
     */
    @Override
    public void process(CommandEvent event, String[] args) {
        TextChannel c = ((TextChannel) event.getChannel());
        c.sendMessage("**Join our development Discord for the latest updates!** \n\nhttps://discord.gg/hVzpx8z").queue();
    }

    /**
     * Aliases
     *
     * @return A string array that consists of other strings which should do the same process
     */
    @Override
    public String[] getAliases() {
        return new String[0];
    }

    /**
     * Category
     *
     * @return Command category
     */
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.DEV;
    }

    /**
     * Delay
     *
     * @return Delay of the command
     */
    @Override
    public long getDelay() {
        return 0;
    }

    /**
     * Description
     *
     * @return A brief command description
     */
    @Override
    public String getDescription() {
        return "Get an invite for the development discord";
    }

    /**
     * Usage
     *
     * @return Command usage
     */
    @Override
    public String getUsage() {
        return "-dev";
    }
}
