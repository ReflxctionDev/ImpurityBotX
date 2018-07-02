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

import java.awt.*;
import java.util.HashMap;

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

public class MakeEmbed extends AbstractCommand {

    @Override
    public String getCommand() {
        return "embed";
    }

    @Override
    public void process(JDA jda, Guild guild, Message message, MessageChannel channel, User user, String[] args) {
        if (args.length == 0) {
            channel.sendMessage("**Invalid usage** Try " + getUsage()).queue();
            return;
        }
        boolean foundTitle = false;
        boolean foundFieldTitle = false;
        boolean foundFieldBody = false;
        String recent = "";
        String title = "";
        String titleField = "";
        String BodyField = "";
        for (String arg : args) {
            if (arg.equals("title:")) {
                if (foundTitle) {
                    channel.sendMessage("Please enter the title once").queue();
                    break;
                }
                recent = "title:";
                foundTitle = true;
            } else if (arg.equals("t-field:")) {
                if (foundFieldTitle) {
                    channel.sendMessage("Please enter the field title once").queue();
                    break;
                }
                recent = "t-field:";
                foundFieldTitle = true;
            } else if (arg.equals("b-field:")) {
                if (foundFieldBody) {
                    channel.sendMessage("Please enter the field body once").queue();
                    break;
                }
                recent = "b-field:";
                foundFieldBody = true;
            } else {
                if (recent.equals("title:")) {
                    title += arg;
                } else if (recent.equals("t-field:")) {
                    titleField += arg;
                } else if (recent.equals("b-field:")) {
                    BodyField += arg;
                } else {
                    channel.sendMessage("Please use **title:<text>** for title, **t-field:<text>** for field title, and **b-field:<text>** for field body").queue();
                    break;
                }
            }
        }
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.decode("#e84118"))
                .setTitle(title)
                .addField(titleField, BodyField, false)
                .build();
        channel.sendMessage(builder.build()).queue();
    }

    private String parse(String arg, String startPoint) {
        char[] array = arg.toCharArray();
        int breakpoint = 0;
        String parsed = "";
        String previous = "";
        for (int i = 0; i < array.length; i++) {
            previous += array[i];
            if (previous.equals(startPoint)) {
                breakpoint = i;
            }
        }
        for (int i = breakpoint + 1; i > breakpoint && i < array.length; i++) {
            parsed += array[i];
        }
        return parsed;
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Create an embed";
    }

    @Override
    public String getUsage() {
        return "-makeembed title:<title> t-field:<field title> b-field:<field body>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}