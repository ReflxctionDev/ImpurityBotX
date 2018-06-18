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

package net.reflxction.impuritybot.utils.data.exp;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.NumberUtils;
import net.reflxction.impuritybot.utils.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ExpManager {

    private ImpurityBot bot = ImpurityBot.getBot();

    private DataManager du = new DataManager(bot);

    public int getRemExp(User u) {
        return getUserExp(u) % 1000;
    }

    public void addUserForFirstTime(User u) {
        try {
            bot.getExpFile().set("Exp." + u.getId() + ".Name", u.getName());
            bot.getExpFile().set("Exp." + u.getId() + ".Exp", 5);
            bot.getExpFile().set("Exp." + u.getId() + ".NextExp", 1000);
            bot.getExpFile().set("Exp." + u.getId() + ".Level", 1);
            bot.getExpFile().set("Exp." + u.getId() + ".NextLevel", 2);
            du.saveFile(bot.getExpFile(), "exp");
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLACK + "Stack-Trace");
            ex.printStackTrace();
        }
    }




    public int getUserExp(User u) {
        int exp;
        try {
            exp = bot.getExpFile().getInt("Exp." + u.getId() + ".Exp");
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLACK + "Stack-Trace");
            ex.printStackTrace();
            exp = bot.getExpFile().getInt("Exp." + u.getId() + ".Exp");
        }
        return exp;
    }

    public void setUserExp(User u, int exp) throws NullPointerException {
        bot.getExpFile().set("Exp." + u.getId() + ".Name", u.getName());
        bot.getExpFile().set("Exp." + u.getId() + ".Exp", exp);
        du.saveFile(bot.getExpFile(), "exp");
    }

    private int getUserNextExp() {
        return 1000;
    }

    public void setUserNextExp(User u) {
        bot.getExpFile().set("Exp." + u.getId() + ".Name", u.getName());
        bot.getExpFile().set("Exp." + u.getId() + ".NextExp", 1000);
        du.saveFile(bot.getExpFile(), "exp");
    }


    public boolean canLevelUp(User u) {
        return getUserExp(u) == getUserNextExp() || getUserExp(u) > getUserNextExp();
    }

    public void addNormalExp(User u) throws NullPointerException {
        NumberUtils nu = new NumberUtils();
        setUserExp(u, getUserExp(u) + nu.randomBetween(5, 15));
    }


}