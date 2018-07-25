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
package net.reflxction.impuritybot.commands.level;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.data.level.LevelManager;

public class SetLevel extends AbstractCommand {

    private LevelManager lvl = new LevelManager();

    @Override
    public String getCommand() {
        return "setlevel";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (!u.getId().equals("211459080860991488")) {
            c.sendMessage("**You don't have permission to use this command!**").queue();
        } else {
            if (args.length != 2) {
                c.sendMessage("**Incorrect arguments. Try " + getUsage() + "**").queue();
            } else {
                int i = 0;
                User target = null;
                try {
                    String id = StringUtils.mentionToId(args[0]);
                    target = j.getUserById(id);
                } catch (NumberFormatException ex) {
                    c.sendMessage("**Expected a user mention (or id), but found `" + args[0] + "`**").queue();
                }
                try {
                    i = Integer.parseInt(args[1]);
                } catch (NumberFormatException ex) {
                    c.sendMessage("**Expected a number, but found `" + args[1] + "`**").queue();
                }
                lvl.setUserLevel(target, i);
                assert target != null;
                c.sendMessage("**" + target.getName() + "**'s level has been set to " + i).queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.LEVEL;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Set a user level";
    }

    @Override
    public String getUsage() {
        return "-setlevel <@user> <level>";
    }
}
