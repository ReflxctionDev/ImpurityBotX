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
package net.reflxction.impuritybot.utils.lang;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String capitalize(String arg0) {
        return arg0.substring(0, 1).toUpperCase() + arg0.substring(1);
    }

    public static String getTimeEST() {
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        Date date = new Date();
        String hour = new SimpleDateFormat("hh:mm aa").format(date);
        if (hour.startsWith("0"))
            hour = hour.substring(1);

        String dayweek = new SimpleDateFormat("EEEE").format(date);

        String month = new SimpleDateFormat("MMMM").format(date);

        String daymonth = new SimpleDateFormat("dd").format(date);

        if (daymonth.endsWith("3")) {
            daymonth = daymonth + "rd";
        } else if (daymonth.endsWith("2")) {
            daymonth = daymonth + "nd";
        } else if (daymonth.endsWith("1")) {
            daymonth = daymonth + "st";
        } else {
            daymonth = daymonth + "th";
        }

        String year = new SimpleDateFormat("y").format(date);

        String time = dayweek + " " + month + " " + daymonth + ", " + year + " at " + hour;

        return time;
    }

    public static String fixGrammar(String arg0) {
        return capitalize(arg0.replace(" im ", " I'm ")
                .replace("dont", "don't")
                .replace("cant", "can't")
                .replace(" i ", " I ")
                .replace(" i'm ", " I'm "));
    }

    public static String mentionToId(String arg0) {
        return arg0
                .replace("<", "")
                .replace("@", "")
                .replace(">", "")
                .replace("!", "");
    }

    public static String channelToId(String arg0) {
        return arg0
                .replace("<", "")
                .replace("#", "")
                .replace(">", "");
    }

    public static String s(Object arg0) {
        return String.valueOf(arg0);
    }

    public static boolean containsWhole(String source, String sub) {
        String pattern = "\\b" + sub + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    public static User getUser(String mention) {
        return ImpurityBot.getJDA().getUserById(mentionToId(mention));
    }

    public static String combine(String[] args, int start) {
        if (start > args.length) throw new IllegalArgumentException("The start point is greater than the args length");
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        return builder.toString().trim();
    }

    public static String combine(String... strings) {
        return combine(strings, 0);
    }

    public static String[] toArgs(String toArgs) {
        return toArgs.split("\\s+");
    }

    public static String[] toArgs(String toArgs, int start) {
        String[] str = toArgs(toArgs);
        String combined = combine(str,start);
        return toArgs(combined);
    }

}
