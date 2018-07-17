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

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.events.warnings.WarningGivenEvent;
import net.reflxction.impuritybot.events.warnings.WarningRemovedEvent;
import net.reflxction.impuritybot.events.warnings.WarningsClearedEvent;
import net.reflxction.impuritybot.main.ImpurityBot;

public class WarningsManager implements IDataManager {

    private ImpurityBot bot = ImpurityBot.getBot();

    private DataManager du = new DataManager(ImpurityBot.getBot());

    public void giveWarning(User warner, User u, String reason, TextChannel channel) {
        WarningGivenEvent event = new WarningGivenEvent(u, warner, reason, channel);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", getWarnings(u) + 1);
            du.saveFile(bot.getWarningsFile(), "warnings");
            bot.getWarningsFile().set("Warnings." + u.getId() + "." + getWarnings(u) + ".Reason", reason);
            bot.getWarningsFile().set("Warnings." + u.getId() + "." + getWarnings(u) + ".WarnedBy.ID", warner.getId());
            bot.getWarningsFile().set("Warnings." + u.getId() + "." + getWarnings(u) + ".WarnedBy.Name", warner.getName());
            du.saveFile(bot.getWarningsFile(), "warnings");
        }
    }

    public int getWarnings(User u) {
        int warnings = bot.getWarningsFile().getInt("Warnings." + u.getId() + ".Amount");
        if (warnings == 0) {
            return 0;
        }
        return warnings;
    }

    public void removeWarning(User u, User remover, TextChannel channel) {
        WarningRemovedEvent event = new WarningRemovedEvent(u, remover, 1, channel);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", getWarnings(u) - 1);
            du.saveFile(bot.getWarningsFile(), "warnings");
        }
    }

    public void removeWarnings(User u, User remover, int amount, TextChannel channel) {
        WarningRemovedEvent event = new WarningRemovedEvent(u, remover, amount, channel);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", getWarnings(u) - amount);
            du.saveFile(bot.getWarningsFile(), "warnings");
        }
    }

    public void clearWarnings(User u, User remover, TextChannel channel) {
        WarningsClearedEvent event = new WarningsClearedEvent(u, remover, channel);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
            bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", 0);
            du.saveFile(bot.getWarningsFile(), "warnings");
        }
    }

    public String getWarningReason(User u, int warning) {
        return bot.getWarningsFile().getString("Warnings." + u.getId() + "." + warning + ".Reason");
    }

    public User getWarnedBy(User u, int warning) {
        return ImpurityBot.getJDA().getUserById(bot.getWarningsFile().getString("Warnings." + u.getId() + "." + warning + ".WarnedBy.ID"));
    }

}
