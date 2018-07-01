package net.reflxction.impuritybot.commands.miscs;

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

public class MakeEmbed extends AbstractCommand {

    @Override
    public String getCommand() {
        return "makeembed";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        String content = getMessageContent();
        if (content.length() <= 10) {
            c.sendMessage("Syntax: `-makeembed (Embed Title) {-Field title-} {{Field content}}`").queue();
            c.sendMessage("Remember: You must put the title in ( ), the field title in {- -}, and the field content in {{ }}").queue();
            c.sendMessage("E.g: ```yml\n-makeembed (My life) {-It's bad-} {{I want to suicide}}```").queue();
        } else {
            String whole = content.substring(10);
            int fbt = whole.indexOf("(");
            int sbt = whole.indexOf(")");
            String title = whole.substring(fbt, sbt);
            int firstfieldt = whole.indexOf("{-");
            int firstfieldt2 = whole.indexOf("-}");
            String fieldtitle = whole.substring(firstfieldt, firstfieldt2);
            int firstfield = whole.indexOf("{{");
            int firstfield2 = whole.indexOf("}}");
            String fieldc = whole.substring(firstfield, firstfield2);
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                    .setTitle(title.replace("(", "").replace(")", ""))
                    .addField(fieldtitle.replace("{-", "").replace("-}", ""), fieldc.replace("{", "").replace("}", ""), true)
                    .setRandomColor()
                    .build();
            c.sendMessage(embed.build()).queue();
            c.deleteMessageById(m.getId()).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Create an embed";
    }

    @Override
    public String getUsage() {
        return "-makeembed (Embed Title) {-Field title-} {{Field content}}";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}