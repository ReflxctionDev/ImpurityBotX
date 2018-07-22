package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Channels;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtils;

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

public class Kick extends AbstractCommand {

    @Override
    public String getCommand() {
        return "kick";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        if (!(event.getMember().hasPermission(Permission.ADMINISTRATOR) && event.getMember().hasPermission(Permission.KICK_MEMBERS))) {
            event.getChannel().sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length < 3) {
            event.getChannel().sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        Member target;
        String id;
        try {
            id = StringUtils.mentionToId(args[0]);
            target = event.getGuild().getMemberById(id);
        } catch (Exception e) {
            event.getChannel().sendMessage("**Invalid input at field <@user>** Please put in the user's mention").queue();
            return;
        }
        if (target.getUser().getId().equals("211459080860991488") || target.getUser().getId().equals("348165612880789508")) {
            event.getChannel().sendMessage("**wHo dO yOu tHiNk yOu aRe tO kIcK dAdDy!!**").queue();
            return;
        }
        if (target.getRoles().get(0).getPositionRaw() >= Roles.IMPURE_BOT.getPositionRaw()) {
            event.getChannel().sendMessage("I can't kick **" + target.getUser().getName() + "** because of their role(s) being higher than mine").queue();
            return;
        }
        if (target.getRoles().get(0).getPositionRaw() >= event.getMember().getRoles().get(0).getPositionRaw()) {
            event.getChannel().sendMessage("You can't kick **" + target.getUser().getName() + "** because of their assigned role").queue();
            return;
        }
        String reason = StringUtils.combine(args, 1);
        event.getGuild().getController().kick(target).queue();
        sendInfo(target.getUser(), event.getMember().getUser(), reason);
        sendInfoToChannel(target.getUser(), event.getMember().getUser(), reason, Channels.LOGS);
    }

    private void sendInfo(User target, User executor, String reason) {
        try {
            PrivateChannel channel = target.openPrivateChannel().complete();
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setTitle("You are kicked from impurity")
                    .addField("You are kicked by", executor.getName())
                    .addField("Reason", reason)
                    .setDescription("If you believe that this is an error or you are innocent, please contact staff")
                    .setColor(Color.decode("#e84118"))
                    .build();
            channel.sendMessage(builder.build()).queue();
        } catch (Exception ignored) {}
    }

    private void sendInfoToChannel(User target, User executor, String reason, MessageChannel channel) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor(target.getName(), target.getAvatarUrl(), target.getAvatarUrl())
                .setTitle("Member got kicked")
                .addField("Executor", executor.getName())
                .addField("Target", target.getName())
                .addField("Reason", reason)
                .setColor(Color.decode("#e84118"))
                .build();
        channel.sendMessage(builder.build()).queue();
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
        return "Kick a user";
    }

    @Override
    public String getUsage() {
        return "-kick <@user> <reason>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
