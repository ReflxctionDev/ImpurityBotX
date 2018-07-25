package net.reflxction.impuritybot.credits.commands;

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
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.data.credits.CreditsManager;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.NumberUtils;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class Transfer extends AbstractCommand {

    private CreditsManager cu = new CreditsManager();
    private NumberUtils nu = new NumberUtils();

    @Override
    public String getCommand() {
        return "transfer";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        TextChannel c = ((TextChannel) event.getChannel());
        if (args.length != 2) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**.").queue();
        } else {
            int cr = 0;
            User target;
            try {
                cr = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                c.sendMessage("**Expected a number, but found `" + args[1] + "`!**").queue();
            }
            try {
                target = j.getUserById(StringUtils.mentionToId(args[0]));
                if (nu.isNegative(cr)) {
                    c.sendMessage("**You may not transfer negative amounts!**").queue();
                } else {
                    if (cu.getUserCredits(u) >= cr) {
                        cu.setUserCredits(u, cu.getUserCredits(u) - cr);
                        cu.setUserCredits(target, cu.getUserCredits(target) + cr);
                        c.sendMessage("You've successfully transferred **" + cr + "** to **" + target.getName() + "**.").queue();
                    } else {
                        c.sendMessage("**You don't have enough credits! You tried to transfer ` " + cr + "`, but you have `" + cu.getUserCredits(u) + "`!   **").queue();
                    }
                }
            } catch (NumberFormatException ex) {
                c.sendMessage("**Expected a user mention (or id), but found `" + args[0] + "`!**").queue();
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
        return "Transfer credits from your own balance to another user";
    }

    @Override
    public String getUsage() {
        return "-transfer <@user> <amount>";
    }


}
