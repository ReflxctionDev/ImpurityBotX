/*
 * * Copyright 2018 github.com/ReflxctionDev
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

package net.reflxction.impuritybot.commands.miscs;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.guild.GuildUtils;
import net.reflxction.impuritybot.utils.data.IgnManager;

import java.util.*;

public class Accept extends AbstractCommand {

    private IgnManager igns = new IgnManager();

    @Override
    public String getCommand() {
        return "accept";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        final Member member = g.getMember(u);
        if (member.getRoles().contains(Roles.UNREGISTERED)) {
            if (igns.hasAssignedIGN(u)) {
                List<Role> roles = new ArrayList<>();
                roles.add(Roles.D_MEMBER);
                if (GuildUtils.isGuildMember(u)) {
                    roles.add(Roles.I_MEMBER);
                }
                GuildUtils.controller().modifyMemberRoles(g.getMember(u), roles, Collections.singleton(Roles.UNREGISTERED)).queue();
            } else {
                c.sendMessage("**You must set your IGN first before accepting! Do so with `-ign <IGN>`**").queue();
            }
        } else {
            c.sendMessage("**You are already registered!**").queue();
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
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Accept our terms";
    }

    @Override
    public String getUsage() {
        return "-accept";
    }
}
