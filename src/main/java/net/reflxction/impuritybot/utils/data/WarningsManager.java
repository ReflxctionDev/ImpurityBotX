package net.reflxction.impuritybot.utils.data;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.logs.user.UserWarnLoggers;
import net.reflxction.impuritybot.main.ImpurityBot;

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
public class WarningsManager implements IDataManager {

    private ImpurityBot bot;

    public WarningsManager(ImpurityBot bot) {
        this.bot = (bot == null ? ImpurityBot.getPlugin(ImpurityBot.class) : bot);
    }

    public static UserWarnLoggers loggers = new UserWarnLoggers((ImpurityBot) ImpurityBot.getPlugin(ImpurityBot.class));

    private final DataManager du = new DataManager(this.bot);

    public void giveWarning(User warner, User u, String reason) {
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", getWarnings(u) + 1);
        du.saveFile(bot.getWarningsFile(), "warnings");
        bot.getWarningsFile().set("Warnings." + u.getId() + "." + getWarnings(u) + ".Reason", reason);
        bot.getWarningsFile().set("Warnings." + u.getId() + "." + getWarnings(u) + ".WarnedBy.ID", warner.getId());
        bot.getWarningsFile().set("Warnings." + u.getId() + "." + getWarnings(u) + ".WarnedBy.Name", warner.getName());
        du.saveFile(bot.getWarningsFile(), "warnings");
        loggers.logGivenWarn(u, warner, reason);
    }

    public int getWarnings(User u) {
        int warnings = bot.getWarningsFile().getInt("Warnings." + u.getId() + ".Amount");
        if (warnings == 0) {
            return 0;
        }
        return warnings;
    }

    public void removeWarning(User u, User remover) {
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", getWarnings(u) - 1);
        du.saveFile(bot.getWarningsFile(), "warnings");
        loggers.logRemovedWarn(u, remover, 1);
    }

    public void removeWarnings(User u, User remover, int amount) {
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", getWarnings(u) - amount);
        du.saveFile(bot.getWarningsFile(), "warnings");
        loggers.logRemovedWarn(u, remover, amount);
    }

    public void clearWarnings(User u, User remover) {
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Name", u.getName());
        bot.getWarningsFile().set("Warnings." + u.getId() + ".Amount", 0);
        du.saveFile(bot.getWarningsFile(), "warnings");
        loggers.logClearedWarnings(u, remover);
    }

    public String getWarningReason(User u, int warning) {
        return bot.getWarningsFile().getString("Warnings." + u.getId() + "." + warning + ".Reason");
    }

    public User getWarnedBy(User u, int warning) {
        return ImpurityBot.getJDA().getUserById(bot.getWarningsFile().getString("Warnings." + u.getId() + "." + warning + ".WarnedBy.ID"));
    }

}
