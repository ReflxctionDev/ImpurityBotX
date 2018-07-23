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
package net.reflxction.impuritybot.core.listeners.discord;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class TableFlip extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        TextChannel c = event.getTextChannel();
        Message m = event.getMessage();
        String content = m.getContentRaw();
        if (content.contains("(╯°□°）╯︵ ┻━┻")) {
            c.sendMessage("Stop flipping.").queue();
            c.sendMessage("┬─┬ ノ( ゜-゜ノ)").queue();
        }
        if (content.contains("¯\\_(ツ)_/¯")) {
            c.sendMessage("( ͡° ͜ʖ ͡°)").queue();
        }
    }

}
