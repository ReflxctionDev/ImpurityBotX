package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;

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

public class GuildInfo extends AbstractCommand {


    @Override
    public String getCommand() {
        return "guildinfo";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel channel, User u, String[] args) {
        if (u.isBot()) {
            return;
        } else {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                    .setRandomColor()
                    .setAuthor("The Impurity Guild", "https://hypixel.net/threads/guild-impurity-discord-party-motd.1376085/", null)
                    .setThumbnail("https://hypixel.net/attachments/screen-shot-2017-10-26-at-1-40-22-pm-png.738051/")
                    .addField("Guild Master", "Inariiiii", true)
                    .addField("Guild Tag", "[IMPURE]", true)
                    .addBlankField()
                    .addField("Founded in:", "8th of September, 2017", true)
                    .addField("Texture Pack", "Coming soon!", true)
                    .addBlankField()
                    .addField("Founders:", "Inariiiii\nOneMcGuy\nHemajin", true)
                    .addField("Guild Housing", "Available, by MK4", true)
                    .build();
            channel.sendMessage(embed.build()).queue();
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
        return "Get information regarding the Impurity guild";
    }

    @Override
    public String getUsage() {
        return "-guildinfo";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
