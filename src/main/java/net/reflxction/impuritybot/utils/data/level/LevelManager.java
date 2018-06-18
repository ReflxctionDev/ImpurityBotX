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

package net.reflxction.impuritybot.utils.data.level;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.GuildUtils;
import net.reflxction.impuritybot.utils.data.DataManager;
import net.reflxction.impuritybot.utils.data.exp.ExpManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelManager {

    public static ImpurityBot bot = ImpurityBot.getPlugin(ImpurityBot.class);

    private DataManager du = new DataManager(bot);

    private ExpManager exp = new ExpManager();

    public void addUserForFirstTime(User u) {
        setUserLevel(u, 1);
    }

    public void setUserLevel(User u, int lvl) {
        bot.getExpFile().set("Exp." + u.getId() + ".Level", lvl);
        bot.getExpFile().set("Exp." + u.getId() + ".NextLevel", lvl + 1);
    }


    public int getUserLevel(User u) {
        return bot.getExpFile().getInt("Exp." + u.getId() + ".Level");
    }

    public void levelUp(User u, MessageChannel c) {
        EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                .setAuthor(u.getName(), null, null)
                .setRandomColor()
                .setThumbnail(u.getAvatarUrl())
                .addField("Level Up!", u.getName() + " has just reached level " + (getUserLevel(u) + 1), true)
                .build();
        c.sendMessage(embed.build()).queue();
        setUserLevel(u, getUserLevel(u) + 1);
        exp.setUserExp(u, exp.getRemExp(u));
        du.saveFile(bot.getExpFile(), "exp");
        exp.setUserNextExp(u);
        du.saveFile(bot.getExpFile(), "exp");
    }

    private LevelAdapter revertLevel(User u) {
        int exp = this.exp.getUserExp(u);
        int level = getUserLevel(u);
        while (exp < level * 1000) {
            exp = exp + 1000;
        }
        return new LevelAdapter(exp, level);
    }


    private List<User> getUserByExp(long exp) {
        List<Member> members = GuildUtils.members();
        List<User> toget = new ArrayList<>();
        for (Member m : members) {
            User u = m.getUser();
            LevelAdapter adapter = revertLevel(u);
            if (adapter.getExp() == exp) {
                toget.add(u);
            }
        }
        return toget;
    }

    public List<User> getTopUsers() {
        List<Integer> lvls = new ArrayList<>();
        List<User> topusers = new ArrayList<>();
        for (Member m : GuildUtils.members()) {
            lvls.add(revertLevel(m.getUser()).getExp());
            Collections.sort(lvls);
            Collections.reverse(lvls);
        }

        topusers.add(getUserByExp(lvls.get(0)).get(0));
        topusers.add(getUserByExp(lvls.get(1)).get(0));
        topusers.add(getUserByExp(lvls.get(2)).get(0));
        return topusers;
    }

}