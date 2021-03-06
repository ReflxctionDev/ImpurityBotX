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
package net.reflxction.impuritybot.commands.admin.botupdates;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;

public class FeatureRelease extends AbstractCommand {

    @Override
    public String getCommand() {
        return "feature";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        Message m = event.getMessage();
        m.delete().queue();
        if (u.getId().equals("211459080860991488")) {
            if (c.getId().equals("433614676086095890")) {
                StringBuilder feature = new StringBuilder();
                for (String argx : args) {
                    String arg = argx + " ";
                    feature.append(arg);
                }
                Roles.BOT_UPDATES.getManager().setMentionable(true).queue();
                c.sendMessage("<@&" + Roles.BOT_UPDATES.getId() + "> - " + feature.toString()).queue();
            }
        } else {
            c.sendMessage("**You don't have permission to use this command!**").queue();
        }
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
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Release a feature";
    }

    @Override
    public String getUsage() {
        return "-feature <information>";
    }
}
