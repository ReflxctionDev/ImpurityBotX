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
package net.reflxction.impuritybot.events.warnings;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

/**
 * Fired when a warning has been given
 */
public class WarningGivenEvent extends WarningEvent {

    private boolean canceled;

    // The warning reason
    private String reason;

    /**
     * Default constructor for all warning eventsbus
     *
     * @param target   Target that the event ran on
     * @param executor The one who ran the event
     */
    public WarningGivenEvent(User target, User executor, String reason, TextChannel channel) {
        super(target, executor, channel);
        this.reason = reason;
    }

    /**
     * The warning reason
     *
     * @return The reason why the warning was given
     */
    public String getReason() {
        return reason;
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
}
