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
package net.reflxction.impuritybot.logs.message;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class MessageEditedLogger extends Logger {

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        final User u = event.getAuthor();
        final Message m = event.getMessage();
        final Color orange = new Color(255, 119, 0);
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setColor(orange)
                .setDescription("**Message edited in #" + event.getChannel().getName() + "**")
                .setFooter("ID: " + m.getId() + " • " + StringUtils.getTimeEST(), null)
                .addField("New content", m.getContentRaw()).build();

        getLogs().sendMessage(builder.build()).queue();
    }

}
