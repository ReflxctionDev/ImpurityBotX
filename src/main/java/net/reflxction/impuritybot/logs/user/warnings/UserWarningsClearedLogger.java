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
package net.reflxction.impuritybot.logs.user.warnings;

import com.google.common.eventbus.Subscribe;
import net.dv8tion.jda.core.EmbedBuilder;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.warnings.WarningsClearedEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;

public class UserWarningsClearedLogger extends Logger {

    @Subscribe
    public void onWarningsCleared(WarningsClearedEvent event) {
        if(event.getManager().getWarnings(event.getTarget()) > 0) {
            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                    .setColor(Color.GREEN)
                    .setAuthor(event.getTarget().getName(), null, event.getTarget().getAvatarUrl())
                    .setDescription("**" + event.getExecutor().getName() + "** has cleared all the warnings for **" + event.getTarget().getName() + "**.")
                    .setFooter(StringUtils.getTimeEST(), null)
                    .setThumbnail(event.getTarget().getAvatarUrl())
                    .build();
            getWarningLogs().sendMessage(builder.build()).queue();
        } else {
            event.setCanceled(true);
            event.getChannel().sendMessage("**" + event.getTarget().getName() + "** doesn't have any warnings!").queue();
        }
    }

}
