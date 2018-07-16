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
package net.reflxction.impuritybot.core.events.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.eventsbus.Event;

/**
 * Fired when a command is being executed
 */
public class CommandProcessedEvent extends Event {

    // Whether the event is canceled
    private boolean canceled;

    // Instance of the JDA cache
    private JDA jda;

    // The command
    private AbstractCommand command;

    // The channel
    private TextChannel channel;

    // The sender
    private User author;

    // Instance of the guild
    private Guild guild;

    // The message
    private Message message;

    // Extra command arguments
    private String[] args;

    /**
     * Initializes a command event
     *
     * @param jda     Instance of the JDA cache
     * @param command Instance of the command
     * @param channel The channel the command was sent in
     * @param author  The command sender
     * @param guild   The guild that the command was sent into
     * @param message Instance of the message
     * @param args    Extra command arguments
     */
    public CommandProcessedEvent(JDA jda, AbstractCommand command, TextChannel channel, User author, Guild guild, Message message, String[] args) {
        this.jda = jda;
        this.command = command;
        this.channel = channel;
        this.author = author;
        this.guild = guild;
        this.message = message;
        this.args = args;
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
    public JDA getJDA() {
        return jda;
    }

    /**
     * {@inheritDoc}
     */
    public AbstractCommand getCommand() {
        return command;
    }

    /**
     * {@inheritDoc}
     */
    public TextChannel getChannel() {
        return channel;
    }

    /**
     * {@inheritDoc}
     */
    public User getAuthor() {
        return author;
    }

    /**
     * {@inheritDoc}
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * {@inheritDoc}
     */
    public Message getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    public String[] getArgs() {
        return args;
    }
}
