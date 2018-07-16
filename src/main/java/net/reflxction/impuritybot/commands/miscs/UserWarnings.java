package net.reflxction.impuritybot.commands.miscs;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.data.WarningsManager;

import java.awt.*;

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

public class UserWarnings extends AbstractCommand {

    private ImpurityBot bot;

    public UserWarnings(ImpurityBot bot) {
        this.bot = bot;
    }

    private final WarningsManager wu = new WarningsManager();



    @Override
    public String getCommand() {
        return "uwarnings";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length == 0) {
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setColor(Color.RED)
                    .setDescription(u.getName() + " has a total of " + wu.getWarnings(u) + " warnings")
                    .build();
            c.sendMessage(builder.build()).queue();
        }
        if (args.length == 1) {
            try {
                User target = j.getUserById(StringUtils.mentionToId(args[0]));
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setColor(Color.RED)
                        .setDescription(target.getName() + " has " + wu.getWarnings(target) + " warnings")
                        .build();
                c.sendMessage(builder.build()).queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**Invalid arguments! Expected a user mention (or id) but found** `" + args[0] + "`").queue();
            }
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
    public String getDescription() {
        return "Get the current warnings of a user";
    }

    @Override
    public String getUsage() {
        return "-warnings / -warnings <@user>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
