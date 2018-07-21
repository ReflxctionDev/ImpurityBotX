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
package net.reflxction.impuritybot.levels;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.utils.data.PointsManager;
import net.reflxction.impuritybot.utils.data.exp.ExpManager;
import net.reflxction.impuritybot.utils.data.level.LevelManager;

/**
 * Listener which listens to user messages
 *
 * @author Reflxction
 */
public class MessageListener extends ListenerAdapter {

    private LevelManager lu = new LevelManager();
    private ExpManager eu = new ExpManager();
    private PointsManager points = new PointsManager();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User u = event.getAuthor();
        TextChannel c = event.getChannel();
        Guild g = event.getGuild();

        if (!u.isBot()) {
            if (g.getId().equals("363721897743089668")) {
                if (!eu.hasTalkedBefore(u)) {
                    eu.addUserForFirstTime(u);
                    lu.addUserForFirstTime(u);
                } else {
                    eu.addNormalExp(u);
                    if (points.deservesRank(u, points.getNextRank(u))) {
                        points.upgrade(u, c);
                    }
                }
                if (eu.canLevelUp(u)) {
                    lu.levelUp(u, c);
                }

            }
        }
    }
}
