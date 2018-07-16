package net.reflxction.impuritybot.core.listeners.discord;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.utils.guild.GuildUtils;

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

public class Welcome extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        welcome(event);
        if (!event.getUser().isBot()) {
            GuildUtils.controller().addSingleRoleToMember(event.getMember(), GuildUtils.roleById("433595739416035338")).queue();
            PrivateChannel pm = event.getUser().openPrivateChannel().complete();
            pm.sendMessage("**Welcome to our Discord! Please visit <#452333985419493376> for steps on registering and getting access to the Discord. Enjoy your stay!**").queue();
        }
    }

    private void welcome(GuildMemberJoinEvent event) {
        Member m = event.getMember();
        Guild g = event.getGuild();
        String name = event.getGuild().getName();
        MessageChannel c = g.getTextChannelById("363721897743089671");
        String mention = "<@" + m.getUser().getId() + ">";
        if (name.equalsIgnoreCase("Impurity")) {
            c.sendMessage("Welcome, " + mention + " to the Impurity Discord server! Please make sure to give <#452333985419493376> a look before hanging out with us. Enjoy your stay! \uD83D\uDC4B").queue();
            g.getController().addRolesToMember(m, Roles.UNREGISTERED).queue();
        }
    }

}
