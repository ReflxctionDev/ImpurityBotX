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

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.data.discord.PollsManager;

/**
 * Created by Reflxction, on 02/02/18.
 */
public class PollReactions extends ListenerAdapter {

    private PollsManager polls = new PollsManager(null);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel() instanceof TextChannel) {
            TextChannel c = (TextChannel) event.getChannel();
            final Guild g = event.getGuild();
            switch (g.getId()) {
                // The main Discord
                case "363721897743089668":
                    if (polls.isPoll(c.getId())) {
                        final Message m = event.getMessage();
                        Emote no = g.getEmoteById("412956641630093316");
                        m.addReaction("\u2705").queue();
                        m.addReaction(no).queue();
                    }
                    if (c.getId().equals("433614676086095890")) {
                        final Message m = event.getMessage();
                        m.addReaction("\u2B50").queue();
                    }
                    break;
                // The development Discord
                case "471703262492360725":
                    if (polls.isPoll(c.getId())) {
                        final Message m = event.getMessage();
                        Emote no = g.getEmoteById("471724203247140867");
                        Emote yes = g.getEmoteById("471724203649794058");
                        m.addReaction(yes).queue();
                        m.addReaction(no).queue();
                    }
                    break;
            }
        }
    }
}
