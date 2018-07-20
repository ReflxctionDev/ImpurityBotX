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
package net.reflxction.impuritybot.commands.miscs;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.util.ArrayList;


public class CommandInfo extends AbstractCommand {

    private static ArrayList<String> names = new ArrayList<>();

    private static ArrayList<String> categories = new ArrayList<>();

    private static ArrayList<String> desc = new ArrayList<>();

    private static ArrayList<String> usage = new ArrayList<>();

    private static ArrayList<AbstractCommand> commands = new ArrayList<>();

    @Override
    public String getCommand() {
        return "command";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                    .setTitle("Not enough arguments!")
                    .setDescription("Try -command <command>")
                    .build();
            c.sendMessage(embed.build()).queue();
        }
        if (args.length == 1) {
            for (int i = 0; i < getCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getCommands().get(i).getCommand())) {
                    AbstractCommand command = getCommands().get(i);
                    EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                            .setRandomColor()
                            .addField("Command Information:",
                                    "**Name**: " + command.getCommand() +
                                            "\n**Description**: " + command.getDescription() +
                                            "\n**Category**: " + command.getCategory() +
                                            "\n**Usage**: " + command.getUsage()).build();
                    c.sendMessage(embed.build()).queue();
                }
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.HELP;
    }

    @Override
    public String getDescription() {
        return "Give information about a specific command";
    }

    @Override
    public String getUsage() {
        return "-command <command here>";
    }

    @Override
    public long getDelay() {
        return 0;
    }

    public static ArrayList<String> getCommandNames() {
        return names;
    }

    public static ArrayList<String> getNames() {
        return names;
    }

    public static ArrayList<String> getCategories() {
        return categories;
    }

    public static ArrayList<String> getDesc() {
        return desc;
    }

    public static ArrayList<String> getUsages() {
        return usage;
    }

    public static ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

}
