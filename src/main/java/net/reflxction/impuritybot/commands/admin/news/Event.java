package net.reflxction.impuritybot.commands.admin.news;

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
import net.reflxction.impuritybot.utils.lang.StringUtils;

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

public class Event extends AbstractCommand {
    @Override
    public String getCommand() {
        return "event";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        String content = getMessageContent();
        int eventtime1 = content.indexOf("(");
        int eventtime2 = content.indexOf(")");
        String time = content.substring(eventtime1, eventtime2).replace("(", "").replace(")", "");

        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setRandomColor()
                .setDescription("**New event!**\n")
                .addField("Event description", content
                        .replace(content.substring(eventtime1, eventtime2), "")
                        .replace("(", "")
                        .replace(")", "")
                        .replace("-event ", "")
                        .replace("-eventz ", ""), true)
                .addField("Time: ", time, true)
                .setFooter("By " + u.getName() + ", at " + StringUtils.getTimeEST(), null)
                .build();
        channel.sendMessage(builder.build()).queue();
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
        return "Declare an event";
    }

    @Override
    public String getUsage() {
        return "-event <The event description (The time of the event)>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
