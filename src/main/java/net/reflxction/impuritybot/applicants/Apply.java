/*
 * * Copyright 2018 github.com/BrokenEarthDev
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
package net.reflxction.impuritybot.applicants;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.awt.*;


public class Apply {

    private String playerName;
    private User sender;

    private boolean isSent = false;

    public Apply(String playerName, User sender) {
        this.playerName = playerName;
        this.sender = sender;
    }

    public void apply(MessageChannel channel) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .addField("IGN", playerName)
                .addField("Plancke", "https://plancke.io/hypixel/player/stats/" + playerName)
                .addField("Discord user", sender.getName() + "#" + sender.getId())
                .addField("Note", "Don't forget to reply to that user!")
                .setTitle(playerName)
                .setColor(Color.decode("#e84118"))
                .build();
        channel.sendMessage(builder.build()).queue();
        isSent = true;
    }

    public void apply() {
        apply(ImpurityBot.getImpurityGuild().getTextChannelById("469126105689882625"));
    }

    public boolean isSent() {
        return isSent;
    }

}
