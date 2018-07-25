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
package net.reflxction.impuritybot.core.eros;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.data.credits.CreditsManager;
import net.reflxction.impuritybot.utils.lang.NumberUtils;

import java.util.Timer;
import java.util.TimerTask;

public class Slots extends AbstractCommand {

    private CreditsManager cu = new CreditsManager();

    private String[] emotes = new String[]{
            "🍎",  // APPLE
            "🍊",  // ORANGE
            "🍒",  // CHERRIES
            "🍆",  // EGGPLANT
            "🌭",  // HOT DOG
            "🍏",  // GREEN APPLE
            "🍌",  // BANANA
            "🍅",  // TOMATO
            "🌶",  // PEPPER
            "🍕",  // PIZZA
            "🍉",  // WATERMELON
            "🍍",  // PINEAPPLE
            "🌽",  // CORN
            "🍔",  // BURGER
            "🍐",  // PEAR
    };

    private NumberUtils nu = new NumberUtils();

    @Override
    public String getCommand() {
        return "slots";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        if (args.length != 1) {
            event.getChannel().sendMessage("**Incorrect command usage. Try `" + getUsage() + "`**.").queue();
        } else {
            try {
                int amount = Integer.parseInt(args[0]);
                if (cu.getUserCredits(event.getMember().getUser()) >= amount) {
                    if (amount < 10) {
                        event.getChannel().sendMessage("**You can't bet less than 10 credits!**").queue();
                    } else {
                        cu.setUserCredits(event.getMember().getUser(), cu.getUserCredits(event.getMember().getUser()) - amount);
                        String[] queuedEmotes = queueEmotes();
                        String first, second, third, fourth, fifth, sixth, seventh, eighth, ninth;
                        first = queuedEmotes[0];
                        second = queuedEmotes[1];
                        third = queuedEmotes[2];
                        fourth = queuedEmotes[3];
                        fifth = queuedEmotes[4];
                        sixth = queuedEmotes[5];
                        seventh = queuedEmotes[6];
                        eighth = queuedEmotes[7];
                        ninth = queuedEmotes[8];
                        Message message = null;
                        try {
                            message = event.getChannel().sendMessage("" +
                                    "╔════[SLOTS]════╗\n" +
                                    "║  " + first + "  ║  " + second + "  ║  " + third + "  ║\n" +
                                    ">  " + fourth + "  ║  " + fifth + "  ║  " + sixth + "  <\n" +
                                    "║  " + seventh + "  ║  " + eighth + "  ║  " + ninth + "  ║\n" +
                                    "╚════[SLOTS]════╝").complete(true);
                        } catch (RateLimitedException e) {
                            e.printStackTrace();
                        }
                        Message finalMessage = message;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                String[] queuedEmotes = queueEmotes();
                                String first, second, third, fourth, fifth, sixth, seventh, eighth, ninth;
                                first = queuedEmotes[0];
                                second = queuedEmotes[1];
                                third = queuedEmotes[2];
                                fourth = queuedEmotes[3];
                                fifth = queuedEmotes[4];
                                sixth = queuedEmotes[5];
                                seventh = queuedEmotes[6];
                                eighth = queuedEmotes[7];
                                ninth = queuedEmotes[8];
                                assert finalMessage != null;
                                finalMessage.editMessage("" +
                                        "╔════[SLOTS]════╗\n" +
                                        "║  " + first + "  ║  " + second + "  ║  " + third + "  ║\n" +
                                        ">  " + fourth + "  ║  " + fifth + "  ║  " + sixth + "  <\n" +
                                        "║  " + seventh + "  ║  " + eighth + "  ║  " + ninth + "  ║\n" +
                                        "╚════[SLOTS]════╝").queue();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        if (hasWon(fourth, fifth, sixth)) {
                                            int prize = nu.randomBetween(50, 100) + amount;
                                            cu.setUserCredits(event.getMember().getUser(), cu.getUserCredits(event.getMember().getUser()) + prize);
                                            event.getChannel().sendMessage("Congratulations! You have bet **" + amount + "** and won **" + prize + "**").queue();
                                        } else {
                                            event.getChannel().sendMessage("You have bet **" + amount + "** and lost everything!").queue();
                                        }
                                    }
                                }, 750);
                            }
                        }, 1200);
                    }
                } else {
                    event.getChannel().sendMessage("**You don't have enough credits to bet this amount!**").queue();
                }
            } catch (NumberFormatException ex) {
                event.getChannel().sendMessage("**You must enter a valid number!**").queue();
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
        return "Play slots";
    }

    @Override
    public String getUsage() {
        return "-slots <amount of credits to bet>";
    }

    private String randomEmote() {
        return emotes[nu.random(emotes.length - 1)];
    }

    private String[] queueEmotes() {
        String first, second, third, fourth, fifth, sixth, seventh, eighth, ninth;
        first = randomEmote();
        second = randomEmote();
        third = randomEmote();
        fourth = randomEmote();
        fifth = randomEmote();
        sixth = randomEmote();
        seventh = randomEmote();
        eighth = randomEmote();
        ninth = randomEmote();
        return new String[]{first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
    }

    private boolean hasWon(String first, String second, String third) {
        return first.equals(second) && second.equals(third) && third.equals(first);
    }

}
