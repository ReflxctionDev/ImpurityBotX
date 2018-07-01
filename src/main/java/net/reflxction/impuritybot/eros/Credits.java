package net.reflxction.impuritybot.eros;

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

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.data.CreditsManager;

public class Credits extends AbstractCommand {

    private CreditsManager cu = new CreditsManager();

    @Override
    public String getCommand() {
        return "credits";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        Permission p = Permission.ADMINISTRATOR;
        Member mb = g.getMember(u);
        if (args.length <= 1) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
        } else {
            if (mb.hasPermission(p)) {
                if (args[0].equalsIgnoreCase("add")) {
                    User target = null;
                    int cr;
                    try {
                        target = j.getUserById(StringUtils.mentionToId(args[1]));
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a user mention (or id), but found **`" + args[0] + "`**.**    ").queue();
                    }
                    try {
                        cr = Integer.parseInt(args[2]);
                        cu.setUserCredits(target, cu.getUserCredits(target) + cr);
                        c.sendMessage("**" + target.getName() + "** has been given **" + cr + "**. Total credits: " + cu.getUserCredits(target)).queue();
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a number, but found **`" + args[1] + "`").queue();
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    User target = null;
                    int cr;
                    try {
                        target = j.getUserById(StringUtils.mentionToId(args[1]));
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a user mention (or id), but found **`" + args[0] + "`**.**    ").queue();
                    }
                    try {
                        cr = Integer.parseInt(args[2]);
                        cu.setUserCredits(target, cu.getUserCredits(target) - cr);
                        c.sendMessage("**" + target.getName() + "** has lost **" + cr + "** credits. Total credits: " + cu.getUserCredits(target)).queue();
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a number, but found **`" + args[1] + "`").queue();
                    }
                }

                if (args[0].equalsIgnoreCase("set")) {
                    User target = null;
                    int cr;
                    try {
                        target = j.getUserById(StringUtils.mentionToId(args[1]));
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a user mention (or id), but found **`" + args[0] + "`**.**").queue();
                    }
                    try {
                        cr = Integer.parseInt(args[2]);
                        cu.setUserCredits(target, cr);
                        c.sendMessage("**" + target.getName() + "**'s credits have been set to **" + cr + "**.").queue();
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a number, but found **`" + args[1] + "`").queue();
                    }
                }

                if (args[0].equalsIgnoreCase("reset")) {
                    User target = null;
                    try {
                        target = j.getUserById(StringUtils.mentionToId(args[1]));
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a user mention (or id), but found **`" + args[0] + "`**.**").queue();
                    }
                    try {
                        cu.setUserCredits(target, 0);
                        c.sendMessage("**" + target.getName() + "**'s credits have been set to 0.").queue();
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a number, but found **`" + args[1] + "`").queue();
                    }
                }

            } else {
                c.sendMessage("**You don't have permission to manage credits! Contact an administrator if you believe this is an error.**").queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CREDITS;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Manage credits for a specific user (admin only)";
    }

    @Override
    public String getUsage() {
        return "-credits <add | remove | set | clear> <@user>";
    }
}
