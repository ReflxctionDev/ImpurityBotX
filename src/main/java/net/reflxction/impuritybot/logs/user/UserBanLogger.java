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
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class UserBanLogger extends Logger {

    @Override
    public void onGuildBan(GuildBanEvent event) {
        final User u = event.getUser();
        final Guild g = event.getGuild();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.RED)
                .setDescription("User " + u.getName() + " has been banned")
                .setThumbnail(u.getAvatarUrl())
                .setFooter("User ID: " + u.getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
