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
package net.reflxction.impuritybot.commands.points;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.utils.data.PointsManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * Command class which manages the "-points" command.
 *
 * @author Reflxction
 */
public class PointsBalance extends AbstractCommand {

    private PointsManager manager = new PointsManager();

    @Override
    public String getCommand() {
        return "points";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        switch (args.length) {
            case 0:
                c.sendMessage("You currently have **" + manager.getUserPoints(u) + "** points.").queue();
                break;
            case 1:
                try {
                    String id = StringUtils.mentionToId(args[0]);
                    User target = j.getUserById(id);
                    c.sendMessage("**" + target.getName() + "** currently has **" + manager.getUserPoints(u) + "** points.").queue();
                } catch (NumberFormatException ex) {
                    c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
                }
                break;
            default:
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
                break;
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.POINTS;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get the points of someone";
    }

    @Override
    public String getUsage() {
        return "-points / -points <@user>";
    }
}
