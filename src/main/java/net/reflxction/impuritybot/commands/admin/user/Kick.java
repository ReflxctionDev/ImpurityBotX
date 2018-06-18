package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtilsL;

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

public class Kick extends AbstractCommand {

    @Override
    public String getCommand() {
        return "kick";
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
        return "Kick a user";
    }

    @Override
    public String getUsage() {
        return "-kick <@user> <reason>";
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel channel, User u, String[] args) {
        final Member member = g.getMember(u);
        if (args.length == 0 || args.length == 1) {
            channel.sendMessage("**Invalid arguments. Try -kick <user> <reason>**").queue();
        }
        if (args.length > 1) {
            if (g.getMember(u).hasPermission(Permission.KICK_MEMBERS) || g.getMember(u).hasPermission(Permission.ADMINISTRATOR)) {
                try {
                    User tr = j.getUserById(StringUtilsL.mentionToId(args[0]));
                    if (member.getRoles().get(0).getPosition() > g.getMember(tr).getRoles().get(0).getPosition()) {

                        if (tr.getId().equals("211459080860991488")) {
                            channel.sendMessage("**wHo dO yOu tHiNk yOu aRe tO kIcK dAdDy?!?!**").queue();
                        } else {
                            g.getController().kick(g.getMember(ImpurityBot.getJDA().getUserById(StringUtilsL.mentionToId(args[0])))).queue();
                            channel.sendMessage(args[0] + " has been kicked.").queue();
                            String reason = "";
                            for (int i = 1; i < args.length; i++) {
                                String arg = args[i] + " ";
                                reason = reason + arg;
                            }
                            j.getUserById(StringUtilsL.mentionToId(args[0])).openPrivateChannel().complete()
                                    .sendMessage("You've been kicked from the Impurity guild. Reason: **" + reason + "**").queue();

                        }
                    } else {
                        channel.sendMessage("**" + tr.getName() + "**'s highest role is higher than yours! Contact an administrator if you believe this shouldn't happen.").queue();
                    }
                } catch (Exception ex) {
                    channel.sendMessage("**Woops. This didn't seem to work. Please contact the developer**").queue();
                    ex.printStackTrace();
                }
            } else {
                channel.sendMessage("**You don't have permission to kick members! Contact an admin if you believe this isn't supposed to happen.**").queue();
            }

        }
    }
}
