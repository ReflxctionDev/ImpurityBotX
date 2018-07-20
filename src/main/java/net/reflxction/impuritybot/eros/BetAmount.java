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
package net.reflxction.impuritybot.eros;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.data.CreditsManager;
import net.reflxction.impuritybot.utils.lang.NumberUtils;

public class BetAmount extends AbstractCommand {

    private CreditsManager manager = new CreditsManager();
    private NumberUtils nu = new NumberUtils();

    @Override
    public String getCommand() {
        return "betm";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        switch (args.length) {
            case 0:
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
                break;
            case 1:
                int amount = 0;
                if (!args[0].equalsIgnoreCase("all")) {
                    try {
                        amount = Integer.parseInt(args[0]);
                        manager.removeCredis(u, amount);
                    } catch (NumberFormatException ex) {
                        c.sendMessage("**Expected a number value, but found **`" + args[1] + "`").queue();
                    }
                    int chance = nu.randomBetween(0, 100);
                    if (chance <= 10) {
                        manager.addCredits(u, amount * 2);
                        c.sendMessage("You have successfully bet **" + amount + "** and received the double (**" + (amount * 2) + "**)").queue();
                    } else {
                        c.sendMessage("You have bet **" + amount + "** and lost everything!").queue();
                    }
                } else {
                    amount = manager.getUserCredits(u);
                    manager.removeCredis(u, amount);
                    int chance = nu.randomBetween(0, 100);
                    if (chance <= 20) {
                        manager.addCredits(u, amount * 2);
                        c.sendMessage("You have successfully bet **" + amount + "** and received the double (**" + (amount * 2) + "**)").queue();
                    } else {
                        c.sendMessage("You have bet **" + amount + "** and lost everything!").queue();
                    }
                }
                break;
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
        return "Bet an amount of coins with a chance to double the amount or lose them";
    }

    @Override
    public String getUsage() {
        return "-bet <amount>";
    }
}
