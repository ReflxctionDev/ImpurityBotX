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
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.data.PointsManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class MMPoints extends AbstractCommand {

    private PointsManager manager = new PointsManager();

    @Override
    public String getCommand() {
        return "mmpoints";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (g.getMember(u).getRoles().contains(Roles.STAFF)) {
            if (args.length < 2) {
                c.sendMessage("**Incorrect usage. Try " + getUsage() + "**.").queue();
            }
            if (args.length == 2) {
                if (args[0].equals("reset")) {
                    try {
                        String id = StringUtils.mentionToId(args[1]);
                        User target = j.getUserById(id);
                        manager.setUserPoints(target, 0);
                        c.sendMessage("**" + target.getName() + "**'s points have been reset.").queue();
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a user mention (or id), but found** `" + args[1] + "`**.**").queue();
                    }
                } else {
                    c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    int p;
                    User target = null;
                    try {
                        String id = StringUtils.mentionToId(args[1]);
                        target = j.getUserById(id);
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a user mention (or id), but found** `" + args[1] + "`**.**").queue();
                    }
                    try {
                        p = Integer.parseInt(args[2]);
                        manager.setUserPoints(target, p);
                        c.sendMessage("**" + target.getName() + "**'s points have been set to " + p).queue();
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
                    }

                } else if (args[0].equalsIgnoreCase("add")) {
                    int p;
                    User target = null;
                    try {
                        String id = StringUtils.mentionToId(args[1]);
                        target = j.getUserById(id);
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a user mention (or id), but found** `" + args[1] + "`**.**").queue();
                    }
                    try {
                        p = Integer.parseInt(args[2]);
                        manager.givePoints(target, p);
                        c.sendMessage("**" + target.getName() + "** has been given **" + p + "** points. Total: **" + manager.getUserPoints(target) + "**").queue();
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    int p;
                    User target = null;
                    try {
                        String id = StringUtils.mentionToId(args[1]);
                        target = j.getUserById(id);
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a user mention (or id), but found** `" + args[1] + "`**.**").queue();
                    }
                    try {
                        p = Integer.parseInt(args[2]);
                        if (p <= manager.getUserPoints(target)) {
                            manager.removePoints(target, p);
                            c.sendMessage("**" + p + "** have been removed from **" + target + "**. Total: " + manager.getUserPoints(target)).queue();
                        } else {
                            c.sendMessage("**" + target.getName() + "** only has **" + manager.getUserPoints(target) + "**. You can't remove **" + p + "** from them!").queue();
                        }
                    } catch (NumberFormatException e) {
                        c.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
                    }
                }
            }
        } else {
            c.sendMessage("**You don't have permission to run this command.**").queue();
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
        return "Manage points";
    }

    @Override
    public String getUsage() {
        return "-mmpoints <add / remove / reset / set> <@user> [amount]";
    }
}
