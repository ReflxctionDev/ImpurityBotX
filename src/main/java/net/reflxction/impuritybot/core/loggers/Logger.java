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
package net.reflxction.impuritybot.core.loggers;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.awt.*;

/*
 * Created by Reflxction, on 01/28/18.
 */
public abstract class Logger extends ListenerAdapter {

    protected static final Color L_RED = new Color(255, 97, 94);

    protected static final Color L_GREEN = new Color(136, 255, 131);

    protected static final Color L_BLUE = new Color(168, 201, 255);

    protected static final Color YELLOW = new Color(255, 255, 0);

    public TextChannel getLogs() {
        return ImpurityBot.getImpurityGuild().getTextChannelById("407256687310012447");
    }

    protected TextChannel getWarningLogs() {
        return ImpurityBot.getImpurityGuild().getTextChannelById("407968054165635083");
    }

}
