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
package net.reflxction.impuritybot.core.info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.reflxction.impuritybot.core.others.EmbedFactory;

import java.io.File;

/**
 * Class which contains punishments guidelines
 */
public enum EnumPunishments implements IEnumInfo {

    P1("Discord Punishment System", "We have a warning system in place to punish rulebreakers. Warnings reset every week, so one or two warns won’t kill you. You should stay chill and keep calm. If you think a warning is unfair, contact an officer or admin. Remember that rules are made for a purpose. For example, although there is no precise and clear definition to spam or excessive tagging, you will be warned if you cause inconvenience to any member of the guild and this discord server. Below are details on what you will be warned for and the consequences of being warned multiple times."),
    P2("What you will be warned for", "1. Spam\n" +
            "2. Swearing directed towards other people\n" +
            "3. Not obeying higher people\n" +
            "4. Not using the correct channel (e.g. Bot commands in general)\n" +
            "5. Excessive/Inappropriate tagging (Spamming Admins, Tagging discord member/impurity member etc.)\n" +
            "6. Mild insults directed towards others\n" +
            "7. Insulting others/Toxicity \n" +
            "8. Encouraging suicide"),
    P3("Warning Punishment System", "1st + 2nd Warn - Nothing Happens\n" +
            "3rd warn - 5 hour mute\n" +
            "4th warn - Kick\n" +
            "5th warn - Ban \n" +
            "Note that you can be both warned and muted for excessive/repeated verbal abuse/toxicity. The length of the mute varies with severity.");

    private String title, description;

    EnumPunishments(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EmbedBuilder getAsEmbed() {
        return new EmbedFactory(new EmbedBuilder())
                .addField(getTitle(), getDescription())
                .setRandomColor()
                .build();
    }


    public static File getImage() {
        return EnumImages.PUNISHMENTS.getFile();
    }

}
