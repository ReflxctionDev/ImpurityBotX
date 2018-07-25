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
import net.reflxction.impuritybot.core.eventsbus.Event;
import net.reflxction.impuritybot.data.warnings.WarningManagerImpl;

/**
 * Fired when a warning event occurs (warning given, removed, etc.). Superclass for all warning eventsbus
 */
public abstract class WarningEvent extends Event {

    // Instance of the warning manager
    private WarningManagerImpl manager = new WarningManagerImpl();

    // The target and the executor
    private User target, executor;

    // The channel that the action was done in
    private TextChannel channel;

    /**
     * Default constructor for all warning eventsbus
     *
     * @param target   Target that the event ran on
     * @param executor The one who ran the event
     */
    WarningEvent(User target, User executor, TextChannel channel) {
        this.target = target;
        this.executor = executor;
        this.channel = channel;
    }

    /**
     * The target user
     *
     * @return The target user in the event
     */
    public User getTarget() {
        return target;
    }

    /**
     * The subject
     *
     * @return The executor in the event
     */
    public User getExecutor() {
        return executor;
    }

    /**
     * An instance of the warning manager
     *
     * @return ^
     */
    public WarningManagerImpl getManager() {
        return manager;
    }

    /**
     * The text channel that the action was done in
     *
     * @return ^
     */
    public TextChannel getChannel() {
        return channel;
    }
}
