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

public class Help extends AbstractCommand {

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel channel, User u, String[] args) {
        EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                .setRandomColor()
                .setAuthor("Impurity Bot\n", null, "https://cdn.discordapp.com/attachments/374104070945767424/400663237428772868/implogo.png")
                .addField("Admin Commands:", "-announce - Make an announcement (Only works in <#374104070945767424>)\n-kick <user> <reason> - Kick a user from the Discord server\n-warn <@user> <reason> - Warn a user\n-removewarn <@user> [amount] - Remove a warning (or more) from a specific user\n-clearwarnings <@user> - Reset a user's warnings\n-pin <message id> - Pin a message\n-unpin <message id> - Unpin a message", true)
                .addField("User Commands:", "-credits - Show the ones who contributed in making the bot\n-guildinfo - Show information about the Impurity guild\n-makeembed - Create an embed message (user -makeembed to show syntax)\n-8ball <question> - Question the 8ball (Impurity exclusive)\n-rate <term> - Get the bot's opinion (Impurity exclusive)\n-ping - Get the bot's ping\n-invite - Get the official invite link\n-userinfo <@user> / -userinfo - Get information about a specific user\n-discordinfo - Get information about the Discord guild and the Hypixel guild", true)
                .addField("Hypixel Commands:", "-updateroles <IGN> - Receive the roles that match your stats (WIP)\n-ign <your in-game name> - Assign your Discord name to your Minecraft name\n-ignof <@user> - Get the IGN of that user (They must have assigned their IGN)", true)
                .addField("Level Commands:", "-level <@user> - Get the level of that user\n-top - Get the top users (WIP)\nMore coming soon!", true)
                .build();
        channel.sendMessage(embed.build()).queue();
//https://cdn.discordapp.com/icons/363721897743089668/1860cac179e12745c0e5c52b34e40a54.webp - Old
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.HELP;
    }

    @Override
    public String getDescription() {
        return "Show the bot commands";
    }

    @Override
    public String getUsage() {
        return "-help";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
