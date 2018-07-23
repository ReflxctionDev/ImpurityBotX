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
package net.reflxction.impuritybot.commands.minecraft;

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
import net.reflxction.impuritybot.utils.data.IgnManager;

import java.awt.*;

public class PlayerIGN extends AbstractCommand {

    private IgnManager igns = new IgnManager();

    @Override
    public String getCommand() {
        return "playerign";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            c.sendMessage("**Invalid arguments! Try -playerign <ign>").queue();
        }
        if (args.length == 1) {
            String ign = args[0];
            if (igns.getUserByIGN(ign).size() == 0) {
                c.sendMessage("**I couldn't find any user with that IGN.**").queue();
            } else {
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setColor(Color.WHITE)
                        .setDescription("**User found**")
                        .addField("User", "<@" + igns.getUserByIGN(ign).get(0).getId() + ">")
                        .addField("IGN", igns.getIGN(igns.getUserByIGN(ign).get(0)))
                        .build();
                c.sendMessage(builder.build()).queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MINECRAFT;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get the user with a specific IGN";
    }

    @Override
    public String getUsage() {
        return "-playerign <ign>";
    }
}
