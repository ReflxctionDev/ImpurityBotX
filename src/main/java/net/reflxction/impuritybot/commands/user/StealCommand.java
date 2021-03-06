/*
 * * Copyright 2017-2018 github.com/BrokenEarthDev
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

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.discord.Steal;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class StealCommand extends AbstractCommand {

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        User user = event.getMember().getUser();
        JDA jda = event.getJDA();
        Guild guild = event.getGuild();
        Message message = event.getMessage();
        if (args.length < 2) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        User target;
        int count;
        try {
            String id = StringUtils.mentionToId(args[0]);
            target = jda.getUserById(id);
        } catch (Exception e) {
            channel.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
            return;
        }
        try {
            count = Integer.parseInt(args[1]);
        } catch (Exception e) {
            channel.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
            return;
        }
        Steal steal = new Steal();
        steal.steal(guild.getMember(target), guild.getMember(user), count, channel);
    }

    @Override
    public String getCommand() {
        return "steal";
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getUsage() {
        return "-steal <@user> <credits>";
    }

    @Override
    public String getDescription() {
        return "20% chance to steal at most of 20% of the target user's" +
                " credits";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CREDITS;
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }
}
