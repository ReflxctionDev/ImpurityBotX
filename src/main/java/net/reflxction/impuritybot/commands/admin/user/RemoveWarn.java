package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.data.WarningsManager;

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

public class RemoveWarn extends AbstractCommand {

    private ImpurityBot bot;

    public RemoveWarn(ImpurityBot bot) {
        this.bot = bot;
    }

    private final WarningsManager wu = new WarningsManager(bot);


    @Override
    public String getCommand() {
        return "removewarn";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        Member member = g.getMember(u);
        if (member.hasPermission(Permission.MANAGE_ROLES)) {
            if (args.length == 0) {
                c.sendMessage("**Invalid usage. Try **`-removewarn <@user> <amount>`**.**").queue();
            }
            if (args.length == 1) {
                User target;
                try {
                    target = j.getUserById(StringUtils.mentionToId(args[0]));
                    if (wu.getWarnings(target) > 0) {
                        wu.removeWarning(target, u);
                        wu.removeWarning(target, u);
                        PrivateChannel pm = target.openPrivateChannel().complete();
                        pm.sendMessage("<@" + u.getId() + "> has removed a warning from your total warnings.").queue();
                        c.sendMessage("Removed **1** of **" + target.getName() + "**'s warnings. Total warnings: **" + wu.getWarnings(target) + "**").queue();
                    } else {
                        c.sendMessage("**" + target.getName() + "** does not have any warnings!").queue();
                    }
                } catch (NumberFormatException ex) {
                    c.sendMessage("**Invalid arguments! Expected a user mention (or id), but found** `" + args[0] + "`").queue();
                } catch (UnsupportedOperationException ignored) {

                }
            }
            if (args.length == 2) {
                User target;
                try {
                    target = j.getUserById(StringUtils.mentionToId(args[0]));
                    if (wu.getWarnings(target) > 0) {
                        wu.removeWarnings(target, u, Integer.parseInt(args[1]));
                        PrivateChannel pm = target.openPrivateChannel().complete();
                        pm.sendMessage("<@" + u.getId() + "> has removed " + args[1] + " from your total warnings. Current warnings: **" + wu.getWarnings(u) + "**").queue();
                        c.sendMessage("Removed **" + args[1] + "** of **" + target.getName() + "**'s warnings. Total warnings: **" + wu.getWarnings(target) + "**").queue();
                    } else {
                        c.sendMessage("**" + target.getName() + "** does not have any warnings!").queue();
                    }
                } catch (NumberFormatException ex) {
                    c.sendMessage("**Invalid arguments! Expected a user mention (or id), but found** `" + args[0] + "`").queue();
                } catch (UnsupportedOperationException ex) {
                    c.sendMessage("**Noob you can't manage warns for bots L**").queue();
                }
            }
        } else {
            c.sendMessage("**You don't have permission to remove warnings from users! Contact an administrator if you believe this isn't supposed to happen.**").queue();
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
        return "Remove a warning or more from a specific user.";
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
