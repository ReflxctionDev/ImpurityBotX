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
package net.reflxction.impuritybot.utils.data;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.eros.ErosItem;
import net.reflxction.impuritybot.main.ImpurityBot;

public class CreditsManager implements IDataManager {

    private ImpurityBot bot = ImpurityBot.getPlugin(ImpurityBot.class);
    private final DataManager du = new DataManager(bot);

    public int getUserCredits(User u) {
        return bot.getCreditsFile().getInt("Credits." + u.getId() + ".Credits");
    }

    private int getSuperDices(User u) {
        return bot.getCreditsFile().getInt("Credits." + u.getId() + ".SuperDices");
    }

    private void addUserForFirstTime(User u, int credits) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".Name", u.getName());
        bot.getCreditsFile().set("Credits." + u.getId() + ".Credits", credits);
        du.saveCreditsFile();
    }

    public void giveDice(User u) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".Name", u.getName());
        bot.getCreditsFile().set("Credits." + u.getId() + ".Credits", getUserCredits(u));
        bot.getCreditsFile().set("Credits." + u.getId() + ".SuperDices", getSuperDices(u) + 1);
        du.saveCreditsFile();
    }

    public void giveCan(User u, int amount) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".Name", u.getName());
        bot.getCreditsFile().set("Credits." + u.getId() + ".Credits", getUserCredits(u));
        bot.getCreditsFile().set("Credits." + u.getId() + ".ImpureCans", getCans(u) + amount);
        du.saveCreditsFile();
    }

    public void giveChest(User u, int amount) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".Name", u.getName());
        bot.getCreditsFile().set("Credits." + u.getId() + ".Credits", getUserCredits(u));
        bot.getCreditsFile().set("Credits." + u.getId() + ".ImpureChests", getChests(u) + amount);
        du.saveCreditsFile();
    }

    public int getChests(User u) {
        return bot.getCreditsFile().getInt("Credits." + u.getId() + ".ImpureChests");
    }

    public int getCans(User u) {
        return bot.getCreditsFile().getInt("Credits." + u.getId() + ".ImpureCans");
    }

    public void setUserCredits(User u, int credits) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".Name", u.getName());
        bot.getCreditsFile().set("Credits." + u.getId() + ".Credits", credits);
        du.saveCreditsFile();
    }

    public void giveDaily(User u, int credits) {
        try {
            setUserCredits(u, getUserCredits(u) + credits);
            du.saveCreditsFile();
        } catch (NullPointerException ex) {
            addUserForFirstTime(u, credits);
            du.saveCreditsFile();
        }
    }

    public boolean canBuyItem(User u, ErosItem items) {
        return getUserCredits(u) >= items.getPrice();
    }
}
