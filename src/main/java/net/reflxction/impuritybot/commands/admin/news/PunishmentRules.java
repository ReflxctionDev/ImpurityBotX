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

import java.awt.*;

public class PunishmentRules extends AbstractCommand {
    @Override
    public String getCommand() {
        return "punishment";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        m.delete().queue();
        EmbedBuilder b = new EmbedFactory(new EmbedBuilder())
                .setDescription("Punishments you may face when not following the rules above:\n")
                .addField("Spam", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban", true)
                .addField("Swearing", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban\n", true)
                .addField("Not obeying higher people", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban", true)
                .addField("Not using the correct channel", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban", true)
                .setColor(Color.RED)
                .setFooter("Accumulation of warnings may get you kicked, muted, demoted or even banned from our Discord server", null)
                .build();
        c.sendMessage(b.build()).queue();

    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Punishments";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
