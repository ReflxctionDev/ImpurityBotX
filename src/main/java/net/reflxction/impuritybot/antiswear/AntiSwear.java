package net.reflxction.impuritybot.antiswear;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.utils.lang.StringUtils;

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
public class AntiSwear extends ListenerAdapter {

    private String[] swears = {"Asshole",
            "bitch",
            "cunt",
            "dick",
            "fuck",
            "fucker",
            "fuck off",
            "fuck you",
            "motherfucker",
            "shit",
            "pussy",
            "whore",
            "nigga",
            "nigger",
            "dickhead",
            "crap",
            "slut",
            "douche",
            "thot",
            "shut up",
            "boobies",
            "vagina",
            "penis",
            "ballsack",
            "kys",
            "gay",
            "cancerous",
            "motherfuck"};

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message m = event.getMessage();
        String content = m.getContentRaw().toLowerCase();
        User u = event.getAuthor();
        MessageChannel c = event.getChannel();
        Member member = event.getMember();
        if (!u.isBot()) {
            for (String swear : swears) {
                if (StringUtils.containsWhole(content, swear.toLowerCase()) &&
                        !c.getId().equalsIgnoreCase("378892621424361472") && !member.getRoles().contains(event.getGuild().getRoleById("395928472473698304"))) {
                    c.deleteMessageById(m.getId()).queue();
                    u.openPrivateChannel().complete().sendMessage("Sorry, but the message you've just sent contains unfriendly words. Please change your sentence or be nice!").queue();
                    u.openPrivateChannel().complete().sendMessage("Your message: " + content.replace(swear, "**" + swear + "**")).queue();
                }
            }
        }
    }
}
