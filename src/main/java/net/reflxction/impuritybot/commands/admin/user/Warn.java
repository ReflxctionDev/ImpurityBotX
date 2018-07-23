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
package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.WarningsManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class Warn extends AbstractCommand {

    private ImpurityBot bot;

    public Warn(ImpurityBot bot) {
        this.bot = bot;
    }

    private final WarningsManager wu = new WarningsManager();


    @Override
    public String getCommand() {
        return "warn";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        Member member = g.getMember(u);
        if (member.hasPermission(Permission.MANAGE_ROLES) || member.getRoles().get(0).getPositionRaw() >= Roles.HELPER.getPositionRaw()) {
            if (args.length == 0) {
                c.sendMessage("**Incorrect usage. Try -warn <user> <reason>**").queue();
            }
            if (args.length == 1) {
                c.sendMessage("**Incorrect usage. Try -warn <user> <reason>**").queue();
            }
            if (args.length >= 2) {
                try {
                    StringBuilder reason = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        String arg = args[i] + " ";
                        reason.append(arg);
                    }
                    User warned = j.getUserById(StringUtils.mentionToId(args[0]));
                    if (member.getRoles().get(0).getPosition() >= g.getMember(warned).getRoles().get(0).getPosition()) {
                        wu.giveWarning(u, warned, reason.toString(), ((TextChannel) c));
                        c.sendMessage("**" + warned.getName() + "** has been warned.").queue();
                        c.sendMessage("Total warnings for this user: **" + wu.getWarnings(warned) + "**.").queue();
                        PrivateChannel pm = warned.openPrivateChannel().complete();

                        pm.sendMessage("**You've been warned by <@" + u.getId() + ">.** Reason: **" + reason + "**").queue();
                        pm.sendMessage("Total warnings: **" + wu.getWarnings(warned) + "**").queue();
                    } else {
                        c.sendMessage("**" + warned.getName() + "**'s highest role is higher than yours! Contact an administrator if you believe this shouldn't happen.").queue();
                    }
                } catch (NumberFormatException ex) {
                    c.sendMessage("**Invalid arguments! Expected a user mention (or id) but found** `" + args[0] + "`").queue();
                } catch (UnsupportedOperationException ex) {
                    c.sendMessage("**Noob you can't warn bots L**").queue();
                }
            }
        } else {
            c.sendMessage("**You don't have permission to warn users! Contact an administrator if you believe this isn't supposed to happen.**").queue();
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
    public String getDescription() {
        return "Warn a user";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
