/*
 * * Copyright 2018 github.com/ReflxctionDev
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
package net.reflxction.impuritybot.filter;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.filter.swears.WordType;

/**
 * The class that handles the command input for "-filter".
 */
public class FilterCommand extends AbstractCommand {

    // Instance of the manager
    private FilterManager manager = new FilterManager();

    /**
     * Name of the command, prefixed with "-"
     *
     * @return ^
     */
    @Override
    public String getCommand() {
        return "filter";
    }

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
    @SuppressWarnings("ConstantConditions")
    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        if (g.getMember(u).getRoles().get(0).getPositionRaw() >= Roles.ADMIN.getPositionRaw()) {
            switch (args.length) {
                case 0:
                    c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
                    break;
                case 1:
                    switch (args[0]) {
                        case "on":
                            manager.setEnabled(true);
                            c.sendMessage("Filter: **ON**").queue();
                            break;
                        case "off":
                            manager.setEnabled(false);
                            c.sendMessage("Filter: **OFF**").queue();
                            break;
                        default:
                            c.sendMessage("**Invalid arguments. Try " + getUsage() + "**").queue();
                            break;
                    }
                    break;
                case 2:
                    switch (args[0]) {
                        case "remove":
                            manager.removeSwearWord(args[1]);
                            c.sendMessage("A word has been removed from the list.").queue();
                            break;
                    }
                    break;
                case 3:
                    switch (args[0]) {
                        case "add":
                            if (WordType.parseType(args[1]) != null) {
                                WordType type = WordType.parseType(args[1]);
                                manager.addSwearWord(args[2], type);
                                c.sendMessage("A new swear word was added with type **" + type.getName() + "**").queue();
                            } else {
                                c.sendMessage("**Invalid word type! Must be `R` for regex, `N` for normal**").queue();
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
                    break;
            }
        } else {
            c.sendMessage("**You don't have permission to manage the filter!**").queue();
        }
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
        return CommandCategory.ADMIN;
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
        return "Manage the anti-swear filter";
    }

    /**
     * Usage
     *
     * @return Command usage
     */
    @Override
    public String getUsage() {
        return "-filter <on / off> | -filter add <word type (R/N) <word> | -filter remove [word]";
    }

}
