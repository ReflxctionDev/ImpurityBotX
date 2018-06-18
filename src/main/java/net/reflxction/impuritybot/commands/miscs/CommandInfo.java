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

import java.util.ArrayList;

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

public class CommandInfo extends AbstractCommand {

    public static ArrayList<String> names = new ArrayList<>();

    public static ArrayList<String> categories = new ArrayList<>();

    public static ArrayList<String> desc = new ArrayList<>();

    public static ArrayList<String> usage = new ArrayList<>();

    @Override
    public String getCommand() {
        return "command";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length == 0) {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                    .setTitle("Not enough arguments!")
                    .setDescription("Try -command <command>")
                    .build();
            c.sendMessage(embed.build()).queue();
        }
        if (args.length == 1) {
            for (int i = 0; i < names.size(); i++) {
                if (args[0].equalsIgnoreCase(names.get(i))) {
                    EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                            .setDescription("Command Information\n")
                            .addField("Name:", names.get(i) + "\n", true)
                            .addField("Description", desc.get(i) + "\n", true)
                            .addBlankField()
                            .addField("Category", categories.get(i) + "\n", true)
                            .addField("Usage", usage.get(i), true)
                            .build();
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

    public static ArrayList<String> getCommands() {
        return names;
    }

}
