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
package net.reflxction.impuritybot.commands.admin.news;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.calendar.CalendarDrawer;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Calendar extends AbstractCommand {

    private CalendarDrawer drawer = new CalendarDrawer();

    @Override
    public String getCommand() {
        return "cal";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        List<Message> msgs = new ArrayList<>();
        final Member member = g.getMember(u);
        if (member.getRoles().contains(Roles.EVENTS_TEAM)) {
            for (Message msg : c.getHistory().retrievePast(31).complete()) {
                if (msg.getAuthor().equals(j.getSelfUser()) && !msg.getId().equals(m.getId())) {
                    msgs.add(msg);
                }
            }
            if (args.length < 2) {
                c.sendMessage("**Incorrect usage. Try " + getUsage() + "**").queue();
            } else {
                try {
                    StringBuilder eventDesc = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        eventDesc.append(" ").append(args[i]);
                    }
                    int i = Integer.parseInt(args[0]);
                    for (Message msg : msgs) {
                        final MessageEmbed embed = msg.getEmbeds().get(0);
                        if (drawer.verify(i, embed)) {
                            EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                                    .setColor(new Color(73, 88, 255))
                                    .setTitle(drawer.get(i))
                                    .addField("", eventDesc.toString())
                                    .build();
                            msg.editMessage(builder.build()).queue();
                        }
                    }
                    m.delete().queue();
                } catch (NumberFormatException ex) {
                    c.sendMessage("**You must enter a valid day!**").queue();
                }
            }
        } else {
            c.sendMessage("**You must be a member of the events team to be able to edit the calendar!**").queue();
        }
    }


    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CALENDAR;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Manage events calendar";
    }

    @Override
    public String getUsage() {
        return "-cal <day> <event>";
    }

}
