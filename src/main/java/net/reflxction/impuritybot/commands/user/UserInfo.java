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
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;
import java.time.OffsetDateTime;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class UserInfo extends AbstractCommand {

    @Override
    public String getCommand() {
        return "userinfo";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            final Color color = g.getMember(u).getColor();
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setColor(color)
                    .setThumbnail(u.getAvatarUrl())
                    .setAuthor(u.getName(), null, u.getAvatarUrl())
                    .addField("User info", "Discord Name: " + u.getName() + "\nID: " + u.getId() + "\nJoined on: " + g.getMember(u).getJoinDate().getMonthValue() + "/" + g.getMember(u).getJoinDate().getDayOfMonth() + "/" + g.getMember(u).getJoinDate().getYear())
                    .build();
            c.sendMessage(builder.build()).queue();
        }
        if (args.length == 1) {
            final User x = j.getUserById(StringUtils.mentionToId(args[0]));
            final Color color = g.getMember(x).getColor();
            final OffsetDateTime date = g.getMember(u).getJoinDate();
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setColor(color)
                    .setThumbnail(x.getAvatarUrl())
                    .setAuthor(x.getName(), null, x.getAvatarUrl())
                    .addField("User info", "Discord Name: " + x.getName() + "\nID: " + x.getId() + "\nJoined on: " + date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear())
                    .build();
            c.sendMessage(builder.build()).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get information about a specific user";
    }

    @Override
    public String getUsage() {
        return "-userinfo / -userinfo <@user | id>";
    }
}
