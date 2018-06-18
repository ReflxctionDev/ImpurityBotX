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
 * limitations under the License
 */
package net.reflxction.impuritybot.utils.data;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.points.PointsRank;

public class PointsManager {

    private ImpurityBot bot = ImpurityBot.getBot();

    private DataManager data = new DataManager(bot);

    public void addUserForFirstTime(User u) {
        bot.getPointsFile().set("Points." + u.getId() + ".Name", u.getName());
        bot.getPointsFile().set("Points." + u.getId() + ".Level", 0);
        bot.getPointsFile().set("Points." + u.getId() + ".Points", 1);
        data.saveFile(bot.getPointsFile(), "points");
    }

    public int getUserPoints(User u) {
        return bot.getPointsFile().getInt("Points." + u.getId() + ".Points");
    }

    public void setUserPoints(User u, int points) {
        bot.getPointsFile().set("Points." + u.getId() + ".Name", u.getName());
        bot.getPointsFile().set("Points." + u.getId() + ".Level", getRank(u).getLevel());
        bot.getPointsFile().set("Points." + u.getId() + ".Points", points);
        data.saveFile(bot.getPointsFile(), "points");
    }

    public int getLevel(User u) {
        return bot.getPointsFile().getInt("Points." + u.getId() + ".Level");
    }

    public PointsRank getRank(User u) {
        int level = getLevel(u);
        return PointsRank.getByLevel(level);
    }

    public void givePoints(User u, int points) {
        setUserPoints(u, getUserPoints(u) + points);
    }

    public void removePoints(User u, int points) {
        setUserPoints(u, getUserPoints(u) - points);
    }

    public boolean deservesRank(User u, PointsRank rank) {
        return getUserPoints(u) >= rank.getRange().getMinimum();
    }

    public PointsRank getNextRank(User u) {
        return PointsRank.getByLevel(getLevel(u) + 1);
    }

}
