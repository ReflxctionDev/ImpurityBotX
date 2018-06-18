package net.reflxction.impuritybot.commands.hypixel;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.IgnManager;

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

public class IGN extends AbstractCommand {

    private IgnManager igns = new IgnManager();

    @Override
    public String getCommand() {
        return "ign";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (args.length == 1) {
            igns.setIGN(u, args[0]);
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                    .setDescription("Name assigned!")
                    .addField("Discord Name:", u.getName() + "#" + u.getDiscriminator(), true)
                    .addField("Name assigned:", args[0], true)
                    .setRandomColor()
                    .build();
            c.sendMessage(embed.build()).queue();
            igns.setIGN(u, args[0]);
        } else {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                    .setTitle("Invalid arguments!")
                    .setDescription("Try -ign <your in-game name>")
                    .build();
            c.sendMessage(embed.build()).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public String getDescription() {
        return "Assign your Discord account to your Minecrat name";
    }

    @Override
    public String getUsage() {
        return "-ign <your in-game name>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
