package net.reflxction.impuritybot.commands.user;

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

import java.awt.*;

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

public class About extends AbstractCommand {

    @Override
    public String getCommand() {
        return "about";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                .setAuthor("About me", null, j.getSelfUser().getAvatarUrl())
                .setDescription("\uD83E\uDD16 I'm Impurity's official Discord bot. I was created by <@211459080860991488>, from the help of some other people, such as <@305665281022361620> and <@127529580604030977>. My prefix is '-', and I provide many commands and features!")
                .setColor(Color.GREEN)
                .build();
        c.sendMessage(embed.build()).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public String getDescription() {
        return "Show information about the bot";
    }

    @Override
    public String getUsage() {
        return "-credits";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
