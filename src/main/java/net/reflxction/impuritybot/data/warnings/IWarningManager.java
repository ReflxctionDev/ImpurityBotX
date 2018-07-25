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

public interface IWarningManager {

    /**
     * Warns a user
     *
     * @param executor The user who warned
     * @param target   The user who got warned
     * @param reason   Reason of the warning
     * @param channel  Channel that the warning was given in
     */
    void warnUser(User executor, User target, String reason, TextChannel channel);

    /**
     * The warnings a user has
     *
     * @param user User to get for
     * @return The amount of warnings the user has
     */
    int getWarnings(User user);

    /**
     * Removes a warning from a user
     *
     * @param executor The user who removed the warning
     * @param target   The user who lost the warning
     * @param channel  The channel that the warning was removed in
     * @param amount   The amount of warnings removed
     */
    void removeWarnings(User executor, User target, TextChannel channel, int amount);

    /**
     * Clears the warnings of a user
     *
     * @param remover The user who cleared warnings
     * @param target  The user who's warnings got cleared
     * @param channel The channel that the action was done in
     */
    void clearWarnings(User remover, User target, TextChannel channel);

    /**
     * The warning reason of a specific warning
     *
     * @param user      User to check for
     * @param warningID The ID/number of the warning
     * @return The reason of the given warning
     */
    String getWarningReason(User user, int warningID);

    /**
     * The user who warned the user
     *
     * @param user      User to check for
     * @param warningID The ID/number of the warning
     * @return The user who warned the target
     */
    User getWarner(User user, int warningID);

}
