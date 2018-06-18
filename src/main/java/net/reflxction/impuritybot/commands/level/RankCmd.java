package net.reflxction.impuritybot.commands.level;

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
import net.reflxction.impuritybot.utils.data.exp.ExpManager;
import net.reflxction.impuritybot.utils.data.level.LevelManager;

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

public class RankCmd extends AbstractCommand {

    private LevelManager lu = new LevelManager();

    private ExpManager eu = new ExpManager();

    @Override
    public String getCommand() {
        return "rank";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length == 0) {
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setThumbnail(u.getAvatarUrl())
                    .setTitle(u.getName() + "'s level")
                    .setDescription("\n")
                    .addField("Exp", StringUtilsL.s(eu.getUserExp(u)) + "/1000", true)
                    .addField("Level", StringUtilsL.s(lu.getUserLevel(u)), true)
                    .setRandomColor()
                    .setFooter("Requested by " + u.getName(), null).build();
            c.sendMessage(builder.build()).queue();
        }
        if (args.length == 1) {
            try {
                User user = j.getUserById(StringUtilsL.mentionToId(args[0]));
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setThumbnail(user.getAvatarUrl())
                        .setTitle(user.getName() + "'s level")
                        .setDescription("\n")
                        .addField("Exp", StringUtilsL.s(eu.getUserExp(user)) + "/1000", true)
                        .addField("Level", StringUtilsL.s(lu.getUserLevel(user)), true)
                        .setRandomColor()
                        .setFooter("Requested by " + u.getName(), null).build();
                c.sendMessage(builder.build()).queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**The argument must be a user mention, but found** `" + args[0] + "`**!**").queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.LEVEL;
    }

    @Override
    public String getDescription() {
        return "Get someone's/your rank";
    }

    @Override
    public String getUsage() {
        return "-rank <@user>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
