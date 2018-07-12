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
package net.reflxction.impuritybot.bridge;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BridgeListener extends ListenerAdapter {

    private BridgesManager manager = new BridgesManager();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannelType() == ChannelType.TEXT) {
            Channel channel = event.getTextChannel();
            System.out.println(channel.getName() + " > " + manager.isBridge(channel));
            if (manager.isBridge(channel)) {
                if (!event.getAuthor().getId().equals("393791576700092416")) {
                    TextChannel bridgeChannel = (TextChannel) manager.getCorrespondingChannel(channel);

                    bridgeChannel.sendMessage(event.getMessage()).queue();
                }
            }
        }
    }
}
