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
package net.reflxction.impuritybot.core.listeners.bot;

import com.google.common.eventbus.Subscribe;
import net.dv8tion.jda.core.EmbedBuilder;
import net.reflxction.impuritybot.events.levels.UserLevelUpEvent;
import net.reflxction.impuritybot.core.others.EmbedFactory;

/**
 * Listener for {@link net.reflxction.impuritybot.events.levels.UserLevelUpEvent}
 */
public class LevelUpListener {

    @Subscribe
    public void onUserLevelUp(UserLevelUpEvent event) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setRandomColor()
                .setAuthor(event.getUser().getName() + " - Level up!", null, event.getUser().getAvatarUrl())
                .setThumbnail(event.getUser().getAvatarUrl())
                .setDescription("**" + event.getUser().getName() + "** has reached level **" + event.getNewLevel() + "**")
                .build();
        event.getChannel().sendMessage(builder.build()).queue();
    }

}
