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
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.eros.Emotes;
import net.reflxction.impuritybot.core.listeners.DelayManager;
import net.reflxction.impuritybot.utils.guild.GuildUtils;
import net.reflxction.impuritybot.utils.lang.NumberUtils;
import net.reflxction.impuritybot.utils.data.CreditsManager;
import net.reflxction.impuritybot.utils.data.exp.ExpManager;

public class Open extends AbstractCommand {

    private NumberUtils nu = new NumberUtils();
    private CreditsManager cu = new CreditsManager();
    private ExpManager eu = new ExpManager();
    private DelayManager dm = new DelayManager();

    @Override
    public String getCommand() {
        return "open";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length != 1) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
        } else {
            if (!args[0].equalsIgnoreCase("can") && !args[0].equalsIgnoreCase("chest")) {
                c.sendMessage("**Invalid item to open. Try " + getUsage() + "**").queue();
            } else {
                if (args[0].equalsIgnoreCase("chest")) {
                    if (cu.getChests(u) > 0) {
                        cu.giveChest(u, -1);
                        c.sendMessage(Emotes.TREASURE.e() + " **Opening your Impure Chest...**").queue();
                        int random = nu.randomBetween(0, 100);
                        // COMMON
                        if (nu.isBetween(random, 0, 40)) {
                            c.sendMessage("(" + Emotes.CAN.e() + ") You've received: **Impure Can**! Open it with `-open can`").queue();
                            cu.giveCan(u, 1);
                        }
                        // RARE
                        if (nu.isBetween(random, 41, 70)) {
                            int i = nu.randomBetween(0, 1);
                            switch (i) {
                                case 0:
                                    //
                                    int given = nu.randomBetween(3000, 5000);
                                    eu.setUserExp(u, eu.getUserExp(u) + given);
                                    c.sendMessage("(" + Emotes.EXP.e() + ") You've received: **" + given + "** exp! You now have **" + eu.getUserExp(u) + "**.").queue();
                                    //
                                    break;
                                case 1:
                                    //
                                    cu.giveDice(u);
                                    c.sendMessage("(" + Emotes.DICE.e() + ") You've received: **Super Dice**! Roll it with `-roll`!").queue();
                                    //
                                    break;
                            }
                        }
                        // EPIC
                        if (nu.isBetween(random, 71, 90)) {
                            Role aki = GuildUtils.guild().getRoleById("425640651648925698");
                            GuildUtils.controller().addSingleRoleToMember(g.getMember(u), aki).queue();
                            c.sendMessage("(" + Emotes.AKINATOR.e() + ") You've received: **Akinator Channel Access**! Play with <@356065937318871041> in <#397992804296687626>!").queue();
                        }
                        // LEGENDARY
                        if (nu.isBetween(random, 91, 101)) {
                            int given = nu.randomBetween(2000, 7000);
                            cu.setUserCredits(u, cu.getUserCredits(u) + given);
                            c.sendMessage("(" + Emotes.CREDITS.e() + ") You've received: **" + given + "** credits! You now have **" + cu.getUserCredits(u) + "**.").queue();
                        }
                    } else if (cu.getChests(u) == 0) {
                        c.sendMessage("**You don't have any impure chests to open!**").queue();
                    }
                }
            }
            if (args[0].equalsIgnoreCase("can")) {
                if (cu.getCans(u) > 0) {
                    cu.giveCan(u, -1);
                    c.sendMessage(Emotes.CAN.e() + " **Opening your Impure Can...**").queue();
                    int random = nu.randomBetween(0, 100);
                    // NORMAL SODA
                    if (nu.isBetween(random, 0, 50)) {
                        int given = nu.randomBetween(100, 1000);
                        c.sendMessage("You've received: **Normal Soda**! You've been given **" + given + "** exp").queue();
                        eu.setUserExp(u, eu.getUserExp(u) + given);
                    }
                    // EXPLODING SODA
                    if (nu.isBetween(random, 51, 70)) {
                        c.sendMessage("You've received: **Exploding Soda**! **200** credits will be taken away from you").queue();
                        cu.setUserCredits(u, cu.getUserCredits(u) - 200);
                    }
                    // IMPOVERISHING SODA
                    if (nu.isBetween(random, 71, 80)) {
                        c.sendMessage("You've received: **Impoverishing Soda**! You will not be able to use -daily for an entire day.").queue();
                        dm.noDailyForYou(u);
                    }
                    // SILENCE SODA
                    if (nu.isBetween(random, 81, 90)) {
                        c.sendMessage("You've received: **Silence Soda**! You will be muted for 10 minutes").queue();
                        dm.mute(u);
                    }
                    // GENIE SODA
                    if (nu.isBetween(random, 91, 95)) {
                        cu.giveChest(u, 1);
                        c.sendMessage("You've received: **Genie Soda**! You have been given an Impure Chest").queue();
                    }
                    if (nu.isBetween(random, 96, 100)) {
                        c.sendMessage("You've received: **Nothing**! Too bad").queue();
                    }
                } else if (cu.getCans(u) == 0) {
                    c.sendMessage("**You don't have any impure cans to open!**").queue();
                }
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
        return "Open a chest or a can";
    }

    @Override
    public String getUsage() {
        return "-open <chest | can>";
    }

}
