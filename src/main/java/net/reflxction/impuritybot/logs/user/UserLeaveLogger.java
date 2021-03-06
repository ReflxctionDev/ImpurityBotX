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
package net.reflxction.impuritybot.logs.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class UserLeaveLogger extends Logger {

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        final User u = event.getUser();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_RED)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("User left")
                .addField("User info", "Name: " + u.getName() + "\nID: " + u.getId())
                .setThumbnail(u.getAvatarUrl())
                .setFooter("ID: " + u.getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
