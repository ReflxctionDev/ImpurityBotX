package net.reflxction.impuritybot.commands.admin.messages;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

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
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length > 0) {
            //if (u.getId().equalsIgnoreCase    ("319506736807542788")) {
            MessageChannel channel = j.getTextChannelById(args[0]);
            c.deleteMessageById(m.getId()).queue();
            channel.deleteMessageById(args[1]).queue();
            //}
        }
    }
}
