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
package net.reflxction.impuritybot.data.warnings;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.data.DataManager;
import net.reflxction.impuritybot.data.IDataManager;
import net.reflxction.impuritybot.events.warnings.WarningGivenEvent;
import net.reflxction.impuritybot.events.warnings.WarningRemovedEvent;
import net.reflxction.impuritybot.events.warnings.WarningsClearedEvent;
import net.reflxction.impuritybot.main.ImpurityBot;

public class WarningManagerImpl implements IDataManager, IWarningManager {

    private ImpurityBot bot = ImpurityBot.getBot();

    private DataManager du = new DataManager(ImpurityBot.getBot());

    /**
     * Warns a user
     *
     * @param executor The user who warned
     * @param target   The user who got warned
     * @param reason   Reason of the warning
     * @param channel  Channel that the warning was given in
     */
    @Override
    public void warnUser(User executor, User target, String reason, TextChannel channel) {
        WarningGivenEvent event = new WarningGivenEvent(executor, target, reason, channel);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getWarningsFile().set("Warnings." + target.getId() + ".Name", target.getName());
            bot.getWarningsFile().set("Warnings." + target.getId() + ".Amount", getWarnings(target) + 1);
            bot.getWarningsFile().set("Warnings." + target.getId() + "." + getWarnings(target) + ".Reason", reason);
            bot.getWarningsFile().set("Warnings." + target.getId() + "." + getWarnings(target) + ".WarnedBy", executor.getId());
            du.saveFile(bot.getWarningsFile(), "warnings");
        }
    }

    @Override
    public int getWarnings(User u) {
        return bot.getWarningsFile().getInt("Warnings." + u.getId() + ".Amount");
    }

    /**
     * Removes a warning from a user
     *
     * @param executor The user who removed the warning
     * @param target   The user who lost the warning
     * @param channel  The channel that the warning was removed in
     * @param amount   The amount of warnings removed
     */
    @Override
    public void removeWarnings(User executor, User target, TextChannel channel, int amount) {
        WarningRemovedEvent event = new WarningRemovedEvent(executor, target, amount, channel);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getWarningsFile().set("Warnings." + target.getId() + ".Name", target.getName());
            bot.getWarningsFile().set("Warnings." + target.getId() + ".Amount", getWarnings(target) - 1);
            bot.getWarningsFile().set("Warnings." + target.getId() + "." + getWarnings(target), null);
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

    /**
     * The user who warned the user
     *
     * @param user      User to check for
     * @param warningID The ID/number of the warning
     * @return The user who warned the target
     */
    @Override
    public User getWarner(User user, int warningID) {
        return ImpurityBot.getJDA().getUserById(bot.getWarningsFile().getString("Warnings." + user.getId() + "." + warningID + ".WarnedBy.ID"));
    }

}
