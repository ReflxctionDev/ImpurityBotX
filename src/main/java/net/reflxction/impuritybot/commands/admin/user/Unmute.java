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
package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.discord.MuteManager;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

public class Unmute extends AbstractCommand {

    private MuteManager manager = new MuteManager();

    @Override
    public String getCommand() {
        return null;
    }


    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (!(g.getMember(u).getRoles().get(0).getPositionRaw() >= Roles.ADMIN.getPositionRaw())) {
            c.sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length != 1) {
            c.sendMessage("**Invalid usage. Usage: " + getUsage() + "**").queue();
            return;
        }
        Member executor = g.getMember(u);
        Member target;
        String id;
        try {
            id = StringUtils.mentionToId(args[0]);
            target = g.getMember(j.getUserById(id));
            if (executor == target) {
                c.sendMessage("**You can't unmute yourself**").queue();
                return;
            }
            if (target.getUser().isBot()) {
                c.sendMessage("**You can't unmute bots**").queue();
                return;
            }
            if (!manager.isMuted(target.getUser())) {
                c.sendMessage(target.getUser().getName() + " is not muted!").queue();
                return;
            }
            if (executor.getRoles().get(0).getPositionRaw() >= target.getRoles().get(0).getPositionRaw()) {
                c.sendMessage("**You are not allowed to unmute a member with higher or equal role**").queue();
            }
            MuteManager manager = new MuteManager();
            manager.unmute(target.getUser());
            sendNews(target.getUser().openPrivateChannel().complete(), target, executor);
            c.sendMessage("**Successfully unmuted " + target.getUser().getAsMention() + "**").queue();
        } catch (NumberFormatException e) {
            c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Unmutes a specified member";
    }

    @Override
    public String getUsage() {
        return "-unmute <@user>";
    }

    private void sendNews(MessageChannel channel, Member target, Member executor) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setTitle(target.getUser().getName())
                .setColor(Color.decode("#4cd137"))
                .addField("You are unmuted", "by " + executor.getUser().getName())
                .build();
        channel.sendMessage(builder.build()).queue();
    }
}
