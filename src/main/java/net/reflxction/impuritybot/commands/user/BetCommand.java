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
package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.discord.Bet;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.data.credits.CreditsManager;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.utils.lang.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BetCommand extends AbstractCommand {

    private CreditsManager manager = new CreditsManager();

    private List<BetRange> currentBets = new ArrayList<>();

    @Override
    public String getCommand() {
        return "bet";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        User user = event.getMember().getUser();
        JDA jda = event.getJDA();
        Guild guild = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("accept")) {
                    BetRange range = getRangeFor(user);
                    if (range != null) {
                        if (!range.hasExpired()) {
                            Bet bet = new Bet();
                            bet.bet(range.getM1(), range.getM2(), channel, range.getAmount());
                            range.setExpired();
                            currentBets.remove(range);
                        } else {
                            channel.sendMessage("**Your last bet has expired!**").queue();
                        }
                    } else {
                        channel.sendMessage("**You haven't got any bets or your last bet has expired!!**").queue();
                    }
                } else {
                    channel.sendMessage("**Invalid usage. Try " + getUsage() + "**").queue();
                }
            } else if (args.length == 2) {
                Member executor = guild.getMember(user);
                Member target;
                int amount;
                try {
                    String id = StringUtils.mentionToId(args[0]);
                    target = guild.getMember(jda.getUserById(id));
                } catch (Exception e) {
                    channel.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**.**").queue();
                    return;
                }
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    channel.sendMessage("**Expected a number, but found** `" + args[1] + "`**.**").queue();
                    return;
                }
                if (executor.getUser().equals(target.getUser())) {
                    channel.sendMessage("**You cannot start a bet with yourself!**").queue();
                } else {
                    if (manager.getUserCredits(user) >= amount) {
                        if (target.getUser().isBot()) {
                            channel.sendMessage("**You cannot bet bots").queue();
                            return;
                        }
                        BetRange range = new BetRange(executor, target, amount, false);
                        currentBets.add(range);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                range.setExpired();
                                currentBets.remove(range);
                            }
                        }, TimeUtils.minutesToSeconds(5) * 1000);
                        channel.sendMessage("You have started a bet with **" + target.getUser().getName() + "**. They have 5 minutes to accept").queue();
                    } else {
                        channel.sendMessage("**You don't have enough credits to bet this amount!**").queue();
                    }
                }
            }
        }
    }


    @Override
    public String getUsage() {
        return "-bet <@user> <amount>";
    }

    @Override
    public String getDescription() {
        return "bets a user";
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

    private BetRange getRangeFor(User user) {
        for (BetRange range : currentBets) {
            if (range.getM2().getUser().getId().equals(user.getId())) {
                return range;
            }
        }
        return null;
    }

    private class BetRange {

        private Member m1, m2;

        private int amount;

        private boolean expired;

        BetRange(Member m1, Member m2, int amount, boolean expired) {
            this.m1 = m1;
            this.m2 = m2;
            this.amount = amount;
            this.expired = expired;
        }

        Member getM1() {
            return m1;
        }

        Member getM2() {
            return m2;
        }

        int getAmount() {
            return amount;
        }

        boolean hasExpired() {
            return expired;
        }

        void setExpired() {
            this.expired = true;
        }
    }

}
