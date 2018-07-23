/*
 * * Copyright 2017-2018 github.com/BrokenEarthDev
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
package net.reflxction.impuritybot.events.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;

public final class CommandEvent {

    private Member member;
    private Guild guild;
    private JDA jda;
    private Message message;
    private MessageChannel channel;

    public CommandEvent(JDA jda, Guild guild, MessageChannel channel, Member member, Message message) {
        this.jda = jda;
        this.guild = guild;
        this.channel = channel;
        this.member = member;
        this.message = message;
    }

    public Member getMember() {
        return member;
    }

    public Guild getGuild() {
        return guild;
    }

    public JDA getJDA() {
        return jda;
    }

    public Message getMessage() {
        return message;
    }

    public MessageChannel getChannel() {
        return channel;
    }

}
