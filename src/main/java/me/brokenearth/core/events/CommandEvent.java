package me.brokenearth.core.events;


import me.brokenearth.core.scheduler.Timer;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Copyright 2018 github.com/BrokenEarthDev
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * <p>
 * The event that is going to be triggered when the prefix is typed
 *
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 * @see me.brokenearth.core.commands.Command
 * @see me.brokenearth.core.commands.CommandExecutor
 */
public final class CommandEvent {
    /**
     * The executor of the command
     */
    private Member member;
    /**
     * The guild in which the executor is in
     */
    private Guild guild;
    /**
     * The jda
     */
    private JDA jda;
    /**
     * The command that is executed
     */
    private String command;
    /**
     * The prefix of the command
     */
    private String prefix;
    /**
     * The channel in which the command is executed
     */
    private MessageChannel channel;

    public CommandEvent(Member member, Guild guild, String command, String prefix, JDA jda, MessageChannel channel) {
        this.member = member;
        this.guild = guild;
        this.jda = jda;
        this.command = command;
        this.prefix = prefix;
        this.channel = channel;
    }

    /**
     * Gets the executor and
     *
     * @return it
     */
    public Member getMember() {
        return member;
    }

    /**
     * Gets the guild in which the executor is in and
     *
     * @return it
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * Gets the jda and
     *
     * @return it
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * Gets the command and
     *
     * @return it
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the prefix and
     *
     * @return it
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets the message channel and
     *
     * @return it
     */
    public MessageChannel getChannel() {
        return channel;
    }

    public void run() {
        int hi = 0;
        final Timer timer = new Timer() {
            int time = 0;

            @Override
            public void run() {
                if (time == 9) this.cancel();
                time++;
            }
        };
        timer.runEvery(9);
    }
}
