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
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

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
public class Announce extends AbstractCommand {

    @Override
    public String getCommand() {
        return "announce";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel channel, User u, String[] args) {
        if (u.isBot()) {
            return;
        } else {
            if (channel.getId().equals("374104070945767424")) {
                EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                        .setRandomColor()
                        .setFooter("By " + u.getName() + ", at " + StringUtilsL.getTimeEST(), null)
                        .setAuthor("\uD83D\uDCE2 Guild announcement", null, g.getIconUrl())
                        .setDescription(StringUtilsL.capitalize(StringUtilsL.fixGrammar(getMessageContent().substring(10))))
                        .build();
                channel.sendMessage(embed.build()).queue();
                channel.deleteMessageById(m.getId()).queue();
            }

        }
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
        return "Announce a message";
    }

    @Override
    public String getUsage() {
        return "-announce <announcement>";
    }

    @Override
    public long getDelay() {
        return 0;
    }

}