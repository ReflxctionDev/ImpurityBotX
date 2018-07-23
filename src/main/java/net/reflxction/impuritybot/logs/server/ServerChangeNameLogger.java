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
package net.reflxction.impuritybot.logs.server;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNameEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class ServerChangeNameLogger extends Logger {

    @Override
    public void onGuildUpdateName(GuildUpdateNameEvent event) {
        final String old = event.getOldName();
        final String newname = event.getGuild().getName();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.YELLOW)
                .setAuthor(event.getGuild().getName(), null, event.getGuild().getIconUrl())
                .addField("Before", old)
                .addField("New", newname)
                .setFooter("Guild ID: " + event.getGuild().getId() + " • " + StringUtils.getTimeEST(), null)
                .build();
        getLogs().sendMessage(builder.build()).queue();
    }

}
