

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

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.utils.data.CreditsManager;
import net.reflxction.impuritybot.utils.lang.NumberUtils;

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
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        final String s = "\uD83C\uDFB0";
        if (args.length != 1) {
            c.sendMessage("**Incorrect command usage. Try `" + getUsage() + "`**.").queue();
        } else {
            try {
                int amount = Integer.parseInt(args[0]);
                if (cu.getUserCredits(u) >= amount) {
                    if (amount < 10) {
                        c.sendMessage("**You can't bet less than 10 credits!**").queue();
                    } else {
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
                        c.sendMessage("" +
                                "╔════[SLOTS]════╗\n" +
                                "║  " + first + "  ║  " + second + "  ║  " + third + "  ║\n" +
                                ">  " + fourth + "  ║  " + fifth + "  ║  " + sixth + "  <\n" +
                                "║  " + seventh + "  ║  " + eighth + "  ║  " + ninth + "  ║\n" +
                                "╚════[SLOTS]════╝").queue();
                    }
                } else {
                    c.sendMessage("**You don't have enough credits to bet this amount!").queue();
                }
            } catch (NumberFormatException ex) {
                c.sendMessage("**You must enter a valid number!**").queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.EROS;
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
        return new String[] {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
    }

}
