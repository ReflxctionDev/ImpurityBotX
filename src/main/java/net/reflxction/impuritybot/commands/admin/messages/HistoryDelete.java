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
package net.reflxction.impuritybot.commands.admin.messages;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HistoryDelete extends AbstractCommand {

    @Override
    public String getCommand() {
        return "clean";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (g.getMember(u).hasPermission(Permission.MESSAGE_MANAGE)) {
            if (args.length == 0) {
                channel.sendMessage("**Invalid arguments. Try -clean <amount of messages to delete>**").queue();
            }
            final List<Message> list;
            if (args.length == 1) {
                TextChannel c = (TextChannel) channel;
                MessageHistory history = new MessageHistory(c);
                m.delete().queue();
                list = history.retrievePast(Integer.parseInt(args[0]) + 1).complete();
                list.forEach(msg -> msg.delete().queue());
                Message success = c.sendMessage("**Successfully cleared " + args[0] + " message(s)**").complete();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        success.delete().queue();
                    }
                }, 3 * 1000);
            }
        } else {
            channel.sendMessage("**You don't have permission to clear messages! Please contact an admin if you believe this isn't supposed to happen.**").queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Delete a specific amount of messages in a channel";
    }

    @Override
    public String getUsage() {
        return "-delete <amount of messages to clear>";
    }

    @Override
    public long getDelay() {
        return 0;
    }

}
