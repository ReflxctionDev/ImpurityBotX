package net.reflxction.impuritybot.utils.data;/*
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

import net.reflxction.impuritybot.main.ImpurityBot;

import java.util.List;

public class PollsManager {

    private ImpurityBot bot;

    public PollsManager(ImpurityBot bot) {
        this.bot = (bot == null ? ImpurityBot.getBot() : bot);
    }

    private DataManager du = new DataManager(bot);


    public void addPoll(String id) {
        if (!isPoll(id)) {
            List<String> list = bot.getPollsFile().getStringList("Polls");
            list.add(id);
            bot.getPollsFile().set("Polls", list);
            du.savePollsFile();
        }
    }

    public void removePoll(String id) {
        if (isPoll(id)) {
            List<String> list = bot.getPollsFile().getStringList("Polls");
            list.remove(id);
            bot.getPollsFile().set("Polls", list);
            du.savePollsFile();
        }
    }

    public boolean isPoll(String id) {
        return bot.getPollsFile().getStringList("Polls").contains(id);
    }


}
