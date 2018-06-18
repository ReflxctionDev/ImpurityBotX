package net.reflxction.impuritybot.eros;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.NumberUtils;
import net.reflxction.impuritybot.utils.data.CreditsManager;
import net.reflxction.impuritybot.utils.data.DataManager;

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

public class Daily extends AbstractCommand {

    private final ImpurityBot bot = ImpurityBot.getPlugin(ImpurityBot.class);

    private final CreditsManager cu = new CreditsManager();
    private final NumberUtils nu = new NumberUtils();

   /* HashMap<User, Integer> cooldownMap = new HashMap<>();
    HashMap<User, Integer> cooldownTime = new HashMap<>();
    HashMap<User, BukkitRunnable> cooldownTask = new HashMap<>();
*/

    @Override
    public String getCommand() {
        return "daily";
    }


    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        int remainingTime = bot.getCreditsFile().getInt("Credits." + u.getId() + ".TimeLeft");
        if (remainingTime != 0) {
            //you must wait
            int hours = remainingTime / 60 / 60;
            int minutes = remainingTime / 60 - hours * 60;
            int seconds = remainingTime % 60;

            String message = "no lel";
            if (hours == 0 && minutes > 0) {
                message = "**You must wait " + minutes + " minutes and " + seconds + " seconds before using this command again.**";
            }
            if (hours == 0 && minutes == 0) {
                message = "**You must wait " + seconds + " seconds before using this command again.**";
            } else if (hours != 0 && minutes != 0){
                message = "**You must wait " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds before using this command again.**";
            }
            c.sendMessage(message).queue();
        }
        if (remainingTime == 0) {
            //code starts here
            int i = nu.randomBetween(50, 150);
            cu.giveDaily(u, i);
            c.sendMessage("**You have been given " + i + " credits. Total**: " + cu.getUserCredits(u)).queue();
            //code ends here
            bot.getCreditsFile().set("Credits." + u.getId() + ".TimeLeft", 43200);
            new DataManager(bot).saveCreditsFile();
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
    public String getDescription() {
        return "Claim your daily credits";
    }

    @Override
    public String getUsage() {
        return "-daily";
    }

    @Override
    public long getDelay() {
        return 86400;
    }

}
