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
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.reflxction.impuritybot.events.swears.UserSwearEvent;
import net.reflxction.impuritybot.filter.FilterManager;

/**
 * Class which listens for {@link net.reflxction.impuritybot.events.swears.UserSwearEvent}
 */
public class UserSwearListener {

    @Subscribe
    public void onUserSwear(UserSwearEvent event) {
        FilterManager manager = event.getManager();
        if(manager.getState()) {
//            event.getMessage().delete().queue();
            PrivateChannel pm = event.getUser().openPrivateChannel().complete();
            pm.sendMessage("Sorry, but your message **" + event.getMessage().getContentRaw() + "** contains censored content, and thus has been deleted. Please watch your language next time").queue();
            event.getMessage().delete().queue();
        }
    }

}
