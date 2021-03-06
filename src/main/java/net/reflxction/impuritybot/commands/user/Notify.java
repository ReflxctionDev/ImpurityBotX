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
package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

/**
 * Created by Reflxction, on 02/01/18.
 */
public class Notify extends AbstractCommand {

    @Override
    public String getCommand() {
        return "notify";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        Role updates = g.getRolesByName("Bot updates", true).get(0);
        if (args.length == 0) {
            addRole(u, g, c);
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("remove")) {
                g.getController().removeSingleRoleFromMember(g.getMember(u), updates).queue();
                c.sendMessage("**You will not be notified on bot updates**").queue();
                return;
            } else if (args[0].equalsIgnoreCase("add")) {
                addRole(u, g, c);
            } else {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
            }
        } else {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
        }
    }

    private void addRole(User user, Guild g, MessageChannel c) {
        Role updates = g.getRolesByName("Bot updates", true).get(0);
        if (g.getMember(user).getRoles().contains(updates)) {
            c.sendMessage("**You already have bot notifications enabled**").queue();
        }
        g.getController().addSingleRoleToMember(g.getMember(user), updates).queue();
        c.sendMessage("**You will now be notified on bot updates**").queue();
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
        return "Get the notify role";
    }

    @Override
    public String getUsage() {
        return "-notify / -notify remove";
    }
}
