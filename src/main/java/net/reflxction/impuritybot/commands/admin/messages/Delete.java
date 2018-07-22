package net.reflxction.impuritybot.commands.admin.messages;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Channels;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

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
public class Delete extends AbstractCommand {
    @Override
    public String getCommand() {
        return "delete";
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
        return "Delete a message";
    }

    @Override
    public String getUsage() {
        return "-delete <message id>";
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        if (!(event.getMember().hasPermission(Permission.ADMINISTRATOR) && event.getMember().hasPermission(Permission.MESSAGE_MANAGE))) {
            event.getChannel().sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length != 1) {
            event.getChannel().sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        String id = args[0];
        String wholeContent;
        try {
            Message message = event.getChannel().getMessageById(id).complete();
            wholeContent = message.getContentRaw();
            message.delete().queue();
            event.getChannel().sendMessage("**Successfully deleted message with id " + id + " in " + event.getChannel().getName()).queue();
            log(event.getMember().getUser(), message, wholeContent, Channels.LOGS);

        } catch (Exception e) {
            event.getChannel().sendMessage("**Invalid id or you may be trying to execute this command on a different channel**").queue();
        }
    }

    private void log(User executor, Message message, String wholeContent, MessageChannel channel) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(Color.decode("#fbc531"))
                .setTitle("Message deleted")
                .addField("Executor", executor.getName())
                .addField("Message id", message.getId())
                .addField("Message Content", wholeContent)
                .setAuthor(executor.getName(), executor.getAvatarUrl(), executor.getAvatarUrl())
                .build();
        channel.sendMessage(builder.build()).queue();

    }
}
