package net.reflxction.impuritybot.commands.minecraft;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.hypixel.DName;
import net.reflxction.impuritybot.hypixel.IHypixelObjective;

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

public class PlayerRank extends AbstractCommand {
    @Override
    public String getCommand() {
        return "playerrank";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        if (getMessageContent().length() <= 12) {
            c.sendMessage("**Invalid usage. Try -playerrank <player>**").queue();
        }
        if (args.length == 1) {
            IHypixelObjective ob = new IHypixelObjective();
            c.sendMessage(DName.getName(args[0]) + "'s rank: " + ob.getRank(args[0])).queue();
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
    public String getDescription() {
        return "Get the rank of a player (Hypixel)";
    }

    @Override
    public String getUsage() {
        return "-playerrank <player>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
