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
package net.reflxction.impuritybot.commands.level;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.others.IAccess;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.data.level.LevelManager;

public class TopLevels extends AbstractCommand implements IAccess {

    private LevelManager lvl = new LevelManager();

    @Override
    public String getCommand() {
        return "top";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .addField("#1", lvl.getTopUsers().get(0).getName(), true)
                .addField("#2", lvl.getTopUsers().get(1).getName(), true)
                .addField("#3", lvl.getTopUsers().get(2).getName(), true)
                .addField("#4", lvl.getTopUsers().get(3).getName(), true)
                .addField("#5", lvl.getTopUsers().get(4).getName(), true)
                .addField((lvl.getPosition(event.getMember().getUser()) != -1) ? "#" + lvl.getPosition(event.getMember().getUser()) : "NA", event.getMember().getUser().getName())
                .setRandomColor()
                .build();
        c.sendMessage(builder.build()).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.LEVEL;
    }

    @Override
    public String getDescription() {
        return "Get the top users";
    }

    @Override
    public String getUsage() {
        return "-top";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
