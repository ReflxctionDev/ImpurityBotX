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
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.data.IgnManager;

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

public class IGNOf extends AbstractCommand {

    private IgnManager igns = new IgnManager();

    @Override
    public String getCommand() {
        return "ignof";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                    .setTitle("Not enough arguments!")
                    .setDescription("Try -ignof <@user>")
                    .build();
            c.sendMessage(embed.build()).queue();
        }
        if (args.length == 1) {
            try {
                User target = j.getUserById(StringUtils.mentionToId(args[0]));
                if (!igns.hasAssignedIGN(target)) {
                    c.sendMessage("**I couldn't find an IGN for this user. Are you sure they have assigned it yet?**").queue();
                } else {
                    EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                            .setDescription(g.getMember(target).getNickname() == null ? target.getName() : g.getMember(target).getNickname())
                            .addField("Discord Name", g.getMember(target).getNickname() == null ? target.getName() : g.getMember(target).getNickname())
                            .addField("IGN", igns.getIGN(target))
                            .setRandomColor()
                            .build();
                    c.sendMessage(builder.build()).queue();
                }
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
        return CommandCategory.MINECRAFT;
    }

    @Override
    public String getDescription() {
        return "Get the IGN of the specified user";
    }

    @Override
    public String getUsage() {
        return "-ignof <@user>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
