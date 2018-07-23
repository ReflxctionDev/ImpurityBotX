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
package net.reflxction.impuritybot.utils.data;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.guild.GuildUtils;

import java.util.ArrayList;
import java.util.List;

public class IgnManager implements IDataManager {

    private ImpurityBot bot = ImpurityBot.getBot();

    private DataManager du = new DataManager(bot);

    public void setIGN(User u, String ign) {
        bot.getIgnsFile().set("IGNs." + u.getId() + ".Name", u.getName());
        bot.getIgnsFile().set("IGNs." + u.getId() + ".IGN", ign);
        du.saveIgnsFile();
    }

    public String getIGN(User u) {
        String ign = bot.getIgnsFile().getString("IGNs." + u.getId() + ".IGN");
        return ign == null ? "NO IGN" : ign;
    }

    public List<User> getUserByIGN(String ign) {

        List<User> users = new ArrayList<>();
        for (Member m : GuildUtils.members()) {
            if (getIGN(m.getUser()).equalsIgnoreCase(ign)) {
                users.add(m.getUser());
            }
        }
        return users;
    }

    public boolean hasAssignedIGN(User u) {
        return !getIGN(u).equals("NO IGN");
    }

}
