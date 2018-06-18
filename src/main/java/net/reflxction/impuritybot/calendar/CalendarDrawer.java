package net.reflxction.impuritybot.calendar;

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

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.reflxction.impuritybot.core.others.EmbedFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalendarDrawer {

    public void drawCalendar(MessageChannel c) {
        c.sendMessage("Impurity Events Calendar For **" + CalendarUtils.getMonthName() + "**").queue();
        List<EmbedBuilder> embeds = new ArrayList<>();
        for (int i = 1; i <= CalendarUtils.getMonthLength(CalendarUtils.getMonth()); i++) {
            embeds.add(newEmbed(i));
        }
        for (EmbedBuilder builder : embeds) {
            c.sendMessage(builder.build()).queue();
        }
    }

    private EmbedBuilder newEmbed(int day) {
        String month = CalendarUtils.getMonthName();
        int year = CalendarUtils.getYear();
        String name = CalendarUtils.getDayName(day);
        return new EmbedFactory(new EmbedBuilder())
                .setColor(Color.WHITE)
                .setTitle(month + " / " + day + " / " + year + " - " + name)
                .addField("", "-")
                .build();
    }

    public boolean verify(int day, MessageEmbed embed) {
        String beginning = CalendarUtils.getMonthName() + " / ";
        String end = " / " + CalendarUtils.getYear() + " - " + CalendarUtils.getDayName(day);
        return embed.getTitle().startsWith(beginning + day) && embed.getTitle().endsWith(end);
    }

    public String get(int day) {
        String beginning = CalendarUtils.getMonthName() + " / ";
        String end = " / " + CalendarUtils.getYear() + " - " + CalendarUtils.getDayName(day);
        return beginning + day + end;
    }

}
