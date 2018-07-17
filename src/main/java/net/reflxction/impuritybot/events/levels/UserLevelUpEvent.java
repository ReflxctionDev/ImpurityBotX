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
package net.reflxction.impuritybot.events.levels;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.eventsbus.Event;

/**
 * Fired when a user levels up
 */
public class UserLevelUpEvent extends Event {

    // The user who leveled up
    private User user;

    // The old level and the new level
    private int oldLevel, newLevel;

    // The channel
    private TextChannel channel;

    // Whether the event is canceled or not
    private boolean canceled;

    /**
     * Initializes a leveling up event
     *
     * @param user     User who leveled pu
     * @param oldLevel The old level
     * @param newLevel The new level
     * @param channel  The channel that the last message for the user was sent
     */
    public UserLevelUpEvent(User user, int oldLevel, int newLevel, TextChannel channel) {
        this.user = user;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
        this.channel = channel;
    }

    /**
     * Determine if this function is cancelable at all.
     *
     * @return If access to setCanceled should be allowed
     */
    @Override
    public boolean isCancelable() {
        return true;
    }

    /**
     * Determine if this event is canceled and should stop executing.
     *
     * @return The current canceled state
     */
    @Override
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Sets the cancel state of this event. Note, not all events are cancelable, and any attempt to
     * invoke this method on an event that is not cancelable will be ignored
     * <p>
     * The functionality of setting the canceled state is defined on a per-event bases.
     *
     * @param canceled The new canceled value
     */
    @Override
    public void setCanceled(boolean canceled) {
        if (!isCancelable()) return;
        this.canceled = canceled;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser() {
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public int getOldLevel() {
        return oldLevel;
    }

    /**
     * {@inheritDoc}
     */
    public int getNewLevel() {
        return newLevel;
    }

    /**
     * {@inheritDoc}
     */
    public TextChannel getChannel() {
        return channel;
    }
}
