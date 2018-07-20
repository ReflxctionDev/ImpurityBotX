package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import java.awt.*;
import java.util.Random;

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

public class Introduce extends AbstractCommand {

    @Override
    public String getCommand() {
        return "introduce";
    }

    @Override
    public String[] getAliases() {
        return null;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public String getDescription() {
        return "Give an introduction about yourself";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g1 = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                    .setTitle("Not enough arguments!")
                    .setDescription("Try -introduce <description>")
                    .build();
            c.sendMessage(embed.build()).queue();
        }
        if (args.length > 0) {
            String whole = "";
            for (int i = 0; i < args.length; i++) {
                String arg = args[i] + " ";
                whole = whole + arg;
            }
            int ign1 = whole.indexOf("(");
            int ign2 = whole.indexOf(")");
            int games1 = whole.indexOf("<");
            int games2 = whole.indexOf(">");
            int desc1 = whole.indexOf("[");
            int desc2 = whole.indexOf("]");
            String ign = whole.substring(ign1, ign2).replace("(", "")
                    .replace(")", "");
            String[] games = whole.substring(games1, games2).replace("<", "")
                    .replace(">", "").split(" ");
            String desc = whole.substring(desc1, desc2).replace("[", "")
                    .replace("]", "");
            int i = 0;
            EmbedBuilder builder = new EmbedBuilder();
            Random random = new Random();
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);
            Color color = new Color(r, g, b);
            builder.setColor(color);
            builder.setAuthor(u.getName(), null, null);
            builder.addField("About me", StringUtils.capitalize(StringUtils.fixGrammar(desc)), true);
            builder.addField("IGN", ign, true);
            String gammes = "";
            for (int x = 0; x < games.length; x++) {
                String gms = StringUtils.capitalize(games[x]) + "\n";
                gammes = gammes + gms;
            }
            builder.addField("Games I play", gammes, true);
            builder.setThumbnail(u.getAvatarUrl());
            c.sendMessage(builder.build()).queue();
            c.deleteMessageById(m.getId()).queue();
        }
    }
}
