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
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.awt.*;
import java.time.OffsetDateTime;

public class DiscordInfo extends AbstractCommand {

    @Override
    public String getCommand() {
        return "discordinfo";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        final String name = g.getName();
        final Member owner = g.getOwner();
        final OffsetDateTime date = g.getCreationTime();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor(name, null, g.getIconUrl())
                .setColor(Color.GREEN).setThumbnail(g.getIconUrl())
                .addField("Server Info", "**Name**: " + g.getName() + "\n**ID**: " + g.getId() + "\n**Members**: " + g.getMembers().size() + "\n**Owner**:" + owner.getUser().getName() + "\n**Date created**: " + date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear())
                .addField("Guild Info", "**Name**: Impurity\n**Guild Master**: Inariiiii\n**Guild Tag**: [IMPURE]\n**Date created**: 8th of Sep. 2017\n**Guild Texture Pack**: https://www.youtube.com/watch?v=XPVD5yqoENI (by <@275609695664734208>)")
                .addField("Useful links",  "**YouTube**: https://www.youtube.com/channel/UCCytGYl6gs58f-8FG8hoT1w\n**Twitter**: https://twitter.com/impurityguild\n**Forums thread**: https://hypixel.net/threads/impurity-impure-notthatpure-low-req-bedwars-guilds.1376085/")
                .build();
        c.sendMessage(builder.build()).queue();
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
        return "Get information about the Discord guild";
    }

    @Override
    public String getUsage() {
        return "-discordinfo";
    }
}
