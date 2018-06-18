package net.reflxction.impuritybot.commands.fun.exclusive;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.NumberUtils;

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

public class Rate extends AbstractCommand {

    private final NumberUtils nu = new NumberUtils();

    private boolean fromImpurity(User u) {
        JDA j = ImpurityBot.getJDA();
        Guild g = j.getGuildById("363721897743089668");
        Member m = g.getMember(u);
        Role role = g.getRoleById("413362035334840324");
        return m.getRoles().contains(role);
    }

    @Override
    public String getCommand() {
        return "rate";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (fromImpurity(u)) {
            if (args.length == 0) {
                c.sendMessage("**Invalid arguments! Try " + getUsage() + "**").queue();
            }
            if (args.length >= 1) {
                StringBuilder term = new StringBuilder();
                for (String arg1 : args) {
                    String arg = arg1 + " ";
                    term.append(arg);
                }
                final int d1 = nu.randomBetween(0, 99);
                final int d2 = nu.randomBetween(0, 99);
                c.sendMessage("I'd rate **" + term + "**a **" + d1 + "." + d2 + "/100**.").queue();
            }
        } else {
            c.sendMessage("**This command is exclusive for the Impurity members only! Contact an administrator if you believe this is not supposed to happen.**").queue();
        }
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
        return "Rate a term";
    }

    @Override
    public String getUsage() {
        return "-rate <term>";
    }
}
