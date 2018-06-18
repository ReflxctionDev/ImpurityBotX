package net.reflxction.impuritybot.commands.fun.exclusive;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;

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

public class EightBall extends AbstractCommand {

    private String BALL_EMOJI = "\uD83C\uDFB1";

    private String[] answers = new String[]{
            "Maybe, but my opinion doesn't count on that matter.",
            "Yes, for sure, yes",
            "Haha yes",
            "You won't handle the truth.",
            "Yes, unless you run out of memes",
            "No, that's obvious!",
            "Definitely not.",
            "I have a headache, I can't predict now",
            "Don't rely too much on it",
            "You are doomed",
            "Without a doubt",
            "Very doubtful",
            "Sure, sure...",
            "Most likely",
            "It's certain",
            "Better not to say now",
            "In my point of view: yes",
            "In my point of view: nope",
            "Ha, in your dreams."
    };


    private boolean fromImpurity(User u) {
        JDA j = ImpurityBot.getJDA();
        Guild g = j.getGuildById("363721897743089668");
        Member m = g.getMember(u);
        Role role = g.getRoleById("413362035334840324");
        return m.getRoles().contains(role);
    }

    @Override
    public String getCommand() {
        return "8ball";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (!fromImpurity(u)) {
            c.sendMessage("**This command is exclusive for the Impurity members only!**").queue();
        } else {
            if (args.length == 0) {
                c.sendMessage("**Usage**: " + getUsage()).queue();
            } else if (args.length > 0) {
                if (!m.getContentRaw().endsWith("?")) {
                    EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                            .setColor(Color.RED)
                            .setTitle("Doesn't look like a question, but a statement. Hint: '?'")
                            .build();
                    c.sendMessage(builder.build()).queue();
                } else {
                    Random r = new Random();
                    int i = r.nextInt(answers.length);
                    EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                            .setRandomColor()
                            .setDescription(BALL_EMOJI + " " + answers[i])
                            .build();
                    c.sendMessage(builder.build()).queue();
                }
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

    @Override
    public String getDescription() {
        return "See what the 8ball has to say";
    }

    @Override
    public String getUsage() {
        return "-8ball <question, make sure it ends with ?>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
